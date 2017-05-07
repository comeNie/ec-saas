package com.yjg.ec.platform.shopcart.result.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartResultDto implements Serializable {

	private static final long serialVersionUID = -8248953500504037684L;

	private List<CartItemResultDto> cartItemList = new ArrayList<>();

	private Integer totalAmount;

	private Double totalPrice;

	public List<CartItemResultDto> getCartItemList() {
		return cartItemList;
	}

	public void setCartItemList(List<CartItemResultDto> cartItemList) {
		this.cartItemList = cartItemList;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

}
