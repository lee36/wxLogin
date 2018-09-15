package com.lee.wxlogin.config;

import com.lee.wxlogin.intercepter.CheckTokenIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        CheckTokenIntercepter intercepter = new CheckTokenIntercepter();
        registry.addInterceptor(intercepter).addPathPatterns("/user/add");
    }
}
