package com.itheima.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


//@WebFilter(urlPatterns = "/*")
/*
（1）拦截具体路径：/login
（2）目录拦截：/emps/*
（3）拦截所有：/*
 */
public class DemoFilter implements Filter {


    @Override//初始化，只调用一次（服务器打开）
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter 初始化");
        Filter.super.init(filterConfig);
    }

    @Override//调用多次（拦截到请求后调用）
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("拦截到请求");
        /*
        过滤器链：一个Web应用中配置了多个过滤器
        过滤器顺序：按照过滤器名称排序
         */
        //放行
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("放行完成");
    }

    @Override//销毁，只调用一次（服务器关闭）
    public void destroy() {
        Filter.super.destroy();
        System.out.println("filter 销毁");
    }
}
