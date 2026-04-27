package com.portfolio.security.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.security")
public class SecurityProperties {
    private String [] permitAll;

    public String [] getPermitAll() {
        return permitAll;
    }

    public void setPermitAll(String [] permitAll) {
        this.permitAll = permitAll;
    }
}

