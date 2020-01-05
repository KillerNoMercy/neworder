package com.fan.neworderOrderServer.repository;

import com.fan.neworderOrderServer.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Auther: zhengfan
 * Date: 2020/1/4
 */
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
}
