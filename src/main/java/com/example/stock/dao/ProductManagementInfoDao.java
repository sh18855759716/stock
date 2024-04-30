package com.example.stock.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.stock.entity.ProductManagementInfoEntity;
import org.springframework.stereotype.Repository;


/**
 * 产品管理表
 * 
 * @author suheng
 * @email 
 * @date 2024-04-29 22:51:37
 * @version v1.1
 */
@Repository("productManagementInfoDao")
public interface ProductManagementInfoDao extends BaseMapper<ProductManagementInfoEntity> {
	
}
