package com.fan.neworderUserServer.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Auther: zhengfan
 * Date: 2020/1/3
 */
@Entity
@Data
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String token;
    private Integer role;
    private String name;
    private String phone;
    private String addr;
}
