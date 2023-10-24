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
		// 1. DB 연결 URL, USERNAME, PASSWORD
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
			
			System.out.println("아이디를 입력 해주세요 : ");
			String userName = sc.next();
			
			
			System.out.println("이메일을 작성 해주세요 : ");
			String email = sc.next();
			
			Date regDate = new Date(); // 현재 날짜와 시간을 사용한다는 의미
			
			// DB에 담기 위해 인스턴스 생성 후 작성 받은 정보 저장하기
			UserVO newUser = new UserVO();
			newUser.setUserId(userId);
			newUser.setUserName(userName);
			newUser.setEmail(email);
			newUser.setRegDate(regDate);
			
			// 데이터가 정상적으로 들어갔는지 확인 voolean을 사용해서 확인
			if (userdao.createUser(newUser)) {
				System.out.println("유저가 성공적으로 등록되었습니다");
				
			} else {
				System.out.println("회원가입 실패!!");
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
				System.out.println("User ID 입력해주세요");
				System.out.println("종료하고싶다면 특수문자 제외 [e]입력");
				String input = sc.nextLine();
	
				if ("e".equalsIgnoreCase(input)) {
					System.out.println("종료");
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
					
					System.out.println("유저를 찾을 수 없습니다.");
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
				System.out.println("User ID 입력해주세요");
				System.out.println("종료하고싶다면 특수문자 제외 [e]입력");
				String inputUserId = sc.nextLine();
	
				if ("e".equalsIgnoreCase(inputUserId)) {
					System.out.println("종료");
					break;
				}
	
				System.out.println("User Email을 입력해주새요");
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
						System.out.println("일치하지 않는 아이디 입니다");
						System.out.println();
					} else if (idTrue && !emailTrue) {
						System.out.println("일치하지 않는 이메일입니다");
						System.out.println();
					} else {
						System.out.println("아이디와 이메일이 일치하지 않습니다");
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
