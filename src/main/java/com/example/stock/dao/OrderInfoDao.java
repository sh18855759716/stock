package com.example.stock.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.stock.entity.OrderInfoEntity;
import org.springframework.stereotype.Repository;


/**
 * 订单表
 * 
 * @author suheng
 * @email 
 * @date 2024-04-30 13:57:20
 * @version v1.1
 */
@Repository("orderInfoDao")
public interface OrderInfoDao extends BaseMapper<OrderInfoEntity> {
	
}
