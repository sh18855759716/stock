package com.example.stock.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * 保存订单 参数
 * @author yang.tao
 * @date 2024/05/01 10:28
 * @Version 1.0
 */
@Data
@ApiModel("保存/编辑订单 参数")
public class SaveStockDto   {

    /**
     * 出入库编号
     */
    private String stockNum;

    /**
     * id
     */
    private Long id;

    /**
     * 规格信息
     */
    private List<SaveStockDetailDto> detailDtoList;
}
