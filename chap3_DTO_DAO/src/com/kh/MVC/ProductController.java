package com.kh.MVC;

import java.util.List;

public class ProductController {

	private ProductDAO dao;
	
	// 생성자
	public ProductController(ProductDAO dao) {
		this.dao = dao;
	}
	
	public double calculateTotalPrice(List<ProductDTO> products) {
		
		double totalPrice = 0;
		// 향상된 for문을 활용하여 가격을 더해줄 것이기 때문
		for (ProductDTO totalp : products) {
			totalPrice += totalp.getPrice();
		}
		return totalPrice;
	}
	
	// 모든 제품 리스트
	public List<ProductDTO> getAllProducts() {
		return dao.getAllProducts();
		
	}
}
