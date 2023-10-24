package com.kh.cafe.menu;

public class menuQuery {
	
	String sql;

	public String selectMenuList() {
		sql = "SELECT * FROM Menu WHERE cafe_id = ? ";
		return sql;
	}
}
