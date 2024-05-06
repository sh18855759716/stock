package com.example.stock.service;


import com.example.stock.dto.ProductManagementInfoDTO;
import com.example.stock.entity.ProductManagementInfoEntity;
import com.example.stock.vo.PageBean;
import com.example.stock.vo.ProductManagementInfoVO;

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



    /**
     * 查询产品列表
     * @param productManagementInfoDTO
     * @return
     */
    PageBean queryProductInfoList(ProductManagementInfoDTO productManagementInfoDTO);

    void updateShelfStatus(Long id, Integer shelfStatus);


    ProductManagementInfoVO getById(Long id);

    Long addOrUpdateProduct(ProductManagementInfoDTO productManagementInfoDTO);

}

