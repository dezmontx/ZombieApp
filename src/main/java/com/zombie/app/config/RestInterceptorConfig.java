package com.zombie.app.config;

import com.zombie.app.interceptor.RestInterceptor;
import com.zombie.app.service.AuthService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class RestInterceptorConfig extends WebMvcConfigurerAdapter {
    private final AuthService authService;

    public RestInterceptorConfig(AuthService authService) {this.authService = authService;}

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RestInterceptor(authService));
    }
}
