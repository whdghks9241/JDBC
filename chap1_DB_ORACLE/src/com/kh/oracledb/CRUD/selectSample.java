package com.kh.oracledb.CRUD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class selectSample {
	public static void main(String[] args) {
//		SelectBank();
//		SelectKHCAFE();
//		SelectKHCAFENINFO();
//		updateMenu();
//		insertBank();
//		SelectIf();
//		insertKhcafe();
	}
	
	static void SelectAll() {
		// 1. ����̹� ���� : Oracle JDBC ����̹� Ŭ���� �̸�
		String driver = "oracle.jdbc.driver.OracleDriver";
		// 2. ����Ŭ �� ��ǻ�� ���� : �����ͺ��̽� ���� ����
		// 								���� ip�ּ�:port��ȣ 
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khbank";
		String password = "kh1234";
		
		Connection con = null;
		
		try {
			// ������ ����Ͽ� ���� ���� �Ǵ� �����ͺ��̽� �۾� ����
			con = DriverManager.getConnection(url, user, password);
			System.out.println("������ ���̽� ���� ����");
			
			// SELECT ����
			String selectOuery = "SELECT * FROM BANK";
			PreparedStatement selectState = con.prepareStatement(selectOuery);
			ResultSet result = selectState.executeQuery();
			
			// result.next() : result ��ü���� ���� ��(row)���� �̵�, �������� ������ true��ȯ, �׷��� ������ false ��ȯ
			while(result.next()) {

//				khbank�� �ִ� bank ���̺� ������տ��� account_id�� �����´�.
				int accountID = result.getInt("account_id");
				String accountName = result.getString("account_name");
				double balace = result.getDouble("balance");
				
				// accountNumber �������
				String accountNumber = result.getString("account_Number");
				// branchName �������
				String branchName = result.getString("branch_name");
				// lastTransctionDate ��������
				Date lastTransactionDate = result.getDate("Last_Transaction_Date");
				
				System.out.println("accountID : " + accountID + "accountName : " + accountName  + "balace : " + balace + 
						"accountNumbe : " + accountNumber + "branchName : " + branchName + "lastTransactionDate" + lastTransactionDate);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void SelectKHCAFE() {
		// 1. ����̺� ����
		String Driver = "oracle.jdbc.driver.OracleDrive";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khcafe";
		String password = "khcafe";
		
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			
			String selectQuery = "SELECT * FROM menu";
			PreparedStatement selectState = con.prepareStatement(selectQuery);
			ResultSet result = selectState.executeQuery();
			
			while(result.next()) {
				int menuID = result.getInt("menu_id");
				int cafeID = result.getInt("cafe_id");
				String menuNmae = result.getString("menu_name");
				Double price = result.getDouble("price");
				String description = result.getString("description");
				
				System.out.println("menuID : " + menuID + "	cafeID : " + cafeID + "	menuNmae : " + menuNmae + 
						"	price : " + price + "	description : " + description);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void SelectOne() {
		// 1. ����̹� ���� : Oracle JDBC ����̹� Ŭ���� �̸�
				String driver = "oracle.jdbc.driver.OracleDriver";
				// 2. ����Ŭ �� ��ǻ�� ���� : �����ͺ��̽� ���� ����
				// 								���� ip�ּ�:port��ȣ 
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String user = "khbank";
				String password = "kh1234";
				
				Connection con = null;
				
				try {
					con = DriverManager.getConnection(url, user, password);
					// where �� ����Ͽ� �����߰�
					String selectQuery = "SELECT * FROM bank WHERE account_number in (?,?)";
					PreparedStatement selectState = con.prepareStatement(selectQuery);
					String[] targerAN = {"1234567890","5555666777"};
					
					selectState.setString(1, targerAN[0]);
					selectState.setString(2, targerAN[1]);
					ResultSet result = selectState.executeQuery();
					
//					// ���⿡ ���ϴ� ������ account_id ����
//					int targetAID = 4;
//					
//					selectState.setInt(1, targetAID);
					if (result.isBeforeFirst()) {
							System.out.println("���̴� ����");
					}
					
					while (result.next()) {
						int a = result.getInt("account_id");
						String b = result.getString("account_name");
						double balace = result.getDouble("balance");
						String accountNumber = result.getString("account_Number");
						String branchName = result.getString("branch_name");
						Date lastTransactionDate = result.getDate("Last_Transaction_Date");
						
						
						System.out.println("accountID : " + a  + "\naccountName : " + b  + "\nbalace : " + balace + 
								"\naccountNumbe : " + accountNumber + "\nbranchName : " + branchName + "\nlastTransactionDate : " + lastTransactionDate);
						System.out.println("");
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	}
	
	static void SelectKHCAFENINFO() {
		// 1. ����̺� ����
		String Driver = "oracle.jdbc.driver.OracleDrive";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khcafe";
		String password = "khcafe";
		
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			
			String selectQuery = "SELECT c.cname ī���̸�, c.address ī���ּ�, m.menu_name �޴� FROM menu m join cafes c on m.cafe_id = c.cafe_id";
			PreparedStatement selectState = con.prepareStatement(selectQuery);
			ResultSet result = selectState.executeQuery();
			
			while(result.next()) {
				String cafeName = result.getString("ī���̸�");
				String cafeAdress = result.getString("ī���ּ�");
				String cafeMenu = result.getString("�޴�");
			
				System.out.println("cafeName : " + cafeName + "	cafeAdress : " + cafeAdress + "	cafeMenu : " + cafeMenu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void updateMenu() {
		String Driver = "oracle.jdbc.driver.OracleDrive";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khcafe";
		String password = "khcafe";
		
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			
			String updateQuery = "update menu set description = '����' WHERE menu_id = 7";
			PreparedStatement updateState = con.prepareStatement(updateQuery);
			int result = updateState.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
}