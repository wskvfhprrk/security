package com.hejz.web.aop;


import javax.servlet.*;
import java.io.IOException;

/**
 * @Auther: hejz
 * @Description:
 * @Date: 2019/6/18 8:12
 */
//如果不使用@Component注解，需要配置@Bean——FilterRegistrationBean
//@Component
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("----过滤器初始化----");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("doFilter");
        long l = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        long l1 = System.currentTimeMillis();
        System.out.println("timeFilter用时：" + (l1 - l));
    }

    @Override
    public void destroy() {
        System.out.println("----过滤器销毁----");
    }
}
