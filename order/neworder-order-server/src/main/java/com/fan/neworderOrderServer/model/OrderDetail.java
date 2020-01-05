package com.fan.neworderOrderServer.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Auther: zhengfan
 * Date: 2020/1/4
 */
@Data
@Entity
public class OrderDetail {
    @Id
    private String id;
    private String orderId;
    private String productId;
    private String productName;
    private double productPrice;
    private String productDetails;
    private Integer count;
    private Date createTime;
    private Date updateTime;
}
