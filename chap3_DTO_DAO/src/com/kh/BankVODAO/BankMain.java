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
			System.out.println("KH 은행입니다 무엇을하겠습니까?");

			System.out.print("1. 송금		");
			System.out.print("2. 입금		");
			System.out.print("3. 인출		");
			System.out.print("4. 잔액조회		");
			System.out.println("5. 종료		");
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
					System.out.println("종료 하겠습니다.");
					whileCheck = false;
					break;
				default :
					System.out.println("잘못 입력하셨습니다. 다시입력해주세요");
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
			// 보낼 계좌번호
			System.out.println("보내실 분의 계좌번호를 입력해주세요");
			while(checkinputAccount) {
				inputAccountNumber = S_sc.nextLine();
				
				idTrue = checkAccountNumber(inputAccountNumber);
				if (idTrue) {
					checkinputAccount = false;
				} else {
					System.out.println("일치하는 계좌번호가 없습니다. 다시입력해주세요");
				}
			}

			
			// 받을 계좌번호
			System.out.println("받으실 분의 계좌번호를 입력해주세요");
			while(checkoutputAccount) {
				outputAccountNumber = S_sc.nextLine();
				
				idTrue = checkAccountNumber(outputAccountNumber);
				if (idTrue) {
					checkoutputAccount = false;
				} else if (inputAccountNumber.equals(outputAccountNumber)) {
					System.out.println("보내는 계좌번호와 받는 계좌번호가 동일합니다. 다시입력해주세요");
				} else {
					System.out.println("일치하는 계좌번호가 없습니다. 다시입력해주세요");
				}
			}
			
			// 보낼 금액
			System.out.println("보낼 금액을 적어주세요");
			while(checkbalance) {
				insertBalance = N_sc.nextDouble();
				
				totalBalance = checkBalence(inputAccountNumber);
				if (insertBalance <= 0) {
					System.out.println("보낼 금액은 0원보다 커야 합니다. 다시입력해주세요");
				// 보내는 금액이 잔액보다 많은지 체크하기
				} else if (totalBalance < insertBalance) {
					System.out.println("보낼 금액이 잔액보다 큽니다. 다시 입력해주세요");
					System.out.println("잔액 : " + totalBalance);
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
