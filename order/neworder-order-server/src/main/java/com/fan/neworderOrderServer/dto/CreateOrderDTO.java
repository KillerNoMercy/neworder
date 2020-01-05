package com.fan.neworderOrderServer.dto;

import lombok.Data;

/**
 * Auther: zhengfan
 * Date: 2020/1/4
 */
@Data
public class CreateOrderDTO {
    private String token;
    private String products;
}
