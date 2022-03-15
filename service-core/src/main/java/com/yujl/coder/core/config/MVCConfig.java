package com.yujl.coder.core.config;

import com.yujl.coder.core.handler.HandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MVCConfig extends WebMvcConfigurerAdapter {
    @Bean
    public HandlerInterceptor securityInterceptor() {
        return new HandlerInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(securityInterceptor())
                .excludePathPatterns("/static/*")
                .excludePathPatterns("/api/core/userInfo/register")
                .excludePathPatterns("/api/core/userInfo/login")
                .excludePathPatterns("/api/core/userInfo/checkToken")
                .addPathPatterns("/**");
    }

}