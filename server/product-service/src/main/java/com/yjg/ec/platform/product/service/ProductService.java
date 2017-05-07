package com.yjg.ec.platform.product.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import com.yjg.ec.platform.product.api.ProductApi;
import com.yjg.ec.platform.product.entity.SkuEntity;

@RestController
public class ProductService implements ProductApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
	
	public List<SkuEntity> querySkuList(){
		
		return null;
	}

}
