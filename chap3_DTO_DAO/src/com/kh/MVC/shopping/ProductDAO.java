package com.kh.MVC.shopping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

	private Connection connection;
	
	String url ="jdbc:oracle:thin:@localhost:1521:xe";
	String uername = "khcafe";
	String password = "khcafe";
	
	public ProductDAO () {
		try {
			connection = DriverManager.getConnection(url, uername, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public List<ProductDTO> getAllProducts() {
		
		List<ProductDTO> products = new ArrayList<>();
		
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM products");
			
			ResultSet result = ps.executeQuery();
			
			while(result.next()) {
				int product_id = result.getInt("product_id");
				String product_name = result.getString("product_name");
				String category = result.getString("category");
				double price = result.getDouble("price");
				int stock_quantity = result.getInt("stock_quantity");
				
				ProductDTO product = new ProductDTO(product_id, product_name, category, price, stock_quantity);
				products.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return products;
	}

}
