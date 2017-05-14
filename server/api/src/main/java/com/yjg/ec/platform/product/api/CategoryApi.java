package com.yjg.ec.platform.product.api;

import java.util.List;

import com.yjg.ec.platform.product.dto.CategoryDto;

public interface CategoryApi {
	public List<CategoryDto> queryCategoryList();
	
	public List<CategoryDto> queryOneLevelCategoryList();
	
	public List<CategoryDto> querySecondLevelCategoryList();

}
