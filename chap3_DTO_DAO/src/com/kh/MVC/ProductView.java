package com.kh.MVC;

import java.util.List;

public class ProductView {

	public void showProductList(/*��ǰ ����Ʈ �Ķ���� �߰�*/List<ProductDTO> products) {
		
		for(ProductDTO p : products) {
			System.out.println("��ǰ�� : " + p.getProduct_name());
			System.out.println("���� : " + p.getPrice());
			System.out.println("====================================");
		}
	}
	
	public void showTotalPrice(double totalPrice) {
		System.out.println("�� ���� : " + totalPrice);
	}
}
