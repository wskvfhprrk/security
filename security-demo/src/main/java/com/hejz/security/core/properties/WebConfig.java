package com.hejz.security.core.properties;

import com.hejz.web.aop.TimeFilter;
import com.hejz.web.aop.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.CallableProcessingInterceptor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: hejz
 * @Description:
 * @Date: 2019/6/18 8:55
 */
//@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private TimeInterceptor timeInterceptor;

    //异步拦截调器
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        //设置异步超时时间
//        configurer.setDefaultTimeout(10000);
        //可以自己设置线程池，spring是异步时每次建立一个线程池，如果通过setTaskExecutor可以设置可重复使用的异步线程池
//        configurer.setTaskExecutor(e)
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
    }

    @Bean
    public FilterRegistrationBean timeFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();
        registrationBean.setFilter(timeFilter);
        //可配置在那些路径上起作用
        List<String> urls = new ArrayList<>();
        urls.add("/*");
        registrationBean.setUrlPatterns(urls);
        return registrationBean;
    }
}
