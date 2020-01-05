package com.fan.neworderOrderServer.repository;

import com.fan.neworderOrderServer.model.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Auther: zhengfan
 * Date: 2020/1/3
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderMaster, String> {
}
