package com.kh.cafe.menu;

import java.util.List;


public class menuView {

	public void showMenuList(List<menuDTO> menuList) {
		
		int count = 1;
		for (menuDTO md : menuList) {
			System.out.print(count++ + ". " + md.getMenu_name() + "	");
		}
		
		System.out.println();
	}
}
