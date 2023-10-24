package com.kh.MVC.orders;

import java.util.List;

import com.kh.MVC.ProductDTO;

public class orderController {

	private orderDAO dao;
	
	// 积己磊
	public orderController(orderDAO dao) {
		this.dao = dao;
	}
	
	public double calculaterTotalPrice(List<orderDTO> orders) {
		double totalPrice = 0;
		for (orderDTO totalo : orders) {
			totalPrice += totalo.getTotal_price();
		}
		
		return totalPrice;
	}
	
	// 葛电 力前 府胶飘
	public List<orderDTO> getAllProducts() {
		return dao.getAllProdeucts();
		
	}
}
