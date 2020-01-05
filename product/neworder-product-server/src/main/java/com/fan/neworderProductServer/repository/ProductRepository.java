package com.fan.neworderProductServer.repository;

import com.fan.neworderProductServer.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Auther: zhengfan
 * Date: 2020/1/3
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findAllBySortIdAndStatus(String sortId, Integer status);
}
