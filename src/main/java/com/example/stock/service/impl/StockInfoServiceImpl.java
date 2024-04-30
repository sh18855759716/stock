package com.example.stock.service.impl;


import com.example.stock.dao.StockInfoDao;
import com.example.stock.service.StockInfoService;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;



/**
 * 库存表
 *
 * @author suheng
 * @email 
 * @date 2024-04-30 13:57:20
 * @version v1.1
 */
@Service("stockInfoService")
public class StockInfoServiceImpl implements StockInfoService {
	@Autowired
	private StockInfoDao stockInfoDao;

}
