package com.fan.neworderProductServer.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Auther: zhengfan
 * Date: 2020/1/3
 */
@Data
@Entity
public class Product {
    @Id
    private String id;
    private String name;
    private double price;
    private Integer stock;
    private String sortId;
    private Integer status;
    private String details;
    private String images;
    private String comments;
    private Date createTime;
    private Date updateTime;
}
