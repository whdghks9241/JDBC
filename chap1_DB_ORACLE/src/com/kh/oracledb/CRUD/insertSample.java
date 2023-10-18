package com.kh.oracledb.CRUD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class insertSample {

	public static void main(String[] args) {
		inserOne();
		insertKhcafe();
	}
	static void inserOne() {
		String Driver = "oracle.jdbc.driver.OracleDrive";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khbank";
		String password = "kh1234";
		
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			
			String insertQuery = "INSERT INTO BANK (account_id, account_number, account_name, balance, branch_name, last_transaction_date) values (?,?,?,?,?,?)";
			
			PreparedStatement insertState = con.prepareStatement(insertQuery);

			insertState.setInt(1, 14);
			insertState.setString(2, "2509182234181");
			insertState.setString(3, "³¶³¶¾Æ");
			insertState.setDouble(4, 900.00);
			insertState.setString(5, "KH");
			insertState.setDate(6, Date.valueOf("2023-10-26"));
			
			int rowsInsert = insertState.executeUpdate();
			
			System.out.println(rowsInsert + "row Ãß°¡µÊ");
//			ResultSet result = insertState.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void insertKhcafe() {
		
		String Driver = "oracle.jdbc.driver.OracleDrive";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khcafe";
		String password = "khcafe";
		
		try {
			Connection con = DriverManager.getConnection(url,user,password);
			
			// Ãß°¡ÇÏ·Á°í ÇÏ´Â Äõ¸®¹® ÀÛ¼º
			String insertQuery = "insert into book (book_id, title, author, publication_year, isbn, genre, description, price, publication_date, created_date, , is_available) "
					+ "values (?,?,?,?,?,?,?,?,?,?,?,?)" ;
		
			PreparedStatement insertState = con.prepareStatement(insertQuery);
		
			insertState.setInt(1, 14);
			insertState.setString(2, "¸ô·ù");
			insertState.setString(3, "´©±¼±î");
			insertState.setInt(4, 2023);
			insertState.setString(5, "999-9999999999");
			insertState.setString(6, "NaDa");
			insertState.setString(7, "±×·±°Å ¾ø´Ù");
			insertState.setDouble(8, 20.99);
			insertState.setDate(9, Date.valueOf("2015-10-10"));
			insertState.setDate(10, Date.valueOf("2023-10-18"));
			insertState.setDate(11, Date.valueOf("NULL"));
			insertState.setString(12, "Y");
			
			int rowsInsert = insertState.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
}
