package com.fan.neworderProductServer.repository;

import com.fan.neworderProductServer.model.ProductSort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Auther: zhengfan
 * Date: 2020/1/3
 */
@Repository
public interface ProductSortRepository extends JpaRepository<ProductSort, String> {
}
