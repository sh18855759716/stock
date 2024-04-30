package com.example.stock.service.impl;


import com.example.stock.dao.OrderInfoDao;
import com.example.stock.service.OrderInfoService;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;



/**
 * 订单表
 *
 * @author suheng
 * @email 
 * @date 2024-04-30 13:57:20
 * @version v1.1
 */
@Service("orderInfoService")
public class OrderInfoServiceImpl implements OrderInfoService {
	@Autowired
	private OrderInfoDao orderInfoDao;

}
