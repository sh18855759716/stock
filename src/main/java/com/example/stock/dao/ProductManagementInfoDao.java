package com.example.stock.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.stock.dto.ProductManagementInfoDTO;
import com.example.stock.entity.ProductManagementInfoEntity;
import com.example.stock.vo.ProductManagementInfoVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 产品管理表
 * 
 * @author suheng
 * @email 
 * @date 2024-04-29 22:51:37
 * @version v1.1
 */
@Repository("productManagementInfoDao")
public interface ProductManagementInfoDao extends BaseMapper<ProductManagementInfoEntity> {

    List<ProductManagementInfoVO> queryProductInfoList(ProductManagementInfoDTO productManagementInfoDTO);

}
