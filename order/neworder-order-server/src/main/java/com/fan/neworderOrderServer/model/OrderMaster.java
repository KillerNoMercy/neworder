package com.fan.neworderOrderServer.model;

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
public class OrderMaster {
    @Id
    private String id;
    private String userName;
    private String userPhone;
    private String userAddr;
    private Integer status;
    private double totalAmount;
    private Date createTime;
    private Date updateTime;
}
