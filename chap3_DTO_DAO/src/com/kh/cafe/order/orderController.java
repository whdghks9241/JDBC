package com.kh.cafe.order;

public class orderController {

	private orderDAO dao;
	
	public orderController(orderDAO dao) {
		this.dao = dao;
	}
	
	public void order(int choice, int menuChoice, int countChoice) {
		
//		dao.setOrder(choice, menuChoice, countChoice);
	}
}
