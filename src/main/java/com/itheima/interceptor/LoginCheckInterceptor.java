package com.itheima.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
Filter与Interceptor:
(1)接口规范不同：过滤器需要实现Filter接口，而拦截器需要实现HandlerInterceptor接口。
(2)拦截范围不同：过滤器Filter会拦截所有的资源，而Interceptor只会拦截Spring环境中的资源。
 */


@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override//
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //1.获取请求url
        String url = request.getRequestURL().toString();
        log.info("请求的url:{}",url);
        //2.判断请求url中是否包含login，若包含，说明是登录操作
        if (url.contains("login")) {
            //登录操作。放行。。。
            return true;
        }

        //3.获取请求头中的令牌（token）
        String jwt = request.getHeader("token");
        //4.判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if (!StringUtils.hasLength(jwt)){
            log.info("token不存在，未登录");
            Result res = Result.error("NOT_LOGIN");
            //手动将对象转换为json格式 ------------> 阿里巴巴fastJSON
            String noLogin = JSONObject.toJSONString(res);
            //设置响应信息
            response.getWriter().write(noLogin);
            return false;
        }
        //5.解析token，若解析失败，返回错误结果（未登录）
        try{
            JWTUtils.parseClaim(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("JWT令牌解析失败，未登录。。。。");
            Result res = Result.error("NOT_LOGIN");
            //手动将对象转换为json格式 ------------> 阿里巴巴fastJSON
            String noLogin = JSONObject.toJSONString(res);
            //设置响应信息
            response.getWriter().write(noLogin);
            return false;
        }
        //6.放行
        log.info("JWT令牌校验成功，放行。。。。。。。。。。。。。");
        return true;


    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
