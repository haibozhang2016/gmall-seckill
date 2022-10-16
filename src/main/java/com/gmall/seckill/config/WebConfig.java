package com.gmall.seckill.config;

import com.gmall.seckill.filter.SessionValidFilter;
import com.gmall.seckill.interceptor.AuthorizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.Filter;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = new String[] {
            "classpath:/META-INF/resources/", "classpath:resources/",
            "classpath:/static/", "classpath:/public/"
    };

    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Bean
    @Order(1)
    public FilterRegistrationBean sessionFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new DelegatingFilterProxy("sessionValidFilter"));
        registration.addUrlPatterns("/**");
        registration.setName("sessionValidFilter");
        return registration;
    }

    @Override
    @Order(2)
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/**")) {
            registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
        }
        super.addResourceHandlers(registry);
    }

    @Override
    @Order(3)
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        // 映射为 user 的控制器下的所有映射
        // registry.addInterceptor(authorizationInterceptor).addPathPatterns("/user/index").excludePathPatterns("/index", "/");
        registry.addInterceptor(authorizationInterceptor).excludePathPatterns("/index", "/");
        super.addInterceptors(registry);
    }

}
