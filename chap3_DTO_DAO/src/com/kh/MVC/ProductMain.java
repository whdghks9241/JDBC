package com.kh.MVC;

import java.util.List;

public class ProductMain {

	public static void main(String[] args) {
		ProductDAO dao = new ProductDAO();
		ProductController controller = new ProductController(dao);
		ProductView view = new ProductView();
		
		// ��� ��ǰ ����Ʈ ��������
		List<ProductDTO> products = controller.getAllProducts();
		view.showProductList(products);
		
		// �������� ��� �������
		double totalPrice = controller.calculateTotalPrice(products);

		
	}
}
