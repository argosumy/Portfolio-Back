package com.portfolio.security.configuration;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.portfolio.security.services.CaptchaService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.time.Duration;

public class CaptchaFilter extends GenericFilterBean {
    private final CaptchaService captchaService;

    public CaptchaFilter(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        if(requestURI.contains("users") && request.getMethod().equals("POST") || requestURI.contains("captcha")) {
            ResponseCookie cookie = ResponseCookie.from("JSESSIONID", request.getSession().getId())
                    .httpOnly(true)
                    .secure(true)
                    .path("/")
                    .sameSite("None")
                    .maxAge(Duration.ofMinutes(3))
                    .build();
            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        }
        if(requestURI.contains("users") && request.getMethod().equals("POST")) {
            CachedBodyHttpServletRequest cachedBody = new CachedBodyHttpServletRequest(request);
            String body = new String(cachedBody.getCachedBody());
            JsonObject jsonObject = JsonParser.parseString(body).getAsJsonObject();
            String userAnswer = jsonObject.get("answerCaptcha") == null
                    ? null
                    : jsonObject.get("answerCaptcha").getAsString();
            String sessionId = cachedBody.getSession().getId();
            if(!captchaService.validateCaptcha(sessionId, userAnswer)) {
                captchaService.removeAnswer(sessionId);
                captchaService.cleanAnswers();
                response.sendError(HttpStatus.FORBIDDEN.value(), "Invalid captcha answer");
                return;
            }
            captchaService.removeAnswer(sessionId);
            filterChain.doFilter(cachedBody, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}