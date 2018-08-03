package com.portal.service;

import java.util.List;

import com.portal.bean.Product;

public interface ProductService {

	public List<Product> getAllProducts();

	public List<Product> getSearchProducts(String productName);

	// public int deleteEmployee(Long employeeId);

	// public int updateEmployee(Employee employee);

	public int createProduct(Product product);
}
