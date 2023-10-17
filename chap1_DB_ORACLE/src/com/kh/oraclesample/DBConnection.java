package com.kh.oraclesample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	public static void main(String[] args) {
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
			
			int a = result.getFetchSize();

			// result.next() : result ��ü���� ���� ��(row)���� �̵�, �������� ������ true��ȯ, �׷��� ������ false ��ȯ
			
			while(result.next()) {
				System.out.println(1);
			}
//			while(result.next()) {
//
////				khbank�� �ִ� bank ���̺� ������տ��� account_id�� �����´�.
//				int accountID = result.getInt("account_id");
//				String accountName = result.getString("account_name");
//				double balace = result.getDouble("balance");
//				System.out.println("ACCOUNT_ID : " + accountID);	
//				
//				// accountNumber �������
//				String accountNumber = result.getString("account_name");
//				// branchName �������
//				String branchName = result.getString("branch_name");
//				// lastTransctionDate ��������
////				String lastTransctionDate = Date(result.getString("last_Transction_Date"), "YYYY-mm-dd");
//			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}