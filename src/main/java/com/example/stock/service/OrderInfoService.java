package com.example.stock.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.example.stock.dto.QueryOrderInfoPageDto;
import com.example.stock.dto.SaveOrderInfoDto;
import com.example.stock.entity.OrderInfoEntity;
import com.example.stock.vo.BaseApi;
import com.example.stock.vo.OrderInfoVo;
import com.example.stock.vo.PageVo;

/**
 * 订单表
 *
 * @author suheng
 * @email
 * @date 2024-04-30 13:57:20
 * @version v1.1
 */
public interface OrderInfoService extends IService<OrderInfoEntity> {

    /**
     * 订单查询
     * @param dto
     * @return
     */
    BaseApi<PageVo<OrderInfoEntity>> queryOrderInfoPage(Integer orderType, QueryOrderInfoPageDto dto);

    /**
     * 保存订单信息
     * @param orderType
     * @param dto
     * @return
     */
    BaseApi<String> saveOrderInfo(Integer orderType, SaveOrderInfoDto dto);


    /**
     * 查询订单详情
     * @param id
     * @return
     */
    BaseApi<OrderInfoVo> queryOrderInfo(Long id);

    /**
     * 删除订单明细行
     * @param id
     * @return
     */
    BaseApi<?> delOrderProduct(Long id);

    /**
     * 操作出库入库
     * @param id
     * @return
     */
    BaseApi<?> saveInOrOutbound(Long id);
}

