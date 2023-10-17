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
		
		String selectQuery = "SELECT student_name �л��̸�, student_address �ּ��� FROM tb_student ORDER BY student_name";

		try {
			insertState = con.prepareStatement(selectQuery);
			ResultSet result = insertState.executeQuery();
			
			
			// result.next() : result ��ü���� ���� ��(row)���� �̵�, �������� ������ true��ȯ, �׷��� ������ false ��ȯ
			while(result.next()) {

				String studentName = result.getString("�л��̸�");
				String studentAddress = result.getString("�ּ���");
	
				
				System.out.println("�л��̸� : " + studentName + "\n�ּ��� : " + studentAddress +"\n");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	static void studentInfo2() {
		
		String selectQuery = "Select student_name �л��̸�, student_ssn �ֹε�Ϲ�ȣ FROM tb_student WHERE ABSENCE_YN = 'Y' order by student_ssn desc";
		
		try {
			insertState = con.prepareStatement(selectQuery);
			ResultSet result = insertState.executeQuery();
			
			while(result.next()) {
				String studentName = result.getString("�л��̸�");
				String studentSsn = result.getString("�ֹε�Ϲ�ȣ");
			
				System.out.println("�л��̸� : " + studentName + "\n�ֹε�Ϲ�ȣ : " + studentSsn + "\n");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	static void studentInfo3() {
		
		String selectQuery = "SELECT student_name �л��̸�, student_no �й�, student_address �������ּ� FROM tb_student WHERE  entrance_date < '2000'  and student_address like '%������%' or student_address like '%��⵵%'";
		
		try {
			insertState = con.prepareStatement(selectQuery);
			ResultSet result = insertState.executeQuery();
			
			while(result.next()) {
				String studentName = result.getString("�л��̸�");
				String studentNo = result.getString("�й�");
				String studentAddress = result.getString("�������ּ�");
			
				System.out.println("�л��̸� : " + studentName + "\n�ֹε�Ϲ�ȣ : " + studentNo + "\n�������ּ� : " + studentAddress + "\n");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

}