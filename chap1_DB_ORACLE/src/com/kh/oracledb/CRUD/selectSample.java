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
		// 1. 드라이버 연결 : Oracle JDBC 드라이버 클래스 이름
		String driver = "oracle.jdbc.driver.OracleDriver";
		// 2. 오라클 내 컴퓨터 연결 : 데이터베이스 연결 정보
		// 								나의 ip주소:port번호 
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khbank";
		String password = "kh1234";
		
		Connection con = null;
		
		try {
			// 연결을 사용하여 쿼리 실행 또는 데이터베이스 작업 수행
			con = DriverManager.getConnection(url, user, password);
			System.out.println("데이터 베이스 연결 성공");
			
			// SELECT 쿼리
			String selectOuery = "SELECT * FROM BANK";
			PreparedStatement selectState = con.prepareStatement(selectOuery);
			ResultSet result = selectState.executeQuery();
			
			// result.next() : result 객체에서 다음 행(row)으로 이동, 다음행이 있으면 true반환, 그렇지 않으면 false 반환
			while(result.next()) {

//				khbank에 있는 bank 테이블 결과집합에서 account_id를 가져온다.
				int accountID = result.getInt("account_id");
				String accountName = result.getString("account_name");
				double balace = result.getDouble("balance");
				
				// accountNumber 들고오기
				String accountNumber = result.getString("account_Number");
				// branchName 들고오기
				String branchName = result.getString("branch_name");
				// lastTransctionDate 가져오기
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
		// 1. 드라이브 연결
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
		// 1. 드라이버 연결 : Oracle JDBC 드라이버 클래스 이름
				String driver = "oracle.jdbc.driver.OracleDriver";
				// 2. 오라클 내 컴퓨터 연결 : 데이터베이스 연결 정보
				// 								나의 ip주소:port번호 
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String user = "khbank";
				String password = "kh1234";
				
				Connection con = null;
				
				try {
					con = DriverManager.getConnection(url, user, password);
					// where 절 사용하여 조건추가
					String selectQuery = "SELECT * FROM bank WHERE account_number in (?,?)";
					PreparedStatement selectState = con.prepareStatement(selectQuery);
					String[] targerAN = {"1234567890","5555666777"};
					
					selectState.setString(1, targerAN[0]);
					selectState.setString(2, targerAN[1]);
					ResultSet result = selectState.executeQuery();
					
//					// 여기에 원하는 조건의 account_id 설정
//					int targetAID = 4;
//					
//					selectState.setInt(1, targetAID);
					if (result.isBeforeFirst()) {
							System.out.println("테이더 없음");
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
		// 1. 드라이브 연결
		String Driver = "oracle.jdbc.driver.OracleDrive";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "khcafe";
		String password = "khcafe";
		
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			
			String selectQuery = "SELECT c.cname 카페이름, c.address 카페주소, m.menu_name 메뉴 FROM menu m join cafes c on m.cafe_id = c.cafe_id";
			PreparedStatement selectState = con.prepareStatement(selectQuery);
			ResultSet result = selectState.executeQuery();
			
			while(result.next()) {
				String cafeName = result.getString("카페이름");
				String cafeAdress = result.getString("카페주소");
				String cafeMenu = result.getString("메뉴");
			
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
			
			String updateQuery = "update menu set description = '쓴맛' WHERE menu_id = 7";
			PreparedStatement updateState = con.prepareStatement(updateQuery);
			int result = updateState.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
}
