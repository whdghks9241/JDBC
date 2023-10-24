package com.kh.cafe;

import java.util.List;

public class cafeView {

	public void showCafeList(List<cafeDTO> cafeList) {
		
		int count = 1;
		for (cafeDTO cd : cafeList) {
			System.out.print(count++ + ". " + cd.getName() + "	");
		}
		
		System.out.println();
	}
}
