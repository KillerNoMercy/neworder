package com.fan.neworderProductServer.dto;

/**
 * Auther: zhengfan
 * Date: 2020/1/3
 * Desc: 结果传输类
 */
public class ResultDTO<T> {
    private Integer code;
    private String msg;
    private T data;

    private ResultDTO (Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResultDTO<T> success(T data) {
        return new ResultDTO<>(200, "成功", data);
    }

    public boolean isSuccess() {
        return this.code == 200;
    }

    public static ResultDTO fail(String msg) {
        return new ResultDTO(400, msg, null);
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
