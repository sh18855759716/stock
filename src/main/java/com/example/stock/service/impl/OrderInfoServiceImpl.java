package com.example.stock.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.stock.dao.OrderInfoDao;
import com.example.stock.dto.QueryOrderInfoPageDto;
import com.example.stock.dto.SaveOrderInfoDto;
import com.example.stock.entity.OrderInfoEntity;
import com.example.stock.entity.OrderProductInfoEntity;
import com.example.stock.entity.ProductManagementInfoEntity;
import com.example.stock.service.OrderInfoService;
import com.example.stock.service.OrderProductInfoService;
import com.example.stock.service.ProductManagementInfoService;
import com.example.stock.vo.ApiUtil;
import com.example.stock.vo.BaseApi;
import com.example.stock.vo.OrderInfoVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 订单表
 *
 * @author suheng
 * @version v1.1
 * @email
 * @date 2024-04-30 13:57:20
 */
@Service("orderInfoService")
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoDao, OrderInfoEntity> implements OrderInfoService {

    @Resource
    OrderProductInfoService orderProductInfoService;

    @Resource
    ProductManagementInfoService productManagementInfoService;

    @Override
    public BaseApi<Page<OrderInfoEntity>> queryOrderInfoPage(Integer orderType, QueryOrderInfoPageDto dto) {
        BaseApi check = dto.check();
        if (check != null) {
            return check;
        }
        Date orderTime = null;

        if (StringUtils.isNotBlank(dto.getOrderTime())) {
            orderTime = DateUtil.parse(dto.getOrderTime());
        }

        Page<OrderInfoEntity> orderInfoEntityPage = this.selectPage(new Page<>(dto.getCurrent(), dto.getSize()), new EntityWrapper<OrderInfoEntity>()
                .eq("order_type", orderType)
                .eq(StringUtils.isNotBlank(dto.getOrderNum()), "order_num", dto.getOrderNum())
                .like(StringUtils.isNotBlank(dto.getOrderName()), "order_name", dto.getOrderName())
                .between(orderTime != null, "order_time", getFirstDate(orderTime), getLastDate(orderTime))
                .eq("is_del", 0));

        BaseApi<Page<OrderInfoEntity>> objectBaseApi = new BaseApi<>();
        objectBaseApi.success(orderInfoEntityPage);
        return objectBaseApi;
    }

    @Override
    @Transactional
    public BaseApi<String> saveOrderInfo(Integer orderType, SaveOrderInfoDto dto) {
        BaseApi<String> res = new BaseApi<>();
        Long id = dto.getId();
        Date now = new Date();
        String resStr = dto.getOrderNum();

        OrderInfoEntity entity = new OrderInfoEntity();
        // 新增
        if (id == null) {
            BaseApi api = dto.saveCheck();
            if (api != null) {
                return api;
            }
            String prefix = orderType == 1 ? "PO" : "XS";
            String yyyyMMdd = DateUtil.format(new Date(), "yyyyMMdd");
            int count = selectCount(new EntityWrapper<OrderInfoEntity>().like("order_num", prefix + yyyyMMdd + "%"));
            count++;
            String orderNum = prefix + yyyyMMdd + String.format("%03d", count);
            resStr = orderNum;
            BeanUtil.copyProperties(dto, entity, CopyOptions.create().ignoreError());
            entity.setOrderType(orderType);
            entity.setOrderNum(orderNum);
            entity.setCreateTime(now);
            entity.setUpdateTime(now);
            insert(entity);
            id = entity.getId();
        }
        BaseApi api = dto.updateCheck();
        if (api != null) {
            return api;
        }
        entity = selectById(id);
        if (entity == null) {
            res.noData();
            return res;
        }

        //orderProductInfoService.delete(new EntityWrapper<OrderProductInfoEntity>()
        //		.eq("order_info_id", id));
        // 订单明细
        List<OrderProductInfoEntity> orderProductInfoEntitieList = dto.getOrderProductInfoEntitieList();
        BigDecimal productNumber = orderProductInfoEntitieList.stream().map(OrderProductInfoEntity::getProductNumber).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal money = orderProductInfoEntitieList.stream().map(OrderProductInfoEntity::getProductMoney).reduce(BigDecimal.ZERO, BigDecimal::add);

        entity.setOrderMoney(money);
        entity.setOrderNumber(productNumber);
        entity.setUpdateTime(now);
        updateById(entity);

        for (OrderProductInfoEntity orderProductInfoEntity : orderProductInfoEntitieList) {
            Long infoId = orderProductInfoEntity.getId();
            if (infoId == null) {
                // 必然为null
                orderProductInfoEntity.setId(null);
                orderProductInfoEntity.setOrderInfoId(entity.getId());
                orderProductInfoEntity.setCreateTime(now);
                orderProductInfoEntity.setUpdateTime(now);
                orderProductInfoService.insert(orderProductInfoEntity);
            } else {
                OrderProductInfoEntity dbInfoEntity = orderProductInfoService.selectById(infoId);
                dbInfoEntity.setProductNumber(orderProductInfoEntity.getProductNumber());
                dbInfoEntity.setProductMoney(orderProductInfoEntity.getProductMoney());
                dbInfoEntity.setUpdateTime(now);
                orderProductInfoService.updateById(dbInfoEntity);
            }
        }

        res.success(resStr);
        return res;
    }

    @Override
    public BaseApi<OrderInfoVo> queryOrderInfo(Long id) {
        BaseApi<OrderInfoVo> res = new BaseApi<>();
        OrderInfoEntity entity = selectById(id);
        List<OrderProductInfoEntity> orderProductInfoEntities = orderProductInfoService.selectList(new EntityWrapper<OrderProductInfoEntity>()
                .eq("order_info_id", id));

        OrderInfoVo vo = new OrderInfoVo();
        BeanUtil.copyProperties(entity, vo, CopyOptions.create().ignoreError());

        // 获取产品明细
        List<Long> productIdList = orderProductInfoEntities.stream().map(OrderProductInfoEntity::getProductInfoId).collect(Collectors.toList());
        Map<Long, ProductManagementInfoEntity> productManagementInfoEntityMap = productManagementInfoService.queryProductMap(productIdList);
        for (OrderProductInfoEntity orderProductInfoEntity : orderProductInfoEntities) {
            Long productInfoId = orderProductInfoEntity.getProductInfoId();
            if(productManagementInfoEntityMap.containsKey(productInfoId)){
                ProductManagementInfoEntity productManagementInfoEntity = productManagementInfoEntityMap.get(productInfoId);
                orderProductInfoEntity.setProductNum(productManagementInfoEntity.getProductNum());
                orderProductInfoEntity.setProductName(productManagementInfoEntity.getProductName());
                orderProductInfoEntity.setSpecification(productManagementInfoEntity.getSpecification());
            }

        }

        vo.setOrderProductInfoEntitieList(orderProductInfoEntities);

        res.success(vo);
        return res;
    }

    @Override
    public BaseApi<?> delOrderProduct(Long id) {
        orderProductInfoService.deleteById(id);
        return ApiUtil.addRightData("成功", null);
    }

    @Override
    public BaseApi<?> saveInOrOutbound(Long id) {
        OrderInfoEntity entity = selectById(id);
        // TODO 操作出库入库动作,等需求回复
        return null;
    }

    /**
     * 获取指定时间凌晨的时间
     * @param date
     * @return
     */
    public static Date getFirstDate(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }


    /**
     * 获取指定时间23:59:59的时间
     * @param date
     * @return
     */
    public static Date getLastDate(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }
}
