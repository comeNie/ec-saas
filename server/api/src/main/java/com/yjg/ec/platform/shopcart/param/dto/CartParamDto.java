package com.yjg.ec.platform.shopcart.param.dto;

import java.io.File;
import java.io.FileFilter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartParamDto implements Serializable {

	private static final long serialVersionUID = -8248953500504037684L;

	private List<CartItemParamDto> cartItemList = new ArrayList<>();

	private Integer totalNumber;

	private Double totalPrice;

	public List<CartItemParamDto> getCartItemList() {
		return cartItemList;
	}

	public void setCartItemList(List<CartItemParamDto> cartItemList) {
		this.cartItemList = cartItemList;
	}

	public Integer getTotalNumber() {
		cartItemList.stream().forEach(cart -> {
			totalNumber += cart.getNumber();
		});
		return totalNumber;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}

	public Double getTotalPrice() {
		cartItemList.stream().forEach(cart -> {
			BigDecimal numberDec = new BigDecimal(cart.getNumber());
			BigDecimal priceDec = new BigDecimal(cart.getPrice());
			totalPrice += numberDec.multiply(priceDec).doubleValue();
		});
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

}
