package com.yjg.ec.platform.shopcart.result.dto;

import java.io.Serializable;

public class CartItemResultDto implements Serializable {

	private static final long serialVersionUID = 8620969979097525949L;

	private Integer skuId;

	private String skuName;

	private Integer number;

	private Double price;

	public Integer getSkuId() {
		return skuId;
	}

	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
