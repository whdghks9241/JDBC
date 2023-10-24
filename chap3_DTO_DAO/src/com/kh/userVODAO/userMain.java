package com.kh.userVODAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class userMain {
	
	static String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
	static String dbUserName = "khcafe";
	static String dbPassword = "khcafe"; 
	
	static Connection connection;
	
	static UserDAO userdao;
	
	public static void main(String[] args) {
		// 1. DB ���� URL, USERNAME, PASSWORD
		userMain um = new userMain();
//		um.selectAll();
		
//		register();
//		um.selectScanner();
		um.selectUser();
	}
	
	
	public static void insertRun () {
		try {
			
			connection = DriverManager.getConnection(jdbcURL, dbUserName, dbPassword);
			
			userdao = new UserDAO(connection);
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("User ID : ");
			int userId = sc.nextInt();
			
			System.out.println("���̵� �Է� ���ּ��� : ");
			String userName = sc.next();
			
			
			System.out.println("�̸����� �ۼ� ���ּ��� : ");
			String email = sc.next();
			
			Date regDate = new Date(); // ���� ��¥�� �ð��� ����Ѵٴ� �ǹ�
			
			// DB�� ��� ���� �ν��Ͻ� ���� �� �ۼ� ���� ���� �����ϱ�
			UserVO newUser = new UserVO();
			newUser.setUserId(userId);
			newUser.setUserName(userName);
			newUser.setEmail(email);
			newUser.setRegDate(regDate);
			
			// �����Ͱ� ���������� ������ Ȯ�� voolean�� ����ؼ� Ȯ��
			if (userdao.createUser(newUser)) {
				System.out.println("������ ���������� ��ϵǾ����ϴ�");
				
			} else {
				System.out.println("ȸ������ ����!!");
			}
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			System.out.println(e);
		}
	}
	
	public void selectAll() {
		try {
			connection = DriverManager.getConnection(jdbcURL, dbUserName, dbPassword);
			
			// List<UserVO> users = getAllusers();
			UserDAO userDAO = new UserDAO(connection);
			
			List<UserVO> users = userDAO.getAllUsers();
			
			for (UserVO u : users) {
				System.out.println("User Id : " + u.getUserId());
				System.out.println("User Name : " + u.getUserName());
				System.out.println("Email : " + u.getEmail());
				System.out.println("reg Date : " + u.getRegDate());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void selectScanner() {
		
		try {
			connection = DriverManager.getConnection(jdbcURL, dbUserName, dbPassword);
			
			Scanner sc = new Scanner(System.in);
			
			while(true) {
				System.out.println("User ID �Է����ּ���");
				System.out.println("�����ϰ�ʹٸ� Ư������ ���� [e]�Է�");
				String input = sc.nextLine();
	
				if ("e".equalsIgnoreCase(input)) {
					System.out.println("����");
					break;
				}
				
				int userId = Integer.parseInt(input);
				String sql = "SELECT * FROM USERINFO WHERE user_id = ?";
				
				PreparedStatement st = connection.prepareStatement(sql);
				st.setInt(1, userId);
				ResultSet rs = st.executeQuery();
				
				if (rs.next()) {
					System.out.println("user ID : " + rs.getInt("user_id"));
					System.out.println("user Name : " + rs.getString("username"));
					System.out.println("email : " + rs.getString("email"));
					System.out.println("Refistration Date : " + rs.getDate("reg_date"));
				} else {
					
					System.out.println("������ ã�� �� �����ϴ�.");
					System.out.println();
				}
				
				st.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void selectUser() {
		
		try {
			connection = DriverManager.getConnection(jdbcURL, dbUserName, dbPassword);
			
			Scanner sc = new Scanner(System.in);
			
			while(true) {
				System.out.println("User ID �Է����ּ���");
				System.out.println("�����ϰ�ʹٸ� Ư������ ���� [e]�Է�");
				String inputUserId = sc.nextLine();
	
				if ("e".equalsIgnoreCase(inputUserId)) {
					System.out.println("����");
					break;
				}
	
				System.out.println("User Email�� �Է����ֻ���");
				String inputEmail = sc.nextLine();
				
				int userId = Integer.parseInt(inputUserId);
				String sql = "SELECT * FROM USERINFO WHERE user_id = ? and email = ?";
				
				PreparedStatement st = connection.prepareStatement(sql);
				st.setInt(1, userId);
				st.setString(2, inputEmail);
				
				ResultSet rs = st.executeQuery();
				
				if (rs.next()) {
					System.out.println("user ID : " + rs.getInt("user_id"));
					System.out.println("user Name : " + rs.getString("username"));
					System.out.println("email : " + rs.getString("email"));
					System.out.println("Refistration Date : " + rs.getDate("reg_date"));
					System.out.println();
				} else {

					boolean idTrue = checkid(userId); 
	
					boolean emailTrue = checkemail(inputEmail);
					
					if (!idTrue && emailTrue) {
						System.out.println("��ġ���� �ʴ� ���̵� �Դϴ�");
						System.out.println();
					} else if (idTrue && !emailTrue) {
						System.out.println("��ġ���� �ʴ� �̸����Դϴ�");
						System.out.println();
					} else {
						System.out.println("���̵�� �̸����� ��ġ���� �ʽ��ϴ�");
						System.out.println();
					}
				}
				
				st.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean checkid(int userId) throws SQLException {
		
		connection = DriverManager.getConnection(jdbcURL, dbUserName, dbPassword);
		
		String sql = "SELECT * FROM USERINFO WHERE user_id = ?";
		
		PreparedStatement st = connection.prepareStatement(sql);
		
		st.setInt(1, userId);
		
		ResultSet rs = st.executeQuery();

		if (rs.next()) {
			int count = rs.getInt(1);
			return count > 0;
		} else {
			return false;
		}
	}

	public boolean checkemail(String inputEmail) throws SQLException {
		
		connection = DriverManager.getConnection(jdbcURL, dbUserName, dbPassword);
		
		String sql = "SELECT * FROM USERINFO WHERE email = ?";
		
		PreparedStatement st = connection.prepareStatement(sql);
		
		st.setString(1, inputEmail);
		
		ResultSet rs = st.executeQuery();

		if (rs.next()) {
			int count = rs.getInt(1);
			return count > 0;
		} else {
			return false;
		}
	}
}
