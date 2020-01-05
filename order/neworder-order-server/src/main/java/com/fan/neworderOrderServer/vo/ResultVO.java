package com.fan.neworderOrderServer.vo;

import lombok.Data;

/**
 * Auther: zhengfan
 * Date: 2020/1/3
 * Desc: 通用返回值
 */
@Data
public class ResultVO<T> {
    private int state;
    private String msg;
    private T data;

    private ResultVO(int state, String msg, T data) {
        this.state = state;
        this.msg = msg;
        this.data = data;
    }

    public static ResultVO ok() {
        return new ResultVO(200, "ok", null);
    }

    public static ResultVO error() {
        return new ResultVO(400, "error", null);
    }

    public static <T> ResultVO<T> success(T data) {
        return new ResultVO(200, "成功", data);
    }

    public static ResultVO fail(String msg) {
        return new ResultVO(400, msg, null);
    }

}
