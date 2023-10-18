package com.kh.oracledb.CRUD.pre;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// cafes insert �ۼ��ؼ� ���� ī�� �߰��ϱ�
public class insertExam {
	
	static String JDBCrul;
	static String userName;
	static String password;
	static Connection con;
	
	public static void main(String[] args) throws SQLException {

		JDBCrul = "jdbc:oracle:thin:@localhost:1521:xe";
		userName = "khcafe";
		password = "khcafe";
		
		con = DriverManager.getConnection(JDBCrul, userName, password);
		
//		cafes();
		book();
	}
	
	static void cafes() {
		// TODO Auto-generated method stub

		
		try {
			
			String insertSQL = "INSERT INTO cafes(cname, address, phone_number, operating_hours) "
					+ "VALUES (?, ?, ?, ?)";
			
			PreparedStatement ps = con.prepareStatement(insertSQL);
			
			insertcafes(ps, "�̸�����", "�ּҾ���", "02-115-1112", "���Ͽ���");
			insertcafes(ps, "�ָ������ϴ� ����", "�ּҾ���", "02-115-1112", "���Ͽ���");

			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void book() {

		try {
			String insertSQL = "INSERT INTO book (book_id, title, author, publication_year, isbn, genre, description, price, publication_date, created_date, is_available)"
					+ " values (?,?,?,?,?,?,?,?,?,?,?)" ;
			
			// TO_DATE(?, 'YYYY-MM-DD')

			PreparedStatement ps = con.prepareStatement(insertSQL);

			insertbook(ps, 15, "�������", "�������", 2020, "984-4951897", "MolRa", "�������", 10.99, "2020-10-10", "2023-10-10", "N");

			insertbook(ps, 16, "�������2", "�������2", 2020, "984-4951897", "MolRa", "�������2", 10.99, "2020-10-11", "2023-10-11", "N");

			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void insertcafes(PreparedStatement ps, String cname, String address, String phone_number, String operating_hours) throws SQLException {
		
		ps.setString(1, cname);
		ps.setString(2, address);
		ps.setString(3, phone_number);
		ps.setString(4, operating_hours);
		
		
		int rowsInsert = ps.executeUpdate();
		
		System.out.println(rowsInsert + "row �߰���");
	}

	static void insertbook(PreparedStatement ps, int book_id, String title, String author, int publication_year, String isbn, String genre, String description, double price, String publication_date, String created_date, String is_available ) throws SQLException {
		
		ps.setInt(1, book_id);
		ps.setString(2, title);
		ps.setString(3, author);
		ps.setInt(4, publication_year);
		ps.setString(5, isbn);
		ps.setString(6, genre);
		ps.setString(7, description);
		ps.setDouble(8, price);
		ps.setDate(9, Date.valueOf(publication_date));
		ps.setDate(10, Date.valueOf(created_date));
		ps.setString(11, is_available);
		
		ps.executeUpdate();
	}
}
