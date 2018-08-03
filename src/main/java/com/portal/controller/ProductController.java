package com.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.portal.bean.Product;
import com.portal.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/products", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Product>> getAllProducts() {
		HttpHeaders headers = new HttpHeaders();
		List<Product> products = productService.getAllProducts();

		if (products == null) {
			return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
		}
		headers.add("Number Of Records Found", String.valueOf(products.size()));
		return new ResponseEntity<List<Product>>(products, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/products/{search}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Product>> getSearchProducts(@PathVariable("search") String search) {
		HttpHeaders headers = new HttpHeaders();
		List<Product> products = productService.getSearchProducts(search);

		if (products == null) {
			return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
		}
		headers.add("Number Of Records Found", String.valueOf(products.size()));
		return new ResponseEntity<List<Product>>(products, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/products", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		HttpHeaders headers = new HttpHeaders();
		if (product == null) {
			return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
		}
		productService.createProduct(product);
		headers.add("Product Created  - ", String.valueOf(product.getProductId()));
		return new ResponseEntity<Product>(product, headers, HttpStatus.CREATED);
	}

}
