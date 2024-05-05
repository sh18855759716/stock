package com.example.stock.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.example.stock.util.Long2StringSerialize;
import com.example.stock.vo.BaseApi;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 订单产品关联表
 *
 * @author suheng
 * @email
 * @date 2024-04-30 13:57:20
 * @verion v1.1
 */
@Data
@TableName("order_product_info")
public class OrderProductInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    @TableId
    @JsonSerialize(using = Long2StringSerialize.class)
    private Long id;
    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单id")
    private Long orderInfoId;
    /**
     * 产品id
     */
    @ApiModelProperty(value = "产品id")
    private Long productInfoId;
    /**
     * 产品数量
     */
    @ApiModelProperty(value = "产品数量")
    private BigDecimal productNumber;
    /**
     * 产品单价
     */
    @ApiModelProperty(value = "产品单价")
    private BigDecimal productMoney;
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

    /**
     * 产品编号
     */
    @ApiModelProperty(value = "产品编号")
    @TableField(exist = false)
    private String productNum;
    /**
     * 产品名称
     */
    @ApiModelProperty(value = "产品名称")
    @TableField(exist = false)
    private String productName;
    /**
     * 规格
     */
    @ApiModelProperty(value = "规格")
    @TableField(exist = false)
    private String specification;



    public BaseApi saveCheck(){
        BaseApi api = new BaseApi();
        if (productInfoId == null) {
            api.paramError("请选择产品", null);
            return api;
        }
        if(productNumber == null){
            api.paramError("请填写产品数量", null);
            return api;
        }
        if (productNumber.compareTo(BigDecimal.ZERO) <= 0) {
            api.paramError("产品数量必须大于0", null);
            return api;
        }

        if(productMoney == null){
            api.paramError("请填写产品单价", null);
            return api;
        }
        if (productMoney.compareTo(BigDecimal.ZERO) <= 0) {
            api.paramError("产品单间必须大于0", null);
            return api;
        }
        return null;
    }

    public BaseApi updateCheck(){
        BaseApi api = new BaseApi();
        if(productNumber == null){
            api.paramError("请填写产品数量", null);
            return api;
        }
        if (productNumber.compareTo(BigDecimal.ZERO) <= 0) {
            api.paramError("产品数量必须大于0", null);
            return api;
        }

        if(productMoney == null){
            api.paramError("请填写产品单价", null);
            return api;
        }
        if (productMoney.compareTo(BigDecimal.ZERO) <= 0) {
            api.paramError("产品单间必须大于0", null);
            return api;
        }
        return null;
    }














}
