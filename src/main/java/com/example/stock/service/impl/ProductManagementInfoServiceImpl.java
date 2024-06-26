package com.example.stock.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.stock.dao.ProductManagementInfoDao;
import com.example.stock.dto.ProductManagementInfoDTO;
import com.example.stock.entity.OrderProductInfoEntity;
import com.example.stock.entity.ProductManagementInfoEntity;
import com.example.stock.service.ProductManagementInfoService;
import com.example.stock.vo.PageBean;
import com.example.stock.vo.ProductManagementInfoVO;
import com.github.pagehelper.PageInfo;
//import javafx.util.Builder;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 产品管理表
 *
 * @author suheng
 * @email
 * @date 2024-04-29 22:51:37
 * @version v1.1
 */
@Service("productManagementInfoService")
public class ProductManagementInfoServiceImpl extends ServiceImpl<ProductManagementInfoDao,ProductManagementInfoEntity> implements ProductManagementInfoService {
    @Autowired
    private ProductManagementInfoDao productManagementInfoDao;


	/**
	 * 查询产品列表
	 * @param productManagementInfoDTO
	 * @return
	 */
	@Override
	public PageBean queryProductInfoList(ProductManagementInfoDTO productManagementInfoDTO) {
		PageHelper.startPage(productManagementInfoDTO.getPageNum(),productManagementInfoDTO.getPageSize());
		List<ProductManagementInfoVO> productManagementInfoVOList = productManagementInfoDao.queryProductInfoList(productManagementInfoDTO);
        PageInfo<ProductManagementInfoVO> productManagementInfoVOPageInfo = new PageInfo<>(productManagementInfoVOList);
        return new PageBean(productManagementInfoVOPageInfo.getTotal(),productManagementInfoVOPageInfo.getList());
	}

    @Override
    public void updateShelfStatus(Long id, Integer shelfStatus) {
        ProductManagementInfoEntity productManagementInfoEntity = ProductManagementInfoEntity.builder()
                .id(id)
                .shelfStatus(shelfStatus)
                .updateTime(new Date())
                .build();
        productManagementInfoDao.updateById(productManagementInfoEntity);
    }

    @Override
    public ProductManagementInfoVO getById(Long id) {
        ProductManagementInfoDTO productManagementInfoDTO = ProductManagementInfoDTO.builder().id(id).build();
        List<ProductManagementInfoVO> productManagementInfoVOList = productManagementInfoDao.queryProductInfoList(productManagementInfoDTO);
        return productManagementInfoVOList.get(0);
    }


    @Override
    public Long addOrUpdateProduct(ProductManagementInfoDTO productManagementInfoDTO) {
        ProductManagementInfoEntity productManagementInfoEntity = ProductManagementInfoEntity.builder().build();
        BeanUtil.copyProperties(productManagementInfoDTO,productManagementInfoEntity, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
        if (ObjectUtil.isEmpty(productManagementInfoDTO.getId())){
            //新增
            this.insert(productManagementInfoEntity);
        }else {
            //修改
            this.updateById(productManagementInfoEntity);
        }
        return 0l;
    }

    @Override
    public List<ProductManagementInfoEntity> test() {
        return this.selectList(new EntityWrapper<>());
    }

    @Override
    public List<ProductManagementInfoEntity> fuzzyQueryProductList(List<Long> productIdList, String productName) {
        // 模糊查询拿五十条
        return this.selectPage(new Page<>(1, 50), new EntityWrapper<ProductManagementInfoEntity>()
                .in(CollectionUtils.isNotEmpty(productIdList), "id", productIdList)
                .like(StringUtils.isNotBlank(productName), "product_name", productName)
                .eq("is_del", 0)).getRecords();
    }

    @Override
    public Map<Long, ProductManagementInfoEntity> queryProductMap(List<Long> productIdList) {
        if (CollectionUtils.isEmpty(productIdList)) {
            return new HashMap<>();
        }
        return this.selectList(new EntityWrapper<ProductManagementInfoEntity>()
                .in("id", productIdList)
                .eq("is_del", 0)).stream().collect(Collectors.toMap(ProductManagementInfoEntity::getId, v -> v, (v1, v2) -> v1));

    }
}
