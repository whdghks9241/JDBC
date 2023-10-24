package com.kh.cafe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class cafeDAO {
	private Connection connection;
	
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String username = "khcafe";
	String password = "khcafe";
	
	cafeQuery cq = new cafeQuery();
	
	
	public cafeDAO() {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<cafeDTO> getAllCafeList() {
		List<cafeDTO> cafeList = new ArrayList<>();
		
		try {
			PreparedStatement ps = connection.prepareStatement(cq.selectCafeList());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int cafe_id = rs.getInt("cafe_id");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String phone_number = rs.getString("phone_number");
				String operating_hours = rs.getString("operating_hours");
				
				cafeDTO cafe = new cafeDTO(cafe_id, name, address, phone_number, operating_hours);
				cafeList.add(cafe);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cafeList;
	}
}
