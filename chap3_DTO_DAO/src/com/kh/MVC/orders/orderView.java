package com.kh.MVC.orders;

import java.util.List;

public class orderView {

	public void showOrderList(List<orderDTO> orders) {
		for (orderDTO o : orders) {
			System.out.println("���� ��ȣ : " + o.getCafe_id());
			System.out.println("�ֹ� �޴� : " + o.getOrders_menu());
			System.out.println("�ֹ� ���� : " + o.getQuantity());
			System.out.println("�� �ֹ� ���� : " + o.getTotal_price());
		}
	}
	
	public void showTotalPrice(double totalPrice) {
		System.out.println("�� ���� : " + totalPrice);
	}
}
