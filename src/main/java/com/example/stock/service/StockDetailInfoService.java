package com.example.stock.service;


import com.baomidou.mybatisplus.service.IService;
import com.example.stock.entity.OrderProductInfoEntity;
import com.example.stock.entity.StockDetailInfoEntity;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 库存明细表
 *
 * @author suheng
 * @email 
 * @date 2024-04-30 13:57:20
 * @version v1.1
 */
public interface StockDetailInfoService extends IService<StockDetailInfoEntity> {

    /**
     * 查询订单出入库数量
     * @param type
     * @param documentNo
     * @return
     */
    Map<Long, BigDecimal> queryProductQuantity(Integer type, String documentNo);




}

