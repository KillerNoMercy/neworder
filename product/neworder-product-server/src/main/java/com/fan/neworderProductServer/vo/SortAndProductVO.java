package com.fan.neworderProductServer.vo;

import lombok.Data;

import java.util.List;

/**
 * Auther: zhengfan
 * Date: 2020/1/3
 */
@Data
public class SortAndProductVO {
    private String sortName;
    private String slogan;
    private List<ProductVO> products;
}
