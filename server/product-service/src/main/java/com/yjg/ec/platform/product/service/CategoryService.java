package com.yjg.ec.platform.product.service;

import java.util.List;

import com.yjg.ec.platform.product.api.CategoryApi;
import com.yjg.ec.platform.product.dto.CategoryDto;

public class CategoryService implements CategoryApi{
	
	private static final int ONE_LEVEL=1;  //一级分类
	private static final int SECOND_LEVEL=2;  //二级分类
	
	@Override
	public List<CategoryDto> queryCategoryList(){
		
		return null;
	}

	@Override
	public List<CategoryDto> queryOneLevelCategoryList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryDto> querySecondLevelCategoryList() {
		// TODO Auto-generated method stub
		return null;
	}
}
