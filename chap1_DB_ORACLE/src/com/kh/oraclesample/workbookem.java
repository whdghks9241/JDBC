package com.kh.oraclesample;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class workbookem {
	
	static String Driver;
	static String url;
	static String user;
	static String password;
	static Connection con;
	static PreparedStatement insertState;
	
	static void dbconnect() {
		Driver = "oracle.jdbc.driver.OracleDrive";
		url = "jdbc:oracle:thin:@localhost:1521:xe";
		user = "workbook";
		password = "workbook";
		
		con = null;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		dbconnect();
//		studentInfo1();
//		studentInfo2();
		studentInfo3();
	}
	
	static void studentInfo1() {
		
		String selectQuery = "SELECT student_name 학생이름, student_address 주소지 FROM tb_student ORDER BY student_name";

		try {
			insertState = con.prepareStatement(selectQuery);
			ResultSet result = insertState.executeQuery();
			
			
			// result.next() : result 객체에서 다음 행(row)으로 이동, 다음행이 있으면 true반환, 그렇지 않으면 false 반환
			while(result.next()) {

				String studentName = result.getString("학생이름");
				String studentAddress = result.getString("주소지");
	
				
				System.out.println("학생이름 : " + studentName + "\n주소지 : " + studentAddress +"\n");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	static void studentInfo2() {
		
		String selectQuery = "Select student_name 학생이름, student_ssn 주민등록번호 FROM tb_student WHERE ABSENCE_YN = 'Y' order by student_ssn desc";
		
		try {
			insertState = con.prepareStatement(selectQuery);
			ResultSet result = insertState.executeQuery();
			
			while(result.next()) {
				String studentName = result.getString("학생이름");
				String studentSsn = result.getString("주민등록번호");
			
				System.out.println("학생이름 : " + studentName + "\n주민등록번호 : " + studentSsn + "\n");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	static void studentInfo3() {
		
		String selectQuery = "SELECT student_name 학생이름, student_no 학번, student_address 거주지주소 FROM tb_student WHERE  entrance_date < '2000'  and student_address like '%강원도%' or student_address like '%경기도%'";
		
		try {
			insertState = con.prepareStatement(selectQuery);
			ResultSet result = insertState.executeQuery();
			
			while(result.next()) {
				String studentName = result.getString("학생이름");
				String studentNo = result.getString("학번");
				String studentAddress = result.getString("거주지주소");
			
				System.out.println("학생이름 : " + studentName + "\n주민등록번호 : " + studentNo + "\n거주지주소 : " + studentAddress + "\n");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
