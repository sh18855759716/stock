package com.example.stock.vo;





import com.alibaba.excel.util.StringUtils;

import java.io.Serializable;



/**
 * 接口返回类型工具类
 *
 * @author mhy
 * @since 2018-09-26
 */
public class ApiUtil implements Serializable {
    //接口查询数据成功
    public static final String SUCCESS = "获取成功";
    

    public static BaseApi addRightData(BaseApi baseApi, String msg, Object data) {
        if (baseApi != null) {
            baseApi.setCode("200");
            if (StringUtils.isNotBlank(msg)) {
                baseApi.setMsg(msg);
            } else {
                baseApi.setMsg(SUCCESS);
            }
            if (data == null){
                baseApi.setRet("");
            }else {
                baseApi.setRet(data);
            }
        }
        return baseApi;
    }

    public static BaseApi addRightData( String msg, Object data) {
        return addRightData(new BaseApi(),msg,data);
    }

    public static BaseApi addRightData(String msg) {
        return addRightData(new BaseApi(), msg);
    }


    /**
     * @param baseApi
     * @param msg
     * @return
     * @author mhy
     * @date 2018-09-26 15:16
     */
    public static BaseApi addRightData( BaseApi baseApi , String msg ){
        if(baseApi != null){
            baseApi.setCode("200");
            if(StringUtils.isNotBlank(msg)){
                baseApi.setMsg(msg);
            }else{
                baseApi.setMsg(SUCCESS);
            }
        }
        return baseApi;
    }




}
