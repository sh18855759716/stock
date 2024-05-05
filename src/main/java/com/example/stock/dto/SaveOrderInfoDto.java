package com.example.stock.dto;

import com.alibaba.excel.util.StringUtils;
import com.example.stock.entity.OrderInfoEntity;
import com.example.stock.entity.OrderProductInfoEntity;
import com.example.stock.vo.BaseApi;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * 保存订单 参数
 * @author yang.tao
 * @date 2024/05/01 10:28
 * @Version 1.0
 */
@Data
@ApiModel("保存/编辑订单 参数")
public class SaveOrderInfoDto extends OrderInfoEntity {

    @ApiModelProperty("订单产品明细列表")
    private List<OrderProductInfoEntity> orderProductInfoEntitieList;

    public BaseApi saveCheck(){
        BaseApi api = new BaseApi();
        if (StringUtils.isBlank(getOrderName())) {
            api.paramError("请填写订单名称", null);
            return api;
        }
        if (StringUtils.isBlank(getSignName())) {
            api.paramError("请填写签订人", null);
            return api;
        }
        if (getOrderTime() == null) {
            api.paramError("请填写签订日期", null);
            return api;
        }
        //if (getOrderMoney() == null) {
        //    api.paramError("请填写订单金额", null);
        //    return api;
        //}
        //if (getOrderMoney().compareTo(BigDecimal.ZERO) <= 0) {
        //    api.paramError("订单金额必须大于0", null);
        //    return api;
        //}
        if (CollectionUtils.isEmpty(orderProductInfoEntitieList)) {
            api.paramError("请添加订单规格，订单规格信息最少一条", null);
            return api;
        }
        //for (OrderProductInfoEntity orderProductInfoEntity : orderProductInfoEntitieList) {
        //    BaseApi infoAPi = orderProductInfoEntity.saveCheck();
        //    if (infoAPi != null) {
        //        return infoAPi;
        //    }
        //}

        return null;
    }

    public BaseApi updateCheck(){
        //BaseApi api = new BaseApi();
        //if (getOrderMoney() == null) {
        //    api.paramError("请填写订单金额", null);
        //    return api;
        //}
        //if (getOrderMoney().compareTo(BigDecimal.ZERO) <= 0) {
        //    api.paramError("订单金额必须大于0", null);
        //    return api;
        //}

        for (OrderProductInfoEntity orderProductInfoEntity : orderProductInfoEntitieList) {
            Long infoId = orderProductInfoEntity.getId();
            if (infoId == null) {
                BaseApi infoAPi = orderProductInfoEntity.saveCheck();
                if (infoAPi != null) {
                    return infoAPi;
                }
            } else {
                BaseApi infoAPi = orderProductInfoEntity.updateCheck();
                if (infoAPi != null) {
                    return infoAPi;
                }
            }
        }
        return null;
    }

}
