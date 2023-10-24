package com.kh.cafe;

public class cafeQuery {

	String sql;
	
	public String selectCafeList() {
		sql = "SELECT * FROM cafes";
		return sql;
	}
}
