package com.example.stock.service;


import com.baomidou.mybatisplus.service.IService;
import com.example.stock.dto.StockInfoDTO;
import com.example.stock.entity.StockDetailInfoEntity;
import com.example.stock.entity.StockInfoEntity;
import com.example.stock.vo.PageBean;

/**
 * 库存表
 *
 * @author suheng
 * @email 
 * @date 2024-04-30 13:57:20
 * @version v1.1
 */
public interface StockInfoService extends IService<StockInfoEntity> {


    PageBean queryStockInfoList(StockInfoDTO stockInfoDTO);


    PageBean queryStockDetailInfoVos(StockInfoDTO stockInfoDTO);

}

