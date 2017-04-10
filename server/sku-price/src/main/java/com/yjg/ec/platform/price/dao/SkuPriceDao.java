package com.yjg.ec.platform.price.dao;

import java.util.List;

import com.yjg.ec.platform.price.domain.SkuPriceDomain;
import com.yjg.ec.platform.price.param.dto.SkuPriceSelectParamDto;

/**
 * 商品价格Dao
 * 
 * @author aoyasong
 *
 */
public interface SkuPriceDao {

	public List<SkuPriceDomain> getSkuPrice(SkuPriceSelectParamDto skuPriceSelectParamDto);

}
