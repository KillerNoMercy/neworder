package com.fan.neworderProductClient.feign;

import com.fan.neworderProductCommon.in.UpdateStocksBO;
import com.fan.neworderProductCommon.out.ProductInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Auther: zhengfan
 * Date: 2020/1/4
 */
@FeignClient(name = "product")
public interface ProductClient {

    @PostMapping("/product/listByIds")
    List<ProductInfo> listByIds(@RequestBody List<String> ids);

    @PostMapping("/product/updateStocks")
    void updateStocks(@RequestBody List<UpdateStocksBO> updateStocksBOList);
}
