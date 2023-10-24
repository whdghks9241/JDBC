package com.kh.MVC.orders;

import java.util.List;

public class orderView {

	public void showOrderList(List<orderDTO> orders) {
		for (orderDTO o : orders) {
			System.out.println("가게 번호 : " + o.getCafe_id());
			System.out.println("주문 메뉴 : " + o.getOrders_menu());
			System.out.println("주문 수량 : " + o.getQuantity());
			System.out.println("총 주문 가격 : " + o.getTotal_price());
		}
	}
	
	public void showTotalPrice(double totalPrice) {
		System.out.println("총 가격 : " + totalPrice);
	}
}
