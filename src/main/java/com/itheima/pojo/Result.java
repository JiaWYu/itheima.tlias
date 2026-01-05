package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code; //响应码
    private String msg; //返回的信息
    private T data; //返回的数据

    //增删改 成功响应
    public static <T> Result<T> success() {
        return new Result<>(1, "success", null);
    }

    //查 成功相应
    public static <T> Result<T> success(T data) {
        return new Result<>(1, "success", data);
    }

    //失败响应
    public static <T> Result<T> error() {
        return new Result<>(0, "error", null);
    }

    public static <T> Result<T> error(String msg) {
        return new Result<>(0, msg, null);
    }
}