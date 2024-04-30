package com.example.stock.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.stock.entity.OrderProductInfoEntity;
import org.springframework.stereotype.Repository;


/**
 * 订单产品关联表
 * 
 * @author suheng
 * @email 
 * @date 2024-04-30 13:57:20
 * @version v1.1
 */
@Repository("orderProductInfoDao")
public interface OrderProductInfoDao extends BaseMapper<OrderProductInfoEntity> {
	
}
