package com.chj.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author ：chj
 * @date ：Created in 2020/5/9 23:07
 * @params :  第五步  配置拦截规则
 */
@Component
public class LoginAdapter extends WebMvcConfigurerAdapter {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/orderPhone");
    }
}
