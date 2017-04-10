package com.yjg.ec.platform.price.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.dozer.Mapper;

import com.yjg.ec.platform.price.dao.SkuPriceDao;
import com.yjg.ec.platform.price.domain.SkuPriceDomain;
import com.yjg.ec.platform.price.param.dto.SkuPriceSelectParamDto;
import com.yjg.ec.platform.price.result.dto.SkuPriceReslutDto;
import com.yjg.ec.platform.price.service.SkuPriceService;

/**
 * 商品价格逻辑实现
 * 
 * @author aoyasong
 *
 */
public class SkuPriceServiceImpl implements SkuPriceService {

	@Resource
	private SkuPriceDao skuPriceDao;

	@Resource
	private Mapper mapper;

	@Override
	public List<SkuPriceReslutDto> listSkuPrice(SkuPriceSelectParamDto skuPriceSelectParamDto) {
		List<SkuPriceDomain> domainList = skuPriceDao.getSkuPrice(skuPriceSelectParamDto);
		List<SkuPriceReslutDto> resultList = new ArrayList<>();
		for (SkuPriceDomain domain : domainList) {
			SkuPriceReslutDto SkuPriceReslutDto = mapper.map(domain, SkuPriceReslutDto.class);
			resultList.add(SkuPriceReslutDto);
		}
		return resultList;
	}

}
