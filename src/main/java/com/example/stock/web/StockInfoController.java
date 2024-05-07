package com.example.stock.web;

import com.example.stock.dto.ProductManagementInfoDTO;
import com.example.stock.dto.StockInfoDTO;
import com.example.stock.service.StockInfoService;
import com.example.stock.vo.ApiUtil;
import com.example.stock.vo.BaseApi;
import com.example.stock.vo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    /**
     * 查询库存台账
     * @param stockInfoDTO
     * @return
     */
    @PostMapping("/queryStockInfoList")
    public BaseApi<PageBean> queryStockInfoList(@RequestBody StockInfoDTO stockInfoDTO){
        PageBean pageBean = stockInfoService.queryStockInfoList(stockInfoDTO);
        return ApiUtil.addRightData("查询成功",pageBean);
    }


    /**
     * 查询库存流水
     * @param stockInfoDTO
     * @return
     */
    @PostMapping("/queryStockDetailInfoVos")
    public BaseApi<PageBean> queryStockDetailInfoVos(@RequestBody StockInfoDTO stockInfoDTO){
        PageBean pageBean = stockInfoService.queryStockDetailInfoVos(stockInfoDTO);
        return ApiUtil.addRightData("查询成功",pageBean);
    }

}
