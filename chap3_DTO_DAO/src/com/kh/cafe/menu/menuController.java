package com.kh.cafe.menu;

import java.util.List;

public class menuController {

	private menuDAO dao;
	
	public menuController(menuDAO dao) {
		this.dao = dao;
	}
	
	public List<menuDTO> getMenuList(int cafeId) {
		return dao.getMenuList(cafeId);
	}
}
