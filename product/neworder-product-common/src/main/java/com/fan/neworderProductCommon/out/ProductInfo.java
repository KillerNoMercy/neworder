package com.fan.neworderProductCommon.out;

import lombok.Data;

/**
 * Auther: zhengfan
 * Date: 2020/1/4
 */
@Data
public class ProductInfo {
    private String id;
    private String name;
    private double price;
    private Integer stock;
    private Integer status;
    private String details;
    private String images;
    private String comments;
}
