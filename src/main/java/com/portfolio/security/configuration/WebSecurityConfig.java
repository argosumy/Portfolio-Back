package com.portfolio.security.configuration;

import com.portfolio.security.services.CaptchaService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@EnableConfigurationProperties({CorsProperties.class, SecurityProperties.class})
public class WebSecurityConfig {
    private final SecurityFilter securityFilter;
    private final CaptchaService captchaService;
    private final CorsProperties corsProperties;
    private final SecurityProperties securityProperties;

    public WebSecurityConfig(SecurityFilter securityFilter, CaptchaService captchaService,
                             CorsProperties corsProperties, SecurityProperties securityProperties) {
        this.securityFilter = securityFilter;
        this.captchaService = captchaService;
        this.corsProperties = corsProperties;
        this.securityProperties = securityProperties;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> req
                                .requestMatchers("/auth/signin", "/users/captcha", "/users/*/cv").permitAll()
                                .requestMatchers(HttpMethod.POST,"/users").permitAll()
                                .requestMatchers(securityProperties.getPermitAll()).permitAll()
                                .anyRequest().authenticated())
                .cors(c -> c.configurationSource(corsConfigurationSource()))
                .csrf(CsrfConfigurer::disable)
                .addFilterBefore(new CaptchaFilter(captchaService), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(corsProperties.getAllowedOrigins());
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        corsConfiguration.addAllowedHeader(HttpHeaders.CONTENT_TYPE);
        corsConfiguration.addAllowedHeader(HttpHeaders.AUTHORIZATION);
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }
}