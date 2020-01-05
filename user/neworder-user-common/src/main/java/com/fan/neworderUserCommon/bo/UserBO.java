package com.fan.neworderUserCommon.bo;

import lombok.Data;

/**
 * Auther: zhengfan
 * Date: 2020/1/3
 */
@Data
public class UserBO {
    private String username;
    private Integer role;
    private String name;
    private String phone;
    private String addr;
}
