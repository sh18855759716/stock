package com.example.stock.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.example.stock.util.Long2StringSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.io.Serializable;
/**
 * 产品管理表
 *
 * @author suheng
 * @email
 * @date 2024-04-29 22:51:37
 * @verion v1.1
 */
@Data
@TableName("product_management_info")
public class ProductManagementInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    @TableId
    @JsonSerialize(using = Long2StringSerialize.class)
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

    // TODO 表里没字段，先注释掉
    //@ApiModelProperty(value = "签订人")
    //private String signName;
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
