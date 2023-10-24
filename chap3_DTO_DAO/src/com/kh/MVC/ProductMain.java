package com.kh.MVC;

import java.util.List;

public class ProductMain {

	public static void main(String[] args) {
		ProductDAO dao = new ProductDAO();
		ProductController controller = new ProductController(dao);
		ProductView view = new ProductView();
		
		// 모든 제품 리스트 가져오기
		List<ProductDTO> products = controller.getAllProducts();
		view.showProductList(products);
		
		// 최종가격 계산 가져어기
		double totalPrice = controller.calculateTotalPrice(products);

		
	}
}
