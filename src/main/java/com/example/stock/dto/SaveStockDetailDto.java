package com.example.stock.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 保存订单 参数
 * @author yang.tao
 * @date 2024/05/01 10:28
 * @Version 1.0
 */
@Data
@ApiModel("保存/编辑订单 参数")
public class SaveStockDetailDto {


    /**
     * 产品id
     */
    private Long productId;

    private BigDecimal quantity;

}
