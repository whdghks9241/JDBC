package com.kh.oracledb.CRUD.pre;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class bookAllExam {
    // �����ͺ��̽� ���� �Ű� ����
	public static void main(String[] args) throws SQLException {
	    String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	    String username = "khcafe";
	    String password = "kh1234";
	
	    try {

	        // ���� 1 ȸ�����ͺ��̽� ���� ����
	        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
	
	        String insertQuery = "INSERT INTO book (book_id, title, author, publication_year, isbn, genre, description, price, publication_date, updated_date, is_available) "
	                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	        PreparedStatement insertPs = connection.prepareStatement(insertQuery);
	        insertBook(insertPs,51,"������ ���1", "����������",2023,"978-1234567111","�ɸ���","���� ���� 1",8.99,"2023-10-18","Y");
	        insertBook(insertPs,52,"������ ���1", "����������",2023,"978-1234567111","�ɸ���","���� ���� 1",8.99,"2023-10-18","Y");

	        String selectQuery = "SELECT * FROM book WHERE book_id = ?";
	        PreparedStatement selectPs = connection.prepareStatement(selectQuery);
	        selectPs.setInt(1, 50);
	        
	        ResultSet rs = selectPs.executeQuery();
	        while (rs.next()) {
                int bookId = rs.getInt("book_id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                int publicationYear = rs.getInt("publication_year");
                String isbn = rs.getString("isbn");
                String genre = rs.getString("genre");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                Date pubDate = rs.getDate("publication_date");
                String isAvailable = rs.getString("is_available");

                System.out.println("���� ����:");
                System.out.println("���� ID: " + bookId);
                System.out.println("����: " + title);
                System.out.println("����: " + author);
                System.out.println("���� �⵵: " + publicationYear);
                System.out.println("ISBN: " + isbn);
                System.out.println("�帣: " + genre);
                System.out.println("����: " + description);
                System.out.println("����: " + price);
                System.out.println("���� ��¥: " + pubDate);
                System.out.println("��� ���� ����: " + isAvailable);
            }

	        selectPs.close();
            connection.close();
	        
	    } catch ( SQLException  e) {
	        e.printStackTrace();
	    }
	}


//INSERT INTO book (book_id, title, author, publication_year, isbn, genre, description, price, publication_date, is_available
	public static void insertBook(PreparedStatement ps, int bookId, String title, String author,
									int publicationyear, String isbn, String genre, String description, double price, String pdate, String isAvailable) throws SQLException {
		ps.setInt(1, bookId);
		ps.setString(2, title);
		ps.setString(3, author);
		ps.setInt(4, publicationyear);
		ps.setString(5, isbn);
		ps.setString(6, genre);
		ps.setString(7, description);
		ps.setDouble(8, price);
		ps.setString(9, pdate);
		ps.setString(10, isAvailable);
		
		int rows = ps.executeUpdate();
		
		if (rows > 0) {
			System.out.println("å " + title + " �����ϴ�.");
		} else {
			System.out.println("���� å�� �����ϴ�.");
		}
	}
}
