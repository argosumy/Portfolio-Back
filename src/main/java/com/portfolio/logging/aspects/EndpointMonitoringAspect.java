package com.portfolio.logging.aspects;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Instant;

@Aspect
@Component
@Profile("monitoring")
public class EndpointMonitoringAspect {
    private final Logger logger = LoggerFactory.getLogger("END POINT MONITOR");
    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public Object monitorEndpoint(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String endpoint = request.getRequestURI();
        String method = request.getMethod();
        String remoteAddr = request.getRemoteAddr();
        Instant start = Instant.now();
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            logEndpointError(endpoint, method, e);
            throw e;
        }
        Instant end = Instant.now();

        logEndpointExecution(endpoint, method, remoteAddr, start, end);
        return result;
    }

    private void logEndpointExecution(String endpoint, String method, String remoteAdr, Instant start, Instant end) {
        long duration = end.toEpochMilli() - start.toEpochMilli();
        logger.info("\n\r{}-{}, DURATION : {}, IP {}", method, endpoint, duration, remoteAdr);
        // Здесь можно добавить логику для сохранения метрик или отправки их в систему мониторинга
    }

    private void logEndpointError(String endpoint, String method, Exception e) {
        System.err.println(String.format("Error in endpoint: %s %s, Error: %s", method, endpoint, e.getMessage()));
        logger.error("\n\r{}-{}\n\r{}", method, endpoint, e.getMessage());
        // Здесь можно добавить логику для обработки ошибок
    }

}