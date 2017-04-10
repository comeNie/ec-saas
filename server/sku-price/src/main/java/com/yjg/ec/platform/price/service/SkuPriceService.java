package com.yjg.ec.platform.price.service;

import java.util.List;

import com.yjg.ec.platform.price.param.dto.SkuPriceSelectParamDto;
import com.yjg.ec.platform.price.result.dto.SkuPriceReslutDto;

/**
 * 商品价格service处理接口
 * 
 * @author aoyasong
 *
 */
public interface SkuPriceService {

	public List<SkuPriceReslutDto> listSkuPrice(SkuPriceSelectParamDto skuPriceSelectParamDto);

}
