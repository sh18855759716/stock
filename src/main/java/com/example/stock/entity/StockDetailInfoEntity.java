package com.example.stock.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 库存明细表
 * 
 * @author suheng
 * @email 
 * @date 2024-04-30 13:57:20
 * @verion v1.1
 */
@Data
@TableName("stock_detail_info")
public class StockDetailInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
    @ApiModelProperty(value = "主键id")
	@TableId
	private Long id;
	/**
	 * 产品id
	 */
    @ApiModelProperty(value = "产品id")
	private Long productInfoId;
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
