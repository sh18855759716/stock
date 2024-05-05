package com.example.stock.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author yang.tao
 * @date 2024/05/02 10:19
 * @Version 1.0
 */
public class Long2StringSerialize extends JsonSerializer<Long> {

    @Override
    public void serialize(Long aLong, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        //aLong就是原始的数据
        if (aLong!=null){
            jsonGenerator.writeString(aLong.toString());
        }
    }
}