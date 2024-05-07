package com.example.stock.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.stock.dto.StockInfoDTO;
import com.example.stock.entity.StockInfoEntity;
import com.example.stock.vo.StockInfoVO;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 库存表
 * 
 * @author suheng
 * @email 
 * @date 2024-04-30 13:57:20
 * @version v1.1
 */
@Repository("stockInfoDao")
public interface StockInfoDao extends BaseMapper<StockInfoEntity> {

    List<StockInfoVO> queryStockInfoList(StockInfoDTO stockInfoDTO);

    List<StockInfoVO> queryStockDetailInfoVos(StockInfoDTO stockInfoDTO);

}
