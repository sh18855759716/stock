package com.example.stock.service.impl;


import com.example.stock.dao.StockDetailInfoDao;
import com.example.stock.service.StockDetailInfoService;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;



/**
 * 库存明细表
 *
 * @author suheng
 * @email 
 * @date 2024-04-30 13:57:20
 * @version v1.1
 */
@Service("stockDetailInfoService")
public class StockDetailInfoServiceImpl implements StockDetailInfoService {
	@Autowired
	private StockDetailInfoDao stockDetailInfoDao;

}
