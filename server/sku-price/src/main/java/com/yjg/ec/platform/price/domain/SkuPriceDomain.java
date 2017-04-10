package com.yjg.ec.platform.price.domain;

import java.util.Date;

/**
 * 商品价格domain
 * 
 * @author aoyasong
 *
 */
public class SkuPriceDomain {

	// 主键ID
	private Long id;

	// 商品ID
	private Long skuId;

	// 商户ID
	private Long merchantId;

	// 地域ID
	private Long areaId;

	// 三级分类ID
	private Long category3Id;

	// 价格
	private Double price;

	// 价格类型
	private String type;

	// 操作人
	private String operator;

	// 数据创建者
	private String createBy;

	// 数据创建时间
	private Date createDate;

	// 数据更新者
	private String lastUpdateBy;

	// 数据更新时间
	private Date lastUpdate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public Long getCategory3Id() {
		return category3Id;
	}

	public void setCategory3Id(Long category3Id) {
		this.category3Id = category3Id;
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

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
