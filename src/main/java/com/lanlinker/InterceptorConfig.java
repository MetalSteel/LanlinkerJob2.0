package com.lanlinker;

import com.lanlinker.interceptor.BackgroundInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 拦截器配置
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 后台登录拦截器
        registry.addInterceptor(new BackgroundInterceptor()).addPathPatterns("/background/**");
    }
}
