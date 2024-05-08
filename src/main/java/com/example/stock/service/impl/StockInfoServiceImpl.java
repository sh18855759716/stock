package com.example.stock.service.impl;


import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.stock.dao.StockDetailInfoDao;
import com.example.stock.dao.StockInfoDao;
import com.example.stock.dto.StockInfoDTO;
import com.example.stock.entity.StockDetailInfoEntity;
import com.example.stock.entity.StockInfoEntity;
import com.example.stock.service.StockInfoService;
import com.example.stock.vo.PageBean;
import com.example.stock.vo.ProductManagementInfoVO;
import com.example.stock.vo.StockInfoVO;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * 库存表
 *
 * @author suheng
 * @email 
 * @date 2024-04-30 13:57:20
 * @version v1.1
 */
@Service("stockInfoService")
public class StockInfoServiceImpl extends ServiceImpl<StockInfoDao, StockInfoEntity> implements StockInfoService {
	@Autowired
	private StockInfoDao stockInfoDao;

	@Override
	public PageBean queryStockInfoList(StockInfoDTO stockInfoDTO) {
		PageHelper.startPage(stockInfoDTO.getPageNum(),stockInfoDTO.getPageSize());
		List<StockInfoVO> stockInfoVOList = stockInfoDao.queryStockInfoList(stockInfoDTO);
		PageInfo<StockInfoVO> stockInfoVOPageInfo = new PageInfo<>(stockInfoVOList);
		return new PageBean(stockInfoVOPageInfo.getTotal(),stockInfoVOPageInfo.getList());
	}

	@Override
	public PageBean queryStockDetailInfoVos(StockInfoDTO stockInfoDTO) {
		PageHelper.startPage(stockInfoDTO.getPageNum(),stockInfoDTO.getPageSize());
		List<StockInfoVO> stockInfoVOList = stockInfoDao.queryStockDetailInfoVos(stockInfoDTO);
		PageInfo<StockInfoVO> stockInfoVOPageInfo = new PageInfo<>(stockInfoVOList);
		return new PageBean(stockInfoVOPageInfo.getTotal(),stockInfoVOPageInfo.getList());

	}
}
