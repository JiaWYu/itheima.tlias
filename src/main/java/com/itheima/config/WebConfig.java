package com.itheima.config;

import com.itheima.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration//配置类
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginCheckInterceptor longinCheckInterceptor;

    @Override// 添加拦截器
    public void addInterceptors(InterceptorRegistry registry) {
//       路径规则
//       /*：       仅匹配一级路径（如 /depts），不匹配多级（如 /depts/1）
//       /**：      匹配所有层级路径（如 /depts、/depts/1/2）
//       /depts/*： 仅匹配 **/depts 下一级路径 **（如 /depts/1），不匹配 /depts 本身或多级
//       /depts/**：匹配 **/depts 下所有层级路径 **（含 /depts 本身），不匹配其他根路径（如 /emps/1）

/*
        拦截路径	    含义	                举例
        /*	        一级路径	            能匹配 /depts, /emps, /login，不能匹配 /depts/1
        /**	        任意级路径	        能匹配 /depts, /depts/1, /depts/1/2
        /depts/*	/depts 下的一级路径	能匹配 /depts/1，不能匹配 /depts/1/2, /depts
        /depts/**	/depts 下的任意级路径	能匹配 /depts, /depts/1, /depts/1/2，不能匹配 /emps/1
*/

        registry.addInterceptor(longinCheckInterceptor).addPathPatterns("/**").excludePathPatterns("/login");
    }
}
