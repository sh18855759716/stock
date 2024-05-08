package com.example.stock.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.stock.dao.OrderInfoDao;
import com.example.stock.dto.QueryOrderInfoPageDto;
import com.example.stock.dto.SaveOrderInfoDto;
import com.example.stock.dto.SaveStockDetailDto;
import com.example.stock.dto.SaveStockDto;
import com.example.stock.entity.OrderInfoEntity;
import com.example.stock.entity.OrderProductInfoEntity;
import com.example.stock.entity.ProductManagementInfoEntity;
import com.example.stock.entity.StockDetailInfoEntity;
import com.example.stock.entity.StockInfoEntity;
import com.example.stock.service.OrderInfoService;
import com.example.stock.service.OrderProductInfoService;
import com.example.stock.service.ProductManagementInfoService;
import com.example.stock.service.StockDetailInfoService;
import com.example.stock.service.StockInfoService;
import com.example.stock.util.IdNumberUtil;
import com.example.stock.vo.ApiUtil;
import com.example.stock.vo.BaseApi;
import com.example.stock.vo.OrderInfoVo;
import com.example.stock.vo.PageVo;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    @Resource
    StockDetailInfoService stockDetailInfoService;

    @Resource
    StockInfoService stockInfoService;

    @Autowired
    private IdNumberUtil idNumberUtil;

    @Override
    public BaseApi<PageVo<OrderInfoEntity>> queryOrderInfoPage(Integer orderType, QueryOrderInfoPageDto dto) {
        BaseApi check = dto.check();
        if (check != null) {
            return check;
        }
        Date orderTime = null;

        if (StringUtils.isNotBlank(dto.getOrderTime())) {
            orderTime = DateUtil.parse(dto.getOrderTime());
        }
        PageHelper.startPage(dto.getCurrent(), dto.getSize());
        List<OrderInfoEntity> orderInfoEntities = this.selectList(new EntityWrapper<OrderInfoEntity>()
                .eq("order_type", orderType)
                .eq(StringUtils.isNotBlank(dto.getOrderNum()), "order_num", dto.getOrderNum())
                .like(StringUtils.isNotBlank(dto.getOrderName()), "order_name", dto.getOrderName())
                .between(orderTime != null, "order_time", getFirstDate(orderTime), getLastDate(orderTime))
                .eq("is_del", 0));
        PageInfo<OrderInfoEntity> orderInfoEntitiesPageInfo = new PageInfo<>(orderInfoEntities);
        BaseApi<PageVo<OrderInfoEntity>> objectBaseApi = new BaseApi<>();
        objectBaseApi.success(new PageVo<OrderInfoEntity>(dto.getCurrent(), dto.getSize(), orderInfoEntitiesPageInfo.getTotal(), orderInfoEntitiesPageInfo.getList()));
        return objectBaseApi;
    }

    @Override
    @Transactional
    public BaseApi<String> saveOrderInfo(Integer orderType, SaveOrderInfoDto dto) {
        BaseApi<String> res = new BaseApi<>();
        Long id = dto.getId();
        Date now = new Date();
        String resStr = dto.getOrderNum();

        // 订单明细
        List<OrderProductInfoEntity> orderProductInfoEntitieList = dto.getOrderProductInfoEntitieList();
        List<Long> collect = orderProductInfoEntitieList.stream().map(OrderProductInfoEntity::getProductInfoId).distinct().collect(Collectors.toList());
        if (collect.size() != orderProductInfoEntitieList.size()) {
            res.paramError("订单的产品不允许重复", null);
            return res;
        }


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
    @Transactional
    public BaseApi<?> saveInOrOutbound(SaveStockDto dto) {
        BaseApi<?> res = new BaseApi<>();
        OrderInfoEntity entity = selectById(dto.getId());
        String message = entity.getOrderType() == 1 ? "入库" : "出库";
        List<SaveStockDetailDto> detailDtoList = dto.getDetailDtoList();
        List<OrderProductInfoEntity> orderProductInfoEntities = orderProductInfoService.selectList(new EntityWrapper<OrderProductInfoEntity>()
                .eq("order_info_id", dto.getId()));

        Map<Long, BigDecimal> orderProductMap = orderProductInfoEntities.stream().collect(Collectors.toMap(OrderProductInfoEntity::getProductInfoId, OrderProductInfoEntity::getProductNumber));


        Map<Long, BigDecimal> productQuantityMap = stockDetailInfoService.queryProductQuantity(entity.getOrderType(), entity.getOrderNum());
        for (SaveStockDetailDto saveStockDetailDto : detailDtoList) {
            Long productId = saveStockDetailDto.getProductId();
            BigDecimal quantity = saveStockDetailDto.getQuantity();
            BigDecimal oldQuantity = productQuantityMap.get(productId);
            BigDecimal total = orderProductMap.get(productId);
            total = Optional.ofNullable(total).orElse(BigDecimal.ZERO);
            if (total.compareTo(NumberUtil.add(quantity, oldQuantity)) < 0) {
                res.paramError(message + "数量超过订单总量", null);
                return res;
            }
        }

        for (SaveStockDetailDto saveStockDetailDto : detailDtoList) {
            StockDetailInfoEntity stockDetailInfo = new StockDetailInfoEntity();
            stockDetailInfo.setProductInfoId(saveStockDetailDto.getProductId());
            stockDetailInfo.setSaleOrderNum(entity.getOrderType() == 1 ? null : entity.getOrderNum());
            stockDetailInfo.setPurchaseOrderNum(entity.getOrderType() == 1 ? entity.getOrderNum() : null);
            stockDetailInfo.setOperateType(entity.getOrderType());
            stockDetailInfo.setStockNum(dto.getStockNum());
            stockDetailInfo.setOperateNumber(saveStockDetailDto.getQuantity());
            stockDetailInfo.setCreateTime(new Date());
            stockDetailInfo.setUpdateTime(new Date());
            stockDetailInfoService.insert(stockDetailInfo);

            BigDecimal direction = entity.getOrderType() == 1 ? new BigDecimal("1") : new BigDecimal("-1");
            BigDecimal quantity = NumberUtil.mul(saveStockDetailDto.getQuantity(), direction);
            StockInfoEntity stockInfoEntity = stockInfoService.selectOne(new EntityWrapper<StockInfoEntity>()
                    .eq("product_info_id", saveStockDetailDto.getProductId())
                    .eq("is_del", 0));
            if (stockInfoEntity == null) {
                stockInfoEntity = new StockInfoEntity();
                stockInfoEntity.setProductInfoId(saveStockDetailDto.getProductId());
                stockInfoEntity.setCreateTime(new Date());
            }
            stockInfoEntity.setStockNumber(NumberUtil.add(quantity, stockInfoEntity.getStockNumber()));
            stockInfoEntity.setUpdateTime(new Date());
            stockInfoService.insertOrUpdate(stockInfoEntity);
        }
        res.success(null);
        return res;
    }

    @Override
    public BaseApi<OrderInfoVo> queryOrderSerialInfo(Long id) {
        BaseApi<OrderInfoVo> res = new BaseApi<>();
        OrderInfoEntity entity = selectById(id);
        Integer orderType = entity.getOrderType();
        List<OrderProductInfoEntity> orderProductInfoEntities = orderProductInfoService.selectList(new EntityWrapper<OrderProductInfoEntity>()
                .eq("order_info_id", id));

        OrderInfoVo vo = new OrderInfoVo();
        BeanUtil.copyProperties(entity, vo, CopyOptions.create().ignoreError());
        vo.setStockNum(idNumberUtil.getStockNumber(orderType));
        Map<Long, BigDecimal> longBigDecimalMap = stockDetailInfoService.queryProductQuantity(orderType, entity.getOrderNum());
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
            if (longBigDecimalMap.containsKey(productInfoId)) {
                BigDecimal bigDecimal = longBigDecimalMap.get(productInfoId);
                orderProductInfoEntity.setQuantity(bigDecimal);
            }
        }

        vo.setOrderProductInfoEntitieList(orderProductInfoEntities);

        res.success(vo);
        return res;
    }

    @Override
    public List<ProductManagementInfoEntity> fuzzyQueryOrderProductList(Long id, String productName) {

        List<OrderProductInfoEntity> orderProductInfoEntities = orderProductInfoService.selectList(new EntityWrapper<OrderProductInfoEntity>()
                .eq("order_info_id", id));
        List<Long> collect = orderProductInfoEntities.stream().map(OrderProductInfoEntity::getProductInfoId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(collect)) {
            return new ArrayList<>();
        }
        return productManagementInfoService.fuzzyQueryProductList(collect, productName);
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
