package com.example.stock.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.stock.dao.OrderProductInfoDao;
import com.example.stock.entity.OrderProductInfoEntity;
import com.example.stock.service.OrderProductInfoService;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * 订单产品关联表
 *
 * @author suheng
 * @version v1.1
 * @email
 * @date 2024-04-30 13:57:20
 */
@Service("orderProductInfoService")
public class OrderProductInfoServiceImpl extends ServiceImpl<OrderProductInfoDao, OrderProductInfoEntity> implements OrderProductInfoService {
    @Autowired
    private OrderProductInfoDao orderProductInfoDao;

}
