package com.kh.cafe.order;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

public class orderDAO {
	

	private Connection connection;
	
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String username = "khcafe";
	String password = "khcafe";
	
	orderQuery oq = new orderQuery();
	
	public orderDAO() {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void selectMenue() {
		
	}
	
	
	public void setOrder(int choice, int menuChoice, int countChoice) throws SQLException {
		
		int menu_id, cafe_id;
		String menu_name;
		double price;
		PreparedStatement ps = connection.prepareStatement(oq.menuOrder());
		
		ps.setInt(1, choice);
		ps.setInt(2, menuChoice);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			menu_id = rs.getInt("menu_id");
			cafe_id = rs.getInt("cafe_id");
			menu_name = rs.getString("menu_name");
			price =+ rs.getDouble("price");
		}
		
		
//		, int menu_id, String order_date, int quantity, double total_price, String orders_menu, String order_ststus)

//		try {
//			PreparedStatement ps; ps = connection.prepareStatement(oq.setOrder());
//			
//			ps.setInt(1, cafe_id);
//			ps.setInt(2, menu_id);
//			ps.setDate(3, Date.valueOf(order_date));
//			ps.setInt(4, quantity);
//			ps.setDouble(5, total_price);
//			ps.setString(6, orders_menu);
//			ps.setString(7, order_ststus);
//			
//			ps.executeUpdate();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
