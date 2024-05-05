package com.example.stock.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author yang.tao
 * @date 2024/05/05 19:45
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVo<T> {

    /**
     * 当前页
     */
    private int current = 1;

    /**
     * 每页显示条数，默认 10
     */
    private int size = 10;

    /**
     * 总数
     */
    private long total;

    private List<T> records;

}
