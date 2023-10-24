package com.kh.cafe.menu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class menuDAO {

private Connection connection;
	
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String username = "khcafe";
	String password = "khcafe";
	
	menuQuery mq = new menuQuery();
	
	public menuDAO() {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<menuDTO> getMenuList(int cafeId) {
		
		List<menuDTO> menuList = new ArrayList<>();
		
		try {
			PreparedStatement ps = connection.prepareStatement(mq.selectMenuList());
			
			ps.setInt(1,cafeId);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int menu_id = rs.getInt("menu_id");
				int cafe_id = rs.getInt("cafe_id");
				String menu_name = rs.getString("menu_name");
				double price = rs.getDouble("price");
				String description = rs.getString("description");
				
				menuDTO menu = new menuDTO(menu_id, cafe_id, menu_name, price, description);
				menuList.add(menu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return menuList;
	}
}
