package com.kh.MVC.shopping;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

	private List<ProductDTO> cartList;
	
	//쇼핑카트 생성자
	public ShoppingCart() {
		cartList = new ArrayList<>();
	}

	// 장바구니 목록 전달해줄 get 매서드 생성
	public List<ProductDTO> getCartList() {
		return cartList;
	}
	
	
	// 장바구니에 추가할 메서드 생성
	public void addCart(/*파라미터 값 추가*/ProductDTO product) {
		cartList.add(product);
		System.out.println(product.getProduct_name() + "가 추가되었습니다");
	}
	
	// 장바구니에서 물품을 제거할 메서드 생성
	public void removeGoods(ProductDTO product) {
		if (cartList.remove(cartList)) {
			System.out.println("장바구니에서 제거 했습니다.");
		} else {
			System.out.println("장바구니에 해당 제품이 없습니다.");
		}
	}

	// 장바구니 최종 결제 금액 메서드
	public double cartTotalPrice() {
		double totalPrice = 0;
		// 향상된 for문을 이용해서 가격 추가
		for (ProductDTO c : cartList) {
			totalPrice += c.getPrice();
		}
		return totalPrice;
	}
	
}
