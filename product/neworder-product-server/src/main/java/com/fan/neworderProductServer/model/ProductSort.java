package com.fan.neworderProductServer.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Auther: zhengfan
 * Date: 2020/1/3
 */
@Entity
@Data
public class ProductSort {
    @Id
    private String id;
    private String name;
    private String slogan;
    private Date createTime;
    private Date updateTime;
}
