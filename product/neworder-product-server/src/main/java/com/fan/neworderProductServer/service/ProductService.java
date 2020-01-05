package com.fan.neworderProductServer.service;

import com.fan.neworderProductCommon.in.UpdateStocksBO;
import com.fan.neworderProductCommon.out.ProductInfo;
import com.fan.neworderProductServer.dto.ResultDTO;
import com.fan.neworderProductServer.model.Product;
import com.fan.neworderProductServer.model.ProductSort;
import com.fan.neworderProductServer.repository.ProductRepository;
import com.fan.neworderProductServer.repository.ProductSortRepository;
import com.fan.neworderProductServer.vo.ProductVO;
import com.fan.neworderProductServer.vo.SortAndProductVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Auther: zhengfan
 * Date: 2020/1/3
 */
@Service
public class ProductService {

    private ProductRepository productRepository;
    private ProductSortRepository sortRepository;

    public ProductService(ProductRepository productRepository,
                          ProductSortRepository sortRepository) {
        this.productRepository = productRepository;
        this.sortRepository = sortRepository;
    }

    public ResultDTO list() {
        List<SortAndProductVO> sortAndProductVOList = new ArrayList<>();

        List<ProductSort> allSorts = sortRepository.findAll();
        for (ProductSort productSort : allSorts) {
            SortAndProductVO sortAndProductVO = new SortAndProductVO();
            sortAndProductVO.setSortName(productSort.getName());
            sortAndProductVO.setSlogan(productSort.getSlogan());
            List<Product> ProductsBySortId = productRepository.findAllBySortIdAndStatus(productSort.getId(), 0);
            List<ProductVO> products = new ArrayList<>();
            for (Product product : ProductsBySortId) {
                ProductVO productVO = new ProductVO();
                BeanUtils.copyProperties(product, productVO);
                products.add(productVO);
            }
            sortAndProductVO.setProducts(products);
            sortAndProductVOList.add(sortAndProductVO);
        }

        return ResultDTO.success(sortAndProductVOList);
    }

    public List<ProductInfo> listByIds(List<String> ids) {
        List<ProductInfo> productInfos = new ArrayList<>();
        for(String id : ids) {
            Optional<Product> optionalProduct = productRepository.findById(id);
            if (!optionalProduct.isPresent())
                throw new RuntimeException("该商品不存在");
            Product product = optionalProduct.get();
            ProductInfo productInfo = new ProductInfo();
            BeanUtils.copyProperties(product, productInfo);
            productInfos.add(productInfo);
        }
        return productInfos;
    }

    @Transactional
    public void updateStocks(List<UpdateStocksBO> updateStocksBOList) {
        for(UpdateStocksBO updateStocksBO : updateStocksBOList) {
            Optional<Product> byId = productRepository.findById(updateStocksBO.getId());
            if (!byId.isPresent())
                throw new RuntimeException("该商品不存在");
            Product product = byId.get();
            Integer newStock = product.getStock() - updateStocksBO.getCount();
            if (newStock < 0)
                throw new RuntimeException("库存不足");
            product.setStock(newStock);
            productRepository.save(product);
        }
    }
}
