package com.kh.BankVODAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BankDAO {
	
	private Connection connection;
	
	String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
	String dbUserName = "khbank";
	String dbPassword = "kh1234";
	
	public BankDAO(Connection connection) {
		this.connection = connection;
	}

	
	public void updateBalence(String inputAccountNumber, String outputAccountNumber, double insertBalence) throws SQLException {
		
		String sql = "UPDATE BANK SET balance = balance - ?  WHERE account_number = ?";
			
		PreparedStatement st = connection.prepareStatement(sql);

		st.setDouble(1, insertBalence);
		st.setString(2, inputAccountNumber);
		
		st.executeUpdate();

		sql = "UPDATE BANK SET balance = balance + ? WHERE account_number = ?";
		
		st = connection.prepareStatement(sql);
		
		st.setDouble(1, insertBalence);
		st.setString(2, outputAccountNumber);
		
		st.executeUpdate();
		
		System.out.println("송금이 완료되었습니다");

		
		connection.commit();
	}
	
	public void selectBalance() {
	}

}
