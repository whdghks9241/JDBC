package com.kh.MVC.orders;

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
	
	public orderDAO() {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<orderDTO> getAllProdeucts() {
		List<orderDTO> orders = new ArrayList<>();
		
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM orders");
			
			ResultSet result = ps.executeQuery();
			
			while(result.next()) {
				int order_id = result.getInt("order_id");
				int cafe_id = result.getInt("cafe_id");
				int menu_id = result.getInt("menu_id");
				Date order_date = result.getDate("order_date");
				int quantity = result.getInt("quantity");
				int total_price = result.getInt("total_price");
				String orders_menu = result.getString("order_menu");
			
				orderDTO order = new orderDTO(order_id, cafe_id, menu_id, order_date, quantity, total_price, orders_menu);
				orders.add(order);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
		
	}
	
}

