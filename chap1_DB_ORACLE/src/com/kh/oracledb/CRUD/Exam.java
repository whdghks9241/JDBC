package com.kh.oracledb.CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Exam {
	
	static String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	static String userName = "khcafe";
	static String passwrod = "khcafe";
	
	static Connection con;
	
	public static void main(String[] args) throws SQLException {

		con = DriverManager.getConnection(jdbcUrl,userName, passwrod);
		
//		selectCafeAllList();
//		selectCafeMenu();
//		insertCafeInfo();
//		updateMenu();
//		updateCafeInfo();
//		deleteCafeInfo();
//		selectCafeMenuCount();
//		selectCafeMenuList();
//		selectMenuInfo();
//		selectCafeNameAddress();
//		updateCafeTime();
//		updateCafePhoneNumber();
	}
	
	//	1. 모든 카페 목록 가져오기
	//	st1 result1	
	static void selectCafeAllList() throws SQLException {
		String selectQuery = "SELECT * FROM cafes";
		
		PreparedStatement selectPs = con.prepareStatement(selectQuery);
	
		ResultSet result = selectPs.executeQuery();
		
		while(result.next()) {
			int CAFE_ID = result.getInt("CAFE_ID");
			String CNAME = result.getString("CNAME");
			String ADDRESS = result.getString("ADDRESS");
			String PHONE_NUMBER = result.getString("PHONE_NUMBER");
			String OPERATING_HOURS = result.getString("OPERATING_HOURS");
			
			System.out.println("카페 정보");			
			System.out.println("카페 번호 : " + CAFE_ID);			
			System.out.println("카페 이름 : " + CNAME);			
			System.out.println("카페 주소 : " + ADDRESS);			
			System.out.println("카페 전화번호 : " + PHONE_NUMBER);		
			System.out.println("카페 영업시간 : " + OPERATING_HOURS);
			System.out.println("");
		}
	}

	//	2. 특정 카페의 메뉴 가져오기
	static void selectCafeMenu() {
		String selectQuery = "SELECT menu_name FROM menu WHERE cafe_id = ?";
		
		try {
			PreparedStatement selectPs = con.prepareStatement(selectQuery);
			selectPs.setInt(1, 39);
			
			ResultSet result = selectPs.executeQuery();
			
			while(result.next()) {
				String menu_name = result.getString("menu_name");
				
				System.out.println("메뉴 : " + menu_name);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//	3, 새로운 카페 추가하기
	static void insertCafeInfo() {
		
		String insertQuery = "INSERT INTO cafes(CAFE_ID, CNAME, ADDRESS, PHONE_NUMBER, OPERATING_HOURS) values(?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement insertPs = con.prepareStatement(insertQuery);
			
			insertPs.setInt(1, 37);
			insertPs.setString(2, "고냥이카페");
			insertPs.setString(3, "서울시 동작구");
			insertPs.setString(4, "02-153-4268");
			insertPs.setString(5, "매일 09:00 ~ 20:00");
			insertPs.executeLargeUpdate();
			
			System.out.println("카페 추가 완료");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	//	4. 특정 카페의 메뉴 가격변경하기
	static void updateMenu() {
		String updateQuery = "UPDATE menu set price = ? WHERE menu_id = ? AND cafe_id = ?";
		
		try {
			PreparedStatement updateState  = con.prepareStatement(updateQuery);
			
			updateState.setDouble(1,9.0);
			updateState.setInt(2, 22);
			updateState.setInt(3, 41);
			
			updateState.executeUpdate();
			
			System.out.println("가격변경 완료");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//	5. 특정 카페의 정보수정하기
	static void updateCafeInfo() {
		String updateQuery = "UPDATE cafes set ADDRESS = ? WHERE cafe_id = ?";
		
		try {
			PreparedStatement updateState = con.prepareStatement(updateQuery);
			
			updateState.setString(1, "서울시 동작구");
			updateState.setInt(2, 59);
			
			updateState.executeUpdate();
			System.out.println("카페정보 수정완료");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//	6. 특정 카페의 정보 삭제하기
	static void deleteCafeInfo() {
		String deleteQuery = "DELETE FROM CAFES WHERE cafe_id = ?";
		
		try {
			PreparedStatement deletePs = con.prepareStatement(deleteQuery);
			deletePs.setInt(1,61);
			
			deletePs.executeUpdate();
			
			System.out.println("카페정보 삭제완료");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//	7. 특정 카페의 메뉴 수 가져오기
	static void selectCafeMenuCount() {
		String selectQuery = "SELECT count(menu_id) as cnt FROM menu WHERE cafe_id = ?";
		
		try {
			PreparedStatement selectPs = con.prepareStatement(selectQuery);
			
			selectPs.setInt(1, 39);
			
			ResultSet rs = selectPs.executeQuery();
			
			while(rs.next()) {
				int count = rs.getInt("cnt");
				
				System.out.println("메뉴 개수 : " + count);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//	8. 특정 가격범위 내의 모든 메뉴 가져오기
	static void selectCafeMenuList() {
		String selectQuery = "SELECT c.cname 카페이름, m.menu_name 메뉴 , m.price 가격 FROM cafes c join menu m on c.cafe_id = m.cafe_id WHERE m.price >= ? and m.price <= ?";
	
		try {
			PreparedStatement selectPs = con.prepareStatement(selectQuery);

			selectPs.setDouble(1, 1.00);
			selectPs.setDouble(2, 6.00);
			
			ResultSet rs = selectPs.executeQuery();
			
			while(rs.next()) {
				String cafeName = rs.getString("카페이름");
				String menuName = rs.getString("메뉴");
				double price = rs.getDouble("가격");

				System.out.println("카페이름 : " + cafeName);
				System.out.println("메뉴 : " + menuName);
				System.out.println("가격 : " + price);
				System.out.println("");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//	9. 특정 메뉴 정보 가져오기
	static void selectMenuInfo() {
		String selectQuery = "SELECT * FROM menu WHERE menu_id = ?";
		
		try {
			PreparedStatement selectPs = con.prepareStatement(selectQuery);
			
			selectPs.setInt(1, 10);
			
			ResultSet rs = selectPs.executeQuery();
			
			while(rs.next()) {
				int MENU_ID = rs.getInt("MENU_ID");
				int CAFE_ID = rs.getInt("CAFE_ID");
				String MENU_NAME = rs.getString("MENU_NAME");
				Double PRICE = rs.getDouble("PRICE");
				String DESCRIPTION = rs.getString("DESCRIPTION");

				System.out.println("메뉴번호 : " + MENU_ID);
				System.out.println("카페번호 : " + CAFE_ID);
				System.out.println("메뉴이름 : " + MENU_NAME);
				System.out.println("가격 : " + PRICE);
				System.out.println("설명 : " + DESCRIPTION);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//	10. 모든 카페의 이름과 주소 가져오기
	static void selectCafeNameAddress() {
		String selectQuery = "SELECT CNAME,ADDRESS FROM cafes";
		
		PreparedStatement selectPs;
		try {
			selectPs = con.prepareStatement(selectQuery);
			
			ResultSet result = selectPs.executeQuery();
			
			while(result.next()) {
				
				String CNAME = result.getString("CNAME");
				String ADDRESS = result.getString("ADDRESS");
				
				System.out.println("카페 정보");				
				System.out.println("카페 이름 : " + CNAME);			
				System.out.println("카페 주소 : " + ADDRESS);			
				System.out.println("");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

	//	11. 특정 카페의 운영 시간 변경 및 결과 확인
	static void updateCafeTime() {
		String updateQuery = "UPDATE cafes set operating_hours = ? WHERE cafe_id = ?";
		
		try {
			PreparedStatement updatePs = con.prepareStatement(updateQuery);
		
			updatePs.setString(1, "매일 09:00 ~ 20:00");
			updatePs.setInt(2, 39);
			
			updatePs.executeUpdate();
			
			String seletQuery = "SELECT * FROM cafes WHERE cafe_id = ?";
			PreparedStatement selectPs = con.prepareStatement(seletQuery);
			selectPs.setInt(1, 39);
			
			ResultSet rs = selectPs.executeQuery();
			while(rs.next()) {
				int CAFE_ID = rs.getInt("CAFE_ID");
				String CNAME = rs.getString("CNAME");
				String ADDRESS = rs.getString("ADDRESS");
				String PHONE_NUMBER = rs.getString("PHONE_NUMBER");
				String OPERATING_HOURS = rs.getString("OPERATING_HOURS");
				
				System.out.println("카페 정보");			
				System.out.println("카페 번호 : " + CAFE_ID);			
				System.out.println("카페 이름 : " + CNAME);			
				System.out.println("카페 주소 : " + ADDRESS);			
				System.out.println("카페 전화번호 : " + PHONE_NUMBER);		
				System.out.println("카페 영업시간 : " + OPERATING_HOURS);
				System.out.println("");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//	12. 특정 카페의 전화번호 변경 및 결과 확인
	static void updateCafePhoneNumber() {
		String updateQuery = "UPDATE cafes set PHONE_NUMBER = ? WHERE cafe_id = ?";
		
		try {
			PreparedStatement updatePs = con.prepareStatement(updateQuery);
		
			updatePs.setString(1, "02-789-1235");
			updatePs.setInt(2, 39);
			
			updatePs.executeUpdate();
			
			String seletQuery = "SELECT * FROM cafes WHERE cafe_id = ?";
			PreparedStatement selectPs = con.prepareStatement(seletQuery);
			selectPs.setInt(1, 39);
			
			ResultSet rs = selectPs.executeQuery();
			while(rs.next()) {
				int CAFE_ID = rs.getInt("CAFE_ID");
				String CNAME = rs.getString("CNAME");
				String ADDRESS = rs.getString("ADDRESS");
				String PHONE_NUMBER = rs.getString("PHONE_NUMBER");
				String OPERATING_HOURS = rs.getString("OPERATING_HOURS");
				
				System.out.println("카페 정보");			
				System.out.println("카페 번호 : " + CAFE_ID);			
				System.out.println("카페 이름 : " + CNAME);			
				System.out.println("카페 주소 : " + ADDRESS);			
				System.out.println("카페 전화번호 : " + PHONE_NUMBER);		
				System.out.println("카페 영업시간 : " + OPERATING_HOURS);
				System.out.println("");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
