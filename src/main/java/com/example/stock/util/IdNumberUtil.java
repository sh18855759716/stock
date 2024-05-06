package com.example.stock.util;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;



@Component
public class IdNumberUtil {

    @Autowired
    private RedisUtil redisUtil;


    public static final String PRODUCT_NUMBER = "product:number";



    /**
     * 获取产品编号
     * @return
     */
    public String getProductNumber(){
        String productNumber = "CP";
        String key = PRODUCT_NUMBER;
        Date now = new Date();
        String format = DateUtils.format(new Date(), "yyyyMMdd");

        LocalDateTime midnight = LocalDateTime.ofInstant(now.toInstant(), ZoneId.systemDefault()).plusDays(1L).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime currentDateTime = LocalDateTime.ofInstant(now.toInstant(), ZoneId.systemDefault());
        long seconds = ChronoUnit.SECONDS.between(currentDateTime, midnight);
        int remainSecondsOneDay = (int)seconds;

        productNumber += format;
        //获取当日的序列号
        Object objNum = redisUtil.get(key);
        if (ObjectUtil.isEmpty(objNum)){
            productNumber += 1;
            redisUtil.set(key,"1",remainSecondsOneDay);
        }else {
            Integer num = Integer.valueOf(objNum.toString());
            num += 1;
            redisUtil.set(key,num.toString(),remainSecondsOneDay);
            productNumber += num;
        }
        return productNumber;
    }

}
