package com.yjg.ec.platform.price.api;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;

import com.yjg.ec.platform.common.Result;
import com.yjg.ec.platform.price.param.dto.SkuPriceSelectParamDto;
import com.yjg.ec.platform.price.result.dto.SkuPriceReslutDto;

/**
 * 商品价格服务Api
 * 
 * @author aoyasong
 *
 */
public interface SkuPriceApi {

	@RequestMapping("/inner/couponUserApi/getUserCoupons")
	public Result<List<SkuPriceReslutDto>> getSkuPricePage(SkuPriceSelectParamDto skuPriceSelectParamDto);

}
