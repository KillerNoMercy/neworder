package com.fan.neworderProductServer.controller;

import com.fan.neworderProductCommon.in.UpdateStocksBO;
import com.fan.neworderProductCommon.out.ProductInfo;
import com.fan.neworderProductServer.dto.ResultDTO;
import com.fan.neworderProductServer.service.ProductService;
import com.fan.neworderProductServer.vo.ResultVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Auther: zhengfan
 * Date: 2020/1/3
 */
@RestController
@RequestMapping("product")
public class ProductController {

    private ProductService productService;

    public ProductController (ProductService productService) {
        this.productService = productService;
    }

    /**
     * 按类别,查询所有在售商品
     * @return
     */
    @GetMapping("list")
    public ResultVO list() {
        ResultDTO list = productService.list();

        return ResultVO.success(list.getData());
    }

    @PostMapping("listByIds")
    public List<ProductInfo> listByIds(@RequestBody List<String> ids) {
        return productService.listByIds(ids);
    }

    @PostMapping("updateStocks")
    public void updateStocks(@RequestBody List<UpdateStocksBO> updateStocksBOList) {
        productService.updateStocks(updateStocksBOList);
    }

}
