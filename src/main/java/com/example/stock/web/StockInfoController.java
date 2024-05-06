package com.example.stock.web;

import com.example.stock.service.StockInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * 库存表
 *
 * @author suheng
 * @email 
 * @date 2024-04-30 13:57:20
 * @version v1.1
 */
@RestController
@RequestMapping("/v1/stockInfo")
public class StockInfoController {
    @Autowired
    private StockInfoService stockInfoService;



}
