package com.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.portal.bean.Cart;
import com.portal.service.CartService;

@RestController
public class CartController {
	@Autowired
	private CartService cartService;
	

	@RequestMapping(value = "/cart", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity <Cart> createCartAccount(@RequestBody Cart cart) {
		HttpHeaders headers = new HttpHeaders();
		 
		if(cart == null) {
			return new ResponseEntity<Cart>(HttpStatus.BAD_REQUEST);
		}
		cartService.createCart(cart);
		headers.add("Cart Created - ", Int.valueOf(cart.getId()));
		return new ResponseEntity<Cart>(cart, headers, HttpStatus.CREATED);
	}
	

}
