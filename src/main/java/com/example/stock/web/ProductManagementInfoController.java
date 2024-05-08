package com.example.stock.web;

import com.example.stock.dto.ProductManagementInfoDTO;
import com.example.stock.entity.ProductManagementInfoEntity;
import com.example.stock.service.ProductManagementInfoService;
import com.example.stock.util.IdNumberUtil;
import com.example.stock.util.RedisUtil;
import com.example.stock.vo.ApiUtil;
import com.example.stock.vo.BaseApi;
import com.example.stock.vo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 发货单车辆信息表
 *
 * @author suheng
 * @email suheng@liangbaba.com
 * @date 2024-01-04 14:58:48
 * @version v8.4.16
 */
@RestController
@RequestMapping("/v1/productManagementInfo")
public class ProductManagementInfoController {
    @Autowired
    private ProductManagementInfoService productManagementInfoService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private IdNumberUtil idNumberUtil;

    @GetMapping("/test")
    public BaseApi test(){
        List<ProductManagementInfoEntity> productManagementInfoEntities =  productManagementInfoService.test();
        return ApiUtil.addRightData("查询成功",productManagementInfoEntities);
    }

    @GetMapping("/testRedis")
    public BaseApi testRedis(){
        redisUtil.set("test","test",-1);
        Object test = redisUtil.get("test");
        return ApiUtil.addRightData("sss",test.toString());
    }

    /**
     * 查询产品列表
     * @param productManagementInfoDTO
     * @return
     */
    @PostMapping("/queryProductInfoList")
    public BaseApi<PageBean> queryProductInfoList(@RequestBody ProductManagementInfoDTO productManagementInfoDTO){
        PageBean pageBean = productManagementInfoService.queryProductInfoList(productManagementInfoDTO);
        return ApiUtil.addRightData("查询成功",pageBean);
    }

    @GetMapping("/updateShelfStatus")
    public BaseApi updateShelfStatus(@RequestParam("id")Long id,@RequestParam("shelfStatus")Integer shelfStatus){
        productManagementInfoService.updateShelfStatus(id,shelfStatus);
        return ApiUtil.addRightData("操作成功","");

    }

    @GetMapping("/getById")
    public BaseApi getById(@RequestParam("id")Long id){
        return ApiUtil.addRightData("查询成功",productManagementInfoService.getById(id));
    }

    @PostMapping("/addOrUpdateProduct")
    public BaseApi addOrUpdateProduct(@RequestBody ProductManagementInfoDTO productManagementInfoDTO){
        return ApiUtil.addRightData("操作成功",productManagementInfoService.addOrUpdateProduct(productManagementInfoDTO));
    }

    @GetMapping("/getProductNumber")
    public BaseApi getProductNumber(){
        return ApiUtil.addRightData("生成成功",idNumberUtil.getProductNumber());
    }




    @GetMapping("/fuzzyQueryProductList")
    public BaseApi<List<ProductManagementInfoEntity>> fuzzyQueryProductList(@RequestParam(value = "productName",required = false) String productName) {
        return ApiUtil.addRightData("成功", productManagementInfoService.fuzzyQueryProductList(null, productName));
    }


}
