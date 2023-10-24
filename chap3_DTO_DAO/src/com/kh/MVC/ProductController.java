package com.kh.MVC;

import java.util.List;

public class ProductController {

	private ProductDAO dao;
	
	// ������
	public ProductController(ProductDAO dao) {
		this.dao = dao;
	}
	
	public double calculateTotalPrice(List<ProductDTO> products) {
		
		double totalPrice = 0;
		// ���� for���� Ȱ���Ͽ� ������ ������ ���̱� ����
		for (ProductDTO totalp : products) {
			totalPrice += totalp.getPrice();
		}
		return totalPrice;
	}
	
	// ��� ��ǰ ����Ʈ
	public List<ProductDTO> getAllProducts() {
		return dao.getAllProducts();
		
	}
}
