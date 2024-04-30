package com.example.stock.web;

import com.example.stock.service.StockDetailInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * 库存明细表
 *
 * @author suheng
 * @email 
 * @date 2024-04-30 13:57:20
 * @version v1.1
 */
@RestController
@RequestMapping("/v1/stockDetailInfo")
public class StockDetailInfoController {
    @Autowired
    private StockDetailInfoService stockDetailInfoService;

}
