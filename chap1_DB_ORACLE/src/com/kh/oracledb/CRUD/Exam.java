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
	
	//	1. ��� ī�� ��� ��������
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
			
			System.out.println("ī�� ����");			
			System.out.println("ī�� ��ȣ : " + CAFE_ID);			
			System.out.println("ī�� �̸� : " + CNAME);			
			System.out.println("ī�� �ּ� : " + ADDRESS);			
			System.out.println("ī�� ��ȭ��ȣ : " + PHONE_NUMBER);		
			System.out.println("ī�� �����ð� : " + OPERATING_HOURS);
			System.out.println("");
		}
	}

	//	2. Ư�� ī���� �޴� ��������
	static void selectCafeMenu() {
		String selectQuery = "SELECT menu_name FROM menu WHERE cafe_id = ?";
		
		try {
			PreparedStatement selectPs = con.prepareStatement(selectQuery);
			selectPs.setInt(1, 39);
			
			ResultSet result = selectPs.executeQuery();
			
			while(result.next()) {
				String menu_name = result.getString("menu_name");
				
				System.out.println("�޴� : " + menu_name);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//	3, ���ο� ī�� �߰��ϱ�
	static void insertCafeInfo() {
		
		String insertQuery = "INSERT INTO cafes(CAFE_ID, CNAME, ADDRESS, PHONE_NUMBER, OPERATING_HOURS) values(?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement insertPs = con.prepareStatement(insertQuery);
			
			insertPs.setInt(1, 37);
			insertPs.setString(2, "�����ī��");
			insertPs.setString(3, "����� ���۱�");
			insertPs.setString(4, "02-153-4268");
			insertPs.setString(5, "���� 09:00 ~ 20:00");
			insertPs.executeLargeUpdate();
			
			System.out.println("ī�� �߰� �Ϸ�");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	//	4. Ư�� ī���� �޴� ���ݺ����ϱ�
	static void updateMenu() {
		String updateQuery = "UPDATE menu set price = ? WHERE menu_id = ? AND cafe_id = ?";
		
		try {
			PreparedStatement updateState  = con.prepareStatement(updateQuery);
			
			updateState.setDouble(1,9.0);
			updateState.setInt(2, 22);
			updateState.setInt(3, 41);
			
			updateState.executeUpdate();
			
			System.out.println("���ݺ��� �Ϸ�");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//	5. Ư�� ī���� ���������ϱ�
	static void updateCafeInfo() {
		String updateQuery = "UPDATE cafes set ADDRESS = ? WHERE cafe_id = ?";
		
		try {
			PreparedStatement updateState = con.prepareStatement(updateQuery);
			
			updateState.setString(1, "����� ���۱�");
			updateState.setInt(2, 59);
			
			updateState.executeUpdate();
			System.out.println("ī������ �����Ϸ�");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//	6. Ư�� ī���� ���� �����ϱ�
	static void deleteCafeInfo() {
		String deleteQuery = "DELETE FROM CAFES WHERE cafe_id = ?";
		
		try {
			PreparedStatement deletePs = con.prepareStatement(deleteQuery);
			deletePs.setInt(1,61);
			
			deletePs.executeUpdate();
			
			System.out.println("ī������ �����Ϸ�");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//	7. Ư�� ī���� �޴� �� ��������
	static void selectCafeMenuCount() {
		String selectQuery = "SELECT count(menu_id) as cnt FROM menu WHERE cafe_id = ?";
		
		try {
			PreparedStatement selectPs = con.prepareStatement(selectQuery);
			
			selectPs.setInt(1, 39);
			
			ResultSet rs = selectPs.executeQuery();
			
			while(rs.next()) {
				int count = rs.getInt("cnt");
				
				System.out.println("�޴� ���� : " + count);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//	8. Ư�� ���ݹ��� ���� ��� �޴� ��������
	static void selectCafeMenuList() {
		String selectQuery = "SELECT c.cname ī���̸�, m.menu_name �޴� , m.price ���� FROM cafes c join menu m on c.cafe_id = m.cafe_id WHERE m.price >= ? and m.price <= ?";
	
		try {
			PreparedStatement selectPs = con.prepareStatement(selectQuery);

			selectPs.setDouble(1, 1.00);
			selectPs.setDouble(2, 6.00);
			
			ResultSet rs = selectPs.executeQuery();
			
			while(rs.next()) {
				String cafeName = rs.getString("ī���̸�");
				String menuName = rs.getString("�޴�");
				double price = rs.getDouble("����");

				System.out.println("ī���̸� : " + cafeName);
				System.out.println("�޴� : " + menuName);
				System.out.println("���� : " + price);
				System.out.println("");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//	9. Ư�� �޴� ���� ��������
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

				System.out.println("�޴���ȣ : " + MENU_ID);
				System.out.println("ī���ȣ : " + CAFE_ID);
				System.out.println("�޴��̸� : " + MENU_NAME);
				System.out.println("���� : " + PRICE);
				System.out.println("���� : " + DESCRIPTION);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//	10. ��� ī���� �̸��� �ּ� ��������
	static void selectCafeNameAddress() {
		String selectQuery = "SELECT CNAME,ADDRESS FROM cafes";
		
		PreparedStatement selectPs;
		try {
			selectPs = con.prepareStatement(selectQuery);
			
			ResultSet result = selectPs.executeQuery();
			
			while(result.next()) {
				
				String CNAME = result.getString("CNAME");
				String ADDRESS = result.getString("ADDRESS");
				
				System.out.println("ī�� ����");				
				System.out.println("ī�� �̸� : " + CNAME);			
				System.out.println("ī�� �ּ� : " + ADDRESS);			
				System.out.println("");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

	//	11. Ư�� ī���� � �ð� ���� �� ��� Ȯ��
	static void updateCafeTime() {
		String updateQuery = "UPDATE cafes set operating_hours = ? WHERE cafe_id = ?";
		
		try {
			PreparedStatement updatePs = con.prepareStatement(updateQuery);
		
			updatePs.setString(1, "���� 09:00 ~ 20:00");
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
				
				System.out.println("ī�� ����");			
				System.out.println("ī�� ��ȣ : " + CAFE_ID);			
				System.out.println("ī�� �̸� : " + CNAME);			
				System.out.println("ī�� �ּ� : " + ADDRESS);			
				System.out.println("ī�� ��ȭ��ȣ : " + PHONE_NUMBER);		
				System.out.println("ī�� �����ð� : " + OPERATING_HOURS);
				System.out.println("");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//	12. Ư�� ī���� ��ȭ��ȣ ���� �� ��� Ȯ��
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
				
				System.out.println("ī�� ����");			
				System.out.println("ī�� ��ȣ : " + CAFE_ID);			
				System.out.println("ī�� �̸� : " + CNAME);			
				System.out.println("ī�� �ּ� : " + ADDRESS);			
				System.out.println("ī�� ��ȭ��ȣ : " + PHONE_NUMBER);		
				System.out.println("ī�� �����ð� : " + OPERATING_HOURS);
				System.out.println("");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
