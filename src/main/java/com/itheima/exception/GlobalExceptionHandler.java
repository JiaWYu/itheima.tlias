package com.itheima.exception;


import com.itheima.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    //捕获异常类型
    @ExceptionHandler(Exception.class)//捕获所有异常
    public Result exception(Exception e) {
        e.printStackTrace();
        return Result.error("操作失败，请联系管理员");
    }

}
