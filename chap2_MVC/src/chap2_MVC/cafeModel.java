package chap2_MVC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class cafeModel {

	String Url = "jdbc:oracle:thin:@localhost:1521:xe";
	String username = "khcafe";
	String password = "khcafe";
	
	Connection con;
	
	SQLQuery query = new SQLQuery();
	
	public void insertCafe(String name, String address, String phone_number, String operating_hours) {

		try {
			con = DriverManager.getConnection(Url, username, password);
			
			String sql = query.InsertCafesData();
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, address);
			ps.setString(3, phone_number);
			ps.setString(4, operating_hours);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateMenu(String description, int menuid , int cafeid) {
		
		try {
			con = DriverManager.getConnection(Url, username, password);
			
			String sql = query.UpdateMenuInfo();
		
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1, description);
			st.setInt(2, menuid);
			st.setInt(3, cafeid);
			
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateCafe(/*매개변수 추가하기*/String operating_hours, int cafeid) {
		
		try {
			con = DriverManager.getConnection(Url, username, password);
			
			String sql = query.UpdateCafeInfo();
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, operating_hours);
			ps.setInt(2, cafeid);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteCafe(/*매개변수 자리*/int cafeid) {
		try {
			con = DriverManager.getConnection(Url, username, password);
			
			String sql = query.DeleteCafeInfo();
			
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, cafeid);
			st.executeUpdate();
					
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deletMenu(int menuid) {
		try {
			con = DriverManager.getConnection(Url, username, password);
			
			String sql = query.DeleteMenuInfo();
			
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, menuid);

			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}