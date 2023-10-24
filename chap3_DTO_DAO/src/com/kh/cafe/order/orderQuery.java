package com.kh.cafe.order;

public class orderQuery {

	String sql;

	public String setOrder() {
		
		sql = "INSERT INTO ORDERS (cafe_id, menu_id ,order_date, quantity, total_price, orders_menu, order_ststus) values (?, ?, ?, ?, ?, ?, ?)";
		
		return sql;
	}

	public String menuOrder() {
		
		sql = "SELECT * FROM menu WHERE cafe_id = ? and menu_id = ?";
				
		return sql;
	}
}
