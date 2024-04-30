package com.example.stock.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 库存表
 * 
 * @author suheng
 * @email 
 * @date 2024-04-30 13:57:20
 * @verion v1.1
 */
@Data
@TableName("stock_info")
public class StockInfoEntity implements Serializable {
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
	 * 库存数量
	 */
    @ApiModelProperty(value = "库存数量")
	private BigDecimal stockNumber;
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
