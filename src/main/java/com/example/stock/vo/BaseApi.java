package com.example.stock.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel
@Data
public class BaseApi<T> implements Serializable {
    @ApiModelProperty(value = "结果状态码", required = true)
    private String code;
    @ApiModelProperty(value = "message", required = true)
    private String msg;
    @ApiModelProperty(value = "数据", required = true)
    private T ret;

    public BaseApi() {

    }

    public BaseApi(String code, String msg, T ret) {
        this.code = code;
        this.msg = msg;
        this.ret = ret;
    }


}
