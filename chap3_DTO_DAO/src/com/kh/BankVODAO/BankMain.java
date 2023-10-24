package com.kh.BankVODAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.kh.userVODAO.UserDAO;

public class BankMain {

	
	static String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
	static String dbUserName = "khbank";
	static String dbPassword = "kh1234"; 
	
	static Connection connection;
	
	static BankDAO bankdao;
	
	public static void main(String[] args) {
		
		BankMain bm = new BankMain();
		
		boolean whileCheck  = true;
		
		Scanner sc = new Scanner(System.in);
		int choice;
		
		while(whileCheck) {
			System.out.println("KH �����Դϴ� �������ϰڽ��ϱ�?");

			System.out.print("1. �۱�		");
			System.out.print("2. �Ա�		");
			System.out.print("3. ����		");
			System.out.print("4. �ܾ���ȸ		");
			System.out.println("5. ����		");
			choice = sc.nextInt();
		
			switch (choice) {
				case 1 :
					bm.remittanceBalance();
					System.out.println();
					break;
				case 2 :
					System.out.println();
					break;
					
				case 3 :
					System.out.println();
					break;
				case 4 :
					System.out.println();
					break;
					
				case 5 :
					System.out.println("���� �ϰڽ��ϴ�.");
					whileCheck = false;
					break;
				default :
					System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ��Է����ּ���");
					System.out.println();
					break;
			}

		}
	}
	
	public void remittanceBalance() {
		try {
			connection = DriverManager.getConnection(jdbcURL, dbUserName, dbPassword);
			
			bankdao = new BankDAO(connection);

			Scanner S_sc = new Scanner(System.in);
			Scanner N_sc = new Scanner(System.in);

			boolean checkinputAccount = true;
			boolean checkoutputAccount = true;
			boolean checkbalance = true;
			
			boolean idTrue;
			
			String inputAccountNumber = null;
			String outputAccountNumber = null;

			double insertBalance = 0;
			double totalBalance;
			// ���� ���¹�ȣ
			System.out.println("������ ���� ���¹�ȣ�� �Է����ּ���");
			while(checkinputAccount) {
				inputAccountNumber = S_sc.nextLine();
				
				idTrue = checkAccountNumber(inputAccountNumber);
				if (idTrue) {
					checkinputAccount = false;
				} else {
					System.out.println("��ġ�ϴ� ���¹�ȣ�� �����ϴ�. �ٽ��Է����ּ���");
				}
			}

			
			// ���� ���¹�ȣ
			System.out.println("������ ���� ���¹�ȣ�� �Է����ּ���");
			while(checkoutputAccount) {
				outputAccountNumber = S_sc.nextLine();
				
				idTrue = checkAccountNumber(outputAccountNumber);
				if (idTrue) {
					checkoutputAccount = false;
				} else if (inputAccountNumber.equals(outputAccountNumber)) {
					System.out.println("������ ���¹�ȣ�� �޴� ���¹�ȣ�� �����մϴ�. �ٽ��Է����ּ���");
				} else {
					System.out.println("��ġ�ϴ� ���¹�ȣ�� �����ϴ�. �ٽ��Է����ּ���");
				}
			}
			
			// ���� �ݾ�
			System.out.println("���� �ݾ��� �����ּ���");
			while(checkbalance) {
				insertBalance = N_sc.nextDouble();
				
				totalBalance = checkBalence(inputAccountNumber);
				if (insertBalance <= 0) {
					System.out.println("���� �ݾ��� 0������ Ŀ�� �մϴ�. �ٽ��Է����ּ���");
				// ������ �ݾ��� �ܾ׺��� ������ üũ�ϱ�
				} else if (totalBalance < insertBalance) {
					System.out.println("���� �ݾ��� �ܾ׺��� Ů�ϴ�. �ٽ� �Է����ּ���");
					System.out.println("�ܾ� : " + totalBalance);
				} else {
					checkbalance = false;
				}
			}
			
			bankdao.updateBalence(inputAccountNumber, outputAccountNumber, insertBalance);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void selectBalace() {
		bankdao.selectBalance();
	}
	
	public boolean checkAccountNumber(String AccountNumber) throws SQLException {
		
		connection = DriverManager.getConnection(jdbcURL, dbUserName, dbPassword);
		
		String sql = "SELECT * FROM BANK WHERE account_number = ?";
		
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setString(1, AccountNumber);
		
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			int count = rs.getInt(1);
			return count > 0;
		} else {
			return false;
		}
	}
	
	public double checkBalence(String AccountNumber) throws SQLException {
		
		connection = DriverManager.getConnection(jdbcURL, dbUserName, dbPassword);
		
		String sql = "SELECT balance FROM BANK WHERE account_number = ?";
		
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setString(1, AccountNumber);
		
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			double balance = rs.getDouble(1);
			return balance;
		} else {
			return 0;
		}
	}
}
