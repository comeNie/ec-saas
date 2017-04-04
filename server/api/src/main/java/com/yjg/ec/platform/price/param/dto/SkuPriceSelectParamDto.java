package com.yjg.ec.platform.price.param.dto;

import java.util.List;

import com.yjg.ec.platform.api.param.dto.ParentParamDto;

/**
 * 商品价格查询参数Dto
 * 
 * @author aoyasong
 *
 */
public class SkuPriceSelectParamDto extends ParentParamDto {

	private static final long serialVersionUID = 1093514472615900165L;

	// skuId 列表
	private List<Long> skuIdList;

	// 三级分类Id列表
	private List<Long> category3IdList;

	// 区域Id
	private Long areaId;

	public List<Long> getSkuIdList() {
		return skuIdList;
	}

	public void setSkuIdList(List<Long> skuIdList) {
		this.skuIdList = skuIdList;
	}

	public List<Long> getCategory3IdList() {
		return category3IdList;
	}

	public void setCategory3IdList(List<Long> category3IdList) {
		this.category3IdList = category3IdList;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

}
