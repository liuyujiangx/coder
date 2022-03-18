package com.yujl.coder.core.pojo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author liuyj
 */
@Component
@ConfigurationProperties(prefix = "coder")
@PropertySource("classpath:application.yml")
public class CoderProperties {
    private String allowedOrigins;

    public String getAllowedOrigins() {
        return allowedOrigins;
    }

    public void setAllowedOrigins(String allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }
}
