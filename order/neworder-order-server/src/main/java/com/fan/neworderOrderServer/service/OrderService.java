package com.fan.neworderOrderServer.service;

import com.fan.neworderOrderServer.dto.ProductDTO;
import com.fan.neworderOrderServer.dto.ResultDTO;
import com.fan.neworderOrderServer.model.OrderDetail;
import com.fan.neworderOrderServer.model.OrderMaster;
import com.fan.neworderOrderServer.repository.OrderDetailRepository;
import com.fan.neworderOrderServer.repository.OrderRepository;
import com.fan.neworderProductClient.feign.ProductClient;
import com.fan.neworderProductCommon.in.UpdateStocksBO;
import com.fan.neworderProductCommon.out.ProductInfo;
import com.fan.neworderUserCommon.bo.UserBO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Auther: zhengfan
 * Date: 2020/1/3
 */
@Service
public class OrderService {

    private OrderRepository orderRepository;
    private ProductClient productClient;
    private OrderDetailRepository orderDetailRepository;

    public OrderService(OrderRepository orderRepository, ProductClient productClient, OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.productClient = productClient;
        this.orderDetailRepository = orderDetailRepository;
    }

    /**
     * 创建订单，维护库存
     *
     * @param userBO
     * @param productDTOList
     * @return
     */
    @Transactional
    public ResultDTO create(UserBO userBO, List<ProductDTO> productDTOList) {
        // 查询订单商品
        final List<String> ids = productDTOList.stream().map(p -> p.getId()).collect(Collectors.toList());
        List<ProductInfo> productInfoList = productClient.listByIds(ids);
        // 计算总价
        BigDecimal totalAmount = new BigDecimal(0);
        for (ProductInfo productInfo : productInfoList) {
            for (ProductDTO productDTO : productDTOList) {
                if (productDTO.getId().equals(productInfo.getId())) {
                    totalAmount = new BigDecimal(productInfo.getPrice()).multiply(new BigDecimal(productDTO.getCount())).add(totalAmount);
                }
            }
        }
        // 写入ordermaster
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setUserName(userBO.getName());
        orderMaster.setUserPhone(userBO.getPhone());
        orderMaster.setUserAddr(userBO.getAddr());
        String orderId = UUID.randomUUID().toString().substring(0, 10);
        orderMaster.setId(orderId);
        orderMaster.setStatus(0);
        orderMaster.setTotalAmount(totalAmount.doubleValue());
        orderMaster.setCreateTime(new Date());
        orderMaster.setUpdateTime(new Date());
        orderRepository.save(orderMaster);
        // 写入order detail
        this.saveOrderDetail(orderId, productDTOList, productInfoList);
        // 组装UpdateStockList
        List<Integer> counts = productDTOList.stream().map(p -> p.getCount()).collect(Collectors.toList());
        List<UpdateStocksBO> updateStocksBOList = this.assembleUpdateStockList(ids, counts);
        // 调用product扣库存服务
        productClient.updateStocks(updateStocksBOList);
        return ResultDTO.success(orderMaster);
    }

    private List<UpdateStocksBO> assembleUpdateStockList(List<String> ids, List<Integer> counts) {
        List<UpdateStocksBO> updateStocksBOList = new ArrayList<>();
        Iterator<String> idsIterator = ids.iterator();
        Iterator<Integer> countsIterator = counts.iterator();
        for (; idsIterator.hasNext(); ) {
            UpdateStocksBO updateStocksBO = new UpdateStocksBO();
            updateStocksBO.setId(idsIterator.next());
            updateStocksBO.setCount(countsIterator.next());
            updateStocksBOList.add(updateStocksBO);
        }
        return updateStocksBOList;
    }

    private void saveOrderDetail(String orderId, List<ProductDTO> productDTOList, List<ProductInfo> productInfoList) {
        Iterator<ProductDTO> productDTOIterator = productDTOList.iterator();
        Iterator<ProductInfo> productInfoIterator = productInfoList.iterator();
        for (; productDTOIterator.hasNext(); ) {
            OrderDetail orderDetail = new OrderDetail();
            ProductDTO productDTO = productDTOIterator.next();
            ProductInfo productInfo = productInfoIterator.next();
            orderDetail.setId(UUID.randomUUID().toString().substring(0, 10));
            orderDetail.setProductId(productDTO.getId());
            orderDetail.setOrderId(orderId);
            orderDetail.setCount(productDTO.getCount());
            orderDetail.setProductName(productInfo.getName());
            orderDetail.setProductPrice(productInfo.getPrice());
            orderDetail.setProductDetails(productInfo.getDetails());
            orderDetail.setCreateTime(new Date());
            orderDetail.setUpdateTime(new Date());
            orderDetailRepository.save(orderDetail);
        }
    }
}
