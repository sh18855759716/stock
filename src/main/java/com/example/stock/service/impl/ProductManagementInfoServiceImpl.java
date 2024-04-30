package com.example.stock.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.stock.dao.ProductManagementInfoDao;
import com.example.stock.entity.ProductManagementInfoEntity;
import com.example.stock.service.ProductManagementInfoService;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


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
}
