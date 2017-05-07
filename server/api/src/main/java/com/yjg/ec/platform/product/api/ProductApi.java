package com.yjg.ec.platform.product.api;

import java.util.List;

import com.yjg.ec.platform.annotation.RestApi;
import com.yjg.ec.platform.api.param.dto.ParentParamDto;
import com.yjg.ec.platform.product.dto.SkuDto;

@RestApi
public interface ProductApi {
	
	
	public List<SkuDto> querySkuList(ParentParamDto param);
	
	
	public boolean createSku(ParentParamDto param);
	

}
