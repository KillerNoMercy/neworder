package com.fan.neworderProductServer.vo;

import lombok.Data;


/**
 * Auther: zhengfan
 * Date: 2020/1/3
 */
@Data
public class ProductVO {
    private String name;
    private double price;
    private Integer stock;
    private String desc;
    private String details;
    private String images;
    private String comments;
}
