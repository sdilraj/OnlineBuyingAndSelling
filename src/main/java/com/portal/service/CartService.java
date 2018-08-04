package com.portal.service;

import com.portal.bean.Cart;

public interface CartService {

	public Cart getCart(int Id);

	public int updateCart(Cart Cart);

	public int deleteCart(int Id);


}
