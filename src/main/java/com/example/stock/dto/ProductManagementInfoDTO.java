package com.example.stock.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

@Data
@Builder
public class ProductManagementInfoDTO extends PageParam{

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
     * 分类
     */
    @ApiModelProperty(value = "分类")
    private String category;

    /**
     * 规格
     */
    @ApiModelProperty(value = "规格")
    private String specification;

    /**
     * 单位
     */
    private String unit;

    /**
     * 仓库
     */
    @ApiModelProperty(value = "仓库")
    private String store;

    /**
     * 上下架状态 1：上架  2：下架
     */
    @ApiModelProperty(value = "上下架状态 1：上架  2：下架")
    private Integer shelfStatus;
}
