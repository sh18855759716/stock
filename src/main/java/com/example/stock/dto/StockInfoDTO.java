package com.example.stock.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockInfoDTO extends PageParam{

    private Long id;

    /**
     * 产品编号
     */
    @ApiModelProperty(value = "产品编号")
    private String productNum;
    /**
     * 产品名称
     */
    @ApiModelProperty(value = "产品名称")
    private String productName;


    /**
     *操作类型：1：入库 2：出库
     */
    private Integer operateType;


    /**
     * 销售订单编号
     */
    private String saleOrderNum;


    /**
     * 采购订单编号
     */
    private String purchaseOrderNum;

    /**
     * 分类
     */
    @ApiModelProperty(value = "分类")
    private String category;


}
