package com.yujl.coder.core.config;

import com.yujl.coder.core.pojo.properties.CoderProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author liuyj
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Autowired
    CoderProperties coderProperties;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins(coderProperties.getAllowedOrigins())
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .maxAge(3600);

    }
}
