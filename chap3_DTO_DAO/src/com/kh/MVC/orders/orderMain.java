package com.kh.MVC.orders;

import java.util.List;

public class orderMain {

	public static void main(String[] args) {
		
		orderDAO dao = new orderDAO();
		orderController controller = new orderController(dao);
		orderView view = new orderView();
		
		//	��ü �ֹ� ����
		List<orderDTO> orders = controller.getAllProducts();
		view.showOrderList(orders);
		
		double totalPrice = controller.calculaterTotalPrice(orders);
	}
}
