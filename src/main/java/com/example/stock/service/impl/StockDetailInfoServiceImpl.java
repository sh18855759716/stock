package com.example.stock.service.impl;


import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.stock.dao.OrderInfoDao;
import com.example.stock.dao.StockDetailInfoDao;
import com.example.stock.entity.OrderInfoEntity;
import com.example.stock.entity.StockDetailInfoEntity;
import com.example.stock.service.StockDetailInfoService;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.sql.Wrapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 库存明细表
 *
 * @author suheng
 * @email 
 * @date 2024-04-30 13:57:20
 * @version v1.1
 */
@Service("stockDetailInfoService")
public class StockDetailInfoServiceImpl extends ServiceImpl<StockDetailInfoDao, StockDetailInfoEntity> implements StockDetailInfoService {
	@Autowired
	private StockDetailInfoDao stockDetailInfoDao;


	@Override
	public Map<Long, BigDecimal> queryProductQuantity(Integer type, String documentNo) {
		List<StockDetailInfoEntity> stockDetailInfoEntities = stockDetailInfoDao.selectList(new EntityWrapper<StockDetailInfoEntity>()
				.eq("operate_type", type)
				.eq(type == 1, "purchase_order_num", documentNo)
				.eq(type == 2, "sale_order_num", documentNo));

		Map<Long, List<StockDetailInfoEntity>> collect = stockDetailInfoEntities.stream().collect(Collectors.groupingBy(StockDetailInfoEntity::getProductInfoId));
		Map<Long, BigDecimal> reuslt = new HashMap<>();
		collect.forEach((key,val)->{
			reuslt.put(key, val.stream().map(StockDetailInfoEntity::getOperateNumber).reduce(BigDecimal.ZERO, NumberUtil::add));
		});

		return reuslt;
	}
}
