package com.example.stock.vo;

import com.baomidou.mybatisplus.annotations.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ProductManagementInfoVO {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    @TableId
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
     * 上下架状态 1：上架  2：下架
     */
    @ApiModelProperty(value = "上下架状态 1：上架  2：下架")
    private Integer shelfStatus;
    /**
     * 仓库
     */
    @ApiModelProperty(value = "仓库")
    private String store;

    @ApiModelProperty(value = "单位")
    private String unit;
    /**
     * 是否删除0：未删除；1：删除
     */
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

}
