package com.example.stock.service.impl;


import com.example.stock.dao.OrderProductInfoDao;
import com.example.stock.service.OrderProductInfoService;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;



/**
 * 订单产品关联表
 *
 * @author suheng
 * @email 
 * @date 2024-04-30 13:57:20
 * @version v1.1
 */
@Service("orderProductInfoService")
public class OrderProductInfoServiceImpl implements OrderProductInfoService {
	@Autowired
	private OrderProductInfoDao orderProductInfoDao;

}
