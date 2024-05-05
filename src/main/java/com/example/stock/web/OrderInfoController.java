package com.example.stock.web;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.stock.dto.QueryOrderInfoPageDto;
import com.example.stock.dto.SaveOrderInfoDto;
import com.example.stock.entity.OrderInfoEntity;
import com.example.stock.service.OrderInfoService;
import com.example.stock.vo.BaseApi;
import com.example.stock.vo.OrderInfoVo;
import com.example.stock.vo.PageVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



/**
 * 订单表
 *
 * @author suheng
 * @email
 * @date 2024-04-30 13:57:20
 * @version v1.1
 */
@RestController
@RequestMapping("/v1/orderInfo")
public class OrderInfoController {
	@Autowired
	private OrderInfoService orderInfoService;


	@GetMapping("/queryPurchaseOrderInfoPage")
	@ApiOperation("查询采购订单")
	public BaseApi<PageVo<OrderInfoEntity>> queryPurchaseOrderInfoPage(QueryOrderInfoPageDto dto){
		return orderInfoService.queryOrderInfoPage(1, dto);
	}

	@PostMapping("/savePurchaseOrderInfo")
	@ApiOperation("保存采购订单信息")
	public BaseApi<String> savePurchaseOrderInfo(@RequestBody SaveOrderInfoDto dto){
		return orderInfoService.saveOrderInfo(1, dto);
	}


	@GetMapping("/querySaleOrderInfoPage")
	@ApiOperation("查询销售订单")
	public BaseApi<PageVo<OrderInfoEntity>> querySaleOrderInfoPage(QueryOrderInfoPageDto dto){
		return orderInfoService.queryOrderInfoPage(2, dto);
	}

	@PostMapping("/saveSaleOrderInfo")
	@ApiOperation("保存销售订单信息")
	public BaseApi<String> saveSaleOrderInfo(@RequestBody SaveOrderInfoDto dto){
		return orderInfoService.saveOrderInfo(2, dto);
	}

	@GetMapping("/queryOrderInfo")
	@ApiOperation("查询订单详情")
	public BaseApi<OrderInfoVo> queryOrderInfo(@RequestParam("id") Long id) {
		return orderInfoService.queryOrderInfo(id);
	}

	@DeleteMapping("/delOrderProduct")
	@ApiOperation("删除订单明细行")
	public BaseApi<?> delOrderProduct(@RequestParam("id") Long id){
		return orderInfoService.delOrderProduct(id);
	}

	@PostMapping("/saveInOrOutbound")
	@ApiOperation("操作出库入库")
	public BaseApi<?> saveInOrOutbound(@RequestBody OrderInfoEntity dto){
		return orderInfoService.saveInOrOutbound(dto.getId());
	}


}
