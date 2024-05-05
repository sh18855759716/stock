package com.example.stock.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

@Data
public class ProductManagementInfoDTO extends PageParam{

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
     * 分类
     */
    @ApiModelProperty(value = "分类")
    private String category;

    /**
     * 上下架状态 1：上架  2：下架
     */
    @ApiModelProperty(value = "上下架状态 1：上架  2：下架")
    private Integer shelfStatus;
}
