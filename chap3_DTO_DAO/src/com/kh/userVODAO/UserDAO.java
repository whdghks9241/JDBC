package com.kh.userVODAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

	private Connection connection;
	
	public UserDAO(Connection connection) {
		this.connection = connection;
	}
	
	public boolean createUser(/*�Ķ���� �� �߰�*/UserVO user) {
		
		int rows = 0;
		
		String sql = "INSERT INTO USERINFO (user_id, username, email, reg_date) values (?, ?, ? ,?)";
		
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			
			st.setInt(1,  user.getUserId());
			st.setString(2, user.getUserName());
			st.setString(3, user.getEmail());
			st.setDate(4,new Date(user.getRegDate().getTime()));
			
			rows = st.executeUpdate();
			// ���� ������ 0���� Ŀ���Ƿ� true�� ��

			return  rows > 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
	}
	
	public List<UserVO> getAllUsers() throws SQLException {
		
		List<UserVO> users = new ArrayList<>();
		
		String sql = "SELECT * FROM USERINFO";
		// try - with - resources
		// �����ͺ��̽� ���ҽ��� �����ϰ� ����ϰ� �ڵ����� �ݾ��ֱ� ���ؼ� ���Ǵ� ���
		try (PreparedStatement st = connection.prepareStatement(sql)) {
			
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				UserVO user = new UserVO();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setRegDate(rs.getDate("reg_date"));
				users.add(user);
			}
		}
		
		return users;
	}
	public boolean selectUser(UserVO user) {

		
		String sql = "SELECT * FROM USERINFO WHERE username = ? AND email = ?";
	
		try {
			
			PreparedStatement st = connection.prepareStatement(sql);

			st.setString(1, user.getUserName());	
			st.setString(2, user.getEmail());
			
			ResultSet rs = st.executeQuery();

			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
	}
	

}
