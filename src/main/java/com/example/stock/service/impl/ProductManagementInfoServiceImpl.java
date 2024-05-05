package com.example.stock.service.impl;


import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.stock.dao.ProductManagementInfoDao;
import com.example.stock.entity.ProductManagementInfoEntity;
import com.example.stock.service.ProductManagementInfoService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 产品管理表
 *
 * @author suheng
 * @email
 * @date 2024-04-29 22:51:37
 * @version v1.1
 */
@Service("productManagementInfoService")
public class ProductManagementInfoServiceImpl extends ServiceImpl<ProductManagementInfoDao,ProductManagementInfoEntity> implements ProductManagementInfoService {
    @Autowired
    private ProductManagementInfoDao productManagementInfoDao;

    @Override
    public List<ProductManagementInfoEntity> test() {
        return this.selectList(new EntityWrapper<>());
    }

    @Override
    public List<ProductManagementInfoEntity> fuzzyQueryProductList(String productName) {
        // 模糊查询拿五十条
        return this.selectPage(new Page<>(1, 50), new EntityWrapper<ProductManagementInfoEntity>()
                .like(StringUtils.isNotBlank(productName), "product_name", productName)
                .eq("is_del", 0)).getRecords();
    }

    @Override
    public Map<Long, ProductManagementInfoEntity> queryProductMap(List<Long> productIdList) {
        if (CollectionUtils.isEmpty(productIdList)) {
            return new HashMap<>();
        }
        return this.selectList(new EntityWrapper<ProductManagementInfoEntity>()
                .in("id", productIdList)
                .eq("is_del", 0)).stream().collect(Collectors.toMap(ProductManagementInfoEntity::getId, v -> v, (v1, v2) -> v1));

    }
}
