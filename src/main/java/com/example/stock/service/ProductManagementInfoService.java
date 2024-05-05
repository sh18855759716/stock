package com.example.stock.service;


import com.example.stock.entity.ProductManagementInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 产品管理表
 *
 * @author suheng
 * @email
 * @date 2024-04-29 22:51:37
 * @version v1.1
 */
public interface ProductManagementInfoService {


    List<ProductManagementInfoEntity> test();


    /**
     * 模糊查询产品信息
     * @param productName
     * @return
     */
    List<ProductManagementInfoEntity> fuzzyQueryProductList(String productName);

    /**
     * 批量查询产品
     * @param productIdList
     * @return
     */
    Map<Long, ProductManagementInfoEntity> queryProductMap(List<Long> productIdList);



}

