package com.yjg.ec.platform.product.entity;

public class SkuEntity extends BaseEntity{

	private String skuName;
	private Long categoryId;
	private Long merchantId;
	private String adWord; //广告词
	private Integer sjStatus; //上下架状态
	private String skuFormat; //售卖规格
	private String unit; //单位
	private String shelfLife; //保质期
	private String originPlace; //产地
	
	private String description; //描述
	private String packDesc; //包装售后
	public String getSkuName() {
		return skuName;
	}
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	public String getAdWord() {
		return adWord;
	}
	public void setAdWord(String adWord) {
		this.adWord = adWord;
	}
	public Integer getSjStatus() {
		return sjStatus;
	}
	public void setSjStatus(Integer sjStatus) {
		this.sjStatus = sjStatus;
	}
	public String getSkuFormat() {
		return skuFormat;
	}
	public void setSkuFormat(String skuFormat) {
		this.skuFormat = skuFormat;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getShelfLife() {
		return shelfLife;
	}
	public void setShelfLife(String shelfLife) {
		this.shelfLife = shelfLife;
	}
	public String getOriginPlace() {
		return originPlace;
	}
	public void setOriginPlace(String originPlace) {
		this.originPlace = originPlace;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPackDesc() {
		return packDesc;
	}
	public void setPackDesc(String packDesc) {
		this.packDesc = packDesc;
	}
	
	
	
}
