package chap2_MVC;

public class SQLQuery {
	
	public String InsertCafesData() {
		
		String sql = "INSERT INTO cafes (cname, address, phone_number, operating_hours) VALUES (?, ?, ?, ?)";
		
		return sql;
	}
	
	public String UpdateMenuInfo() {
		
		String sql = "UPDATE cafes SET description = ? WHERE menu_id = ? AND cafe_id = ?";
		
		return sql;
	}
	
	public String UpdateCafeInfo() {
		String sql = "UPDATE menu SET  = operatng_hours = ? WHERE cafe_id = ?";
		
		return sql;
	}

	public String DeleteCafeInfo() {
		String sql = "DELETE FROM cafes WHERE cafe_id = ?";
		
		return sql;
	}

	public String DeleteMenuInfo() {
		String sql = "DELETE FROM menu WHERE menu_id = ?";
		
		return sql;
	}
}
