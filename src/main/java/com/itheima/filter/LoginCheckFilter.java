package com.itheima.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //1.获取请求url
        String url = request.getRequestURL().toString();
        log.info("请求的url:{}",url);
        //2.判断请求url中是否包含login，若包含，说明是登录操作
        if (url.contains("login")) {
            //登录操作。放行。。。
            filterChain.doFilter(request, response);
            return ;
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
            return ;
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
            return ;
        }
        //6.放行
        log.info("JWT令牌校验成功，放行。。。。。。。。。。。。。");
        filterChain.doFilter(request, response);


    }

}
