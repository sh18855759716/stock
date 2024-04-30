package com.example.stock.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 订单表
 * 
 * @author suheng
 * @email 
 * @date 2024-04-30 13:57:20
 * @verion v1.1
 */
@Data
@TableName("order_info")
public class OrderInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
    @ApiModelProperty(value = "主键id")
	@TableId
	private Long id;
	/**
	 * 订单类型 1：采购 2：销售
	 */
    @ApiModelProperty(value = "订单类型 1：采购 2：销售")
	private Integer orderType;
	/**
	 * 订单编号
	 */
    @ApiModelProperty(value = "订单编号")
	private String orderNum;
	/**
	 * 订单名称
	 */
    @ApiModelProperty(value = "订单名称")
	private String orderName;
	/**
	 * 订单时间
	 */
    @ApiModelProperty(value = "订单时间")
	private Date orderTime;
	/**
	 * 订单数量
	 */
    @ApiModelProperty(value = "订单数量")
	private BigDecimal orderNumber;
	/**
	 * 订单金额
	 */
    @ApiModelProperty(value = "订单金额")
	private BigDecimal orderMoney;
	/**
	 * 签订人
	 */
    @ApiModelProperty(value = "签订人")
	private String signName;
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
