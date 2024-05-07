package com.example.stock.vo;

import com.baomidou.mybatisplus.annotations.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class StockInfoVO {
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
     * 仓库
     */
    @ApiModelProperty(value = "仓库")
    private String store;

    @ApiModelProperty(value = "库存数量")
    private BigDecimal stockNumber;
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

    /**
     * 销售单编号
     */
    @ApiModelProperty(value = "销售单编号")
    private String saleOrderNum;
    /**
     * 采购单编号
     */
    @ApiModelProperty(value = "采购单编号")
    private String purchaseOrderNum;
    /**
     * 操作类型：1：入库 2：出库
     */
    @ApiModelProperty(value = "操作类型：1：入库 2：出库")
    private Integer operateType;
    /**
     * 操作数量
     */
    @ApiModelProperty(value = "操作数量")
    private BigDecimal operateNumber;
    /**
     * 是否删除0：未删除；1：删除
     */
    @ApiModelProperty(value = "是否删除0：未删除；1：删除")
    private Integer isDel;


}
