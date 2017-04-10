package com.yjg.ec.platform.price.api;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import com.yjg.ec.platform.common.Result;
import com.yjg.ec.platform.price.param.dto.SkuPriceSelectParamDto;
import com.yjg.ec.platform.price.result.dto.SkuPriceReslutDto;
import com.yjg.ec.platform.price.service.SkuPriceService;

/**
 * 商品价格服务api实现
 * 
 * @author aoyasong
 *
 */
@RestController
public class SkuPriceApiImpl implements SkuPriceApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(SkuPriceApi.class);

	@Resource
	private SkuPriceService skuPriceService;

	@Override
	public Result<List<SkuPriceReslutDto>> getSkuPricePage(SkuPriceSelectParamDto skuPriceSelectParamDto) {
		List<SkuPriceReslutDto> skuPriceDtoList = skuPriceService.listSkuPrice(skuPriceSelectParamDto);
		return Result.buildSuccessResult(skuPriceDtoList);
	}

}
