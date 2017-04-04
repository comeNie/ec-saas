package com.yjg.ec.platform.price.result.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品价格查询结果Dto
 * 
 * @author aoyasong
 *
 */
public class SkuPriceReslutDto implements Serializable {

	private static final long serialVersionUID = 8379291929091631305L;

	// 商品ID
	private Long skuId;

	// 商品名称
	private String name;

	// 区域ID
	private Long areaId;

	// 区域名称
	private String areaName;

	// 三级分类ID
	private Long category3Id;

	// 三级分类名称
	private String category3Name;

	// 商品价格
	private Double price;

	// 价格类型
	private String type;

	// 操作人
	private String operator;

	// 数据创建者
	private String createBy;

	// 数据创建日期
	private Date createDate;

	// 数据更新者
	private String lastUpdateBy;

	// 数据更新日期
	private Date lastUpdateDate;

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Long getCategory3Id() {
		return category3Id;
	}

	public void setCategory3Id(Long category3Id) {
		this.category3Id = category3Id;
	}

	public String getCategory3Name() {
		return category3Name;
	}

	public void setCategory3Name(String category3Name) {
		this.category3Name = category3Name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

}
