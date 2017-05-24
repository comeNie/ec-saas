package com.yjg.ec.platform.shopcart.api;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.yjg.ec.platform.auth.common.UserUtil;
import com.yjg.ec.platform.common.constant.CommonConstants;
import com.yjg.ec.platform.common.util.CookieUtil;
import com.yjg.ec.platform.login.result.dto.CustomerResultDto;
import com.yjg.ec.platform.shopcart.param.dto.CartItemParamDto;
import com.yjg.ec.platform.shopcart.param.dto.CartParamDto;
import com.yjg.ec.platform.shopcart.service.ShoppingCartService;

public class ShoppingCartApiImpl implements ShoppingCartApi {

	@Resource
	private CookieUtil cookieUtil;

	@Resource
	private ShoppingCartService shoppingCartService;

	public void addToShoppingCart(HttpServletRequest request, HttpServletResponse response,
			CartItemParamDto cartItemParamDto) throws Exception {
		CustomerResultDto customerResultDto = (CustomerResultDto) UserUtil.getCurrentUserInfo();
		if (customerResultDto != null) {

		}
		Cookie cookie = cookieUtil.getCookieByName(request, CommonConstants.SHOPPING_CART_COOKIE_ID);
		if (cookie == null) {
			CartParamDto cartDto = addNewCartItem(cartItemParamDto);
			cookieUtil.addCookie(request, response, CommonConstants.SHOPPING_CART_COOKIE_ID, JSON.toJSONString(cartDto),
					CommonConstants.SHOPPING_CART_MAX_AGE);
		} else {
			String cartStr = cookie.getName();
			CartParamDto cartTempDto = JSON.parseObject(cartStr, CartParamDto.class);
			mergeCart(cartItemParamDto, cartTempDto);
			cookieUtil.addCookie(request, response, CommonConstants.SHOPPING_CART_COOKIE_ID,
					JSON.toJSONString(cartTempDto), CommonConstants.SHOPPING_CART_MAX_AGE);
		}
	}

	public void reduceItemNumber(HttpServletRequest request, HttpServletResponse response) {

	}

	private CartParamDto addNewCartItem(CartItemParamDto cartItemParamDto) {
		CartParamDto cartDto = new CartParamDto();
		cartDto.getCartItemList().add(cartItemParamDto);
		return cartDto;
	}

	private void mergeCart(CartItemParamDto cartItemParamDto, CartParamDto cartTempDto) {
		List<CartItemParamDto> cartTempList = cartTempDto.getCartItemList();
		if (cartTempList.contains(cartItemParamDto)) {
			cartTempList.forEach(dtoTemp -> {
				if (dtoTemp.equals(cartItemParamDto)) {
					dtoTemp.setNumber(dtoTemp.getNumber() + cartItemParamDto.getNumber());
				}
			});
		} else {
			cartTempList.add(cartItemParamDto);
		}

	}

}
