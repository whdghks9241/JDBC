package com.kh.cafe;

import java.util.List;

public class cafeController {

	private cafeDAO dao;
	
	public cafeController(cafeDAO dao) {
	
		this.dao = dao;
		
	}
	
	public List<cafeDTO> getCafeList() {
		return dao.getAllCafeList(); 
	}
}
