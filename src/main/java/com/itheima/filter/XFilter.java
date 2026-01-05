package com.itheima.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
public class XFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        System.out.println("XFilter 初始化");

    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("XFilter 拦截到请求");

        filterChain.doFilter(servletRequest, servletResponse);

        System.out.println("XFilter 放行请求");

    }

    @Override
    public void destroy() {
        System.out.println("XFilter 销毁");
    }
}
