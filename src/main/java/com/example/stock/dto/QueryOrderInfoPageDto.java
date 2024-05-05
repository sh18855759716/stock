package com.example.stock.dto;

import com.example.stock.vo.BaseApi;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author yang.tao
 * @date 2024/05/01 10:28
 * @Version 1.0
 */
@Data
public class QueryOrderInfoPageDto {

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页", required = true)
    private Integer current;

    @ApiModelProperty(value = "每页显示条数",required = true)
    private Integer size;

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
    @ApiModelProperty(value = "订单时间 yyyy-MM-dd 2024-05-01")
    private String orderTime;

    public BaseApi check(){
        if (current == null || size == null) {
            return new BaseApi("500", "参数错误", null);
        }
        return null;
    }
}
