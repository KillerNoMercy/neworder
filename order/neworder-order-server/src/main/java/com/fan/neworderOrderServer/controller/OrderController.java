package com.fan.neworderOrderServer.controller;

import com.alibaba.fastjson.JSON;
import com.fan.neworderOrderServer.dto.CreateOrderDTO;
import com.fan.neworderOrderServer.dto.ProductDTO;
import com.fan.neworderOrderServer.dto.ResultDTO;
import com.fan.neworderOrderServer.service.OrderService;
import com.fan.neworderOrderServer.vo.ResultVO;
import com.fan.neworderUserClient.feign.UserClient;
import com.fan.neworderUserCommon.bo.UserBO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Auther: zhengfan
 * Date: 2020/1/3
 */
@RestController
@RequestMapping("order")
public class OrderController {

    private OrderService orderService;
    private UserClient userClient;

    public OrderController(OrderService orderService, UserClient userClient) {
        this.orderService = orderService;
        this.userClient = userClient;
    }

    @PostMapping("/create")
    public ResultVO create(@Valid CreateOrderDTO createOrderDTO) {
        String token = createOrderDTO.getToken();
        String products = createOrderDTO.getProducts();
        // 调用用户服务，验证token
        UserBO userBO = userClient.checkToken(token);
        if (userBO == null)
            return ResultVO.fail("该Token不存在");
        // 将products解析成对象
        List<ProductDTO> productDTOList = JSON.parseArray(products, ProductDTO.class);
//        Gson gson = new Gson();
//        ProductDTO[] productDTOList = gson.fromJson(products, new TypeToken<ProductDTO[]>(){}.getType());
        // 调用OrderService服务创建订单
        ResultDTO resultDTO = orderService.create(userBO, productDTOList);
        if (!resultDTO.isSuccess())
            return ResultVO.fail("订单创建失败");
        return ResultVO.success(resultDTO.getData());
    }

}
