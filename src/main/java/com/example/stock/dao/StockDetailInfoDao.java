package com.example.stock.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.stock.entity.StockDetailInfoEntity;
import org.springframework.stereotype.Repository;


/**
 * 库存明细表
 * 
 * @author suheng
 * @email 
 * @date 2024-04-30 13:57:20
 * @version v1.1
 */
@Repository("stockDetailInfoDao")
public interface StockDetailInfoDao extends BaseMapper<StockDetailInfoEntity> {
	
}
