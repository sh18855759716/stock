package com.example.stock.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.example.stock.entity.OrderInfoEntity;
import com.example.stock.entity.OrderProductInfoEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author yang.tao
 * @date 2024/05/01 10:28
 * @Version 1.0
 */
@Data
public class OrderInfoVo extends OrderInfoEntity {



    @ApiModelProperty(value = "已出库/出库单号")
    @TableField(exist = false)
    private String stockNum;

    @ApiModelProperty("订单产品明细列表")
    private List<OrderProductInfoEntity> orderProductInfoEntitieList;
}
