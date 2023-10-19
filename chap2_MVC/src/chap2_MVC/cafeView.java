package chap2_MVC;

import java.util.Scanner;

public class cafeView {
	// cafeModel 이라는 클래스를 가져오기 위해 맴버 변수로 카페 모델을 추가함
	public cafeModel model;

	// model 매개변수를 받을 수 있는 생성자를 만들어줘야함
	public cafeView(cafeModel model) {
		this.model = model;
	}
	
	// name, address, phone_number, operating_hours
	public void addCafeName() {
		Scanner sc = new Scanner(System.in);
		System.out.println("카페 정보를 입력하세요.");
		System.out.println("상호명 : ");
		String name = sc.nextLine();
		
		System.out.println("카페주소 : ");
		String address = sc.nextLine();
		
		System.out.println("카페 연락처 : ");
		String phone_number = sc.nextLine();
		
		System.out.println("영업시간 : ");
		String operating_hours = sc.nextLine();
		
		// 카페 모델에서 insertCafe라는 메서드를 가져와야함.
		model.insertCafe(name, address, phone_number, operating_hours);
		
		System.out.println("카페가 성공적으로 추가되었습니다.");
		
	}
	
	public void updateMenu() {
		Scanner sc = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		System.out.println("메뉴 설명을 업데이트 하세요.");
		System.out.print("메뉴 ID : ");
		int menuid = sc.nextInt();
		System.out.print("카페 ID : ");
		int cafeid = sc.nextInt();
		System.out.print("수정할 메뉴 설명 : ");
		String description = sc2.nextLine();
		
		// 메뉴에 있는 UpdateMenu를 가져와 수정내용 추가하기
		model.updateMenu(description, menuid, cafeid);
		
		System.out.println("메뉴 정보가 수정되었습니다.");
	}	
	
	public void updateCafes() {
		Scanner sc = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		
		System.out.println("카페 영업시간을 업데이트 하세요");
		System.out.print("카페 ID : ");
		int cafeid = sc.nextInt();
		System.out.print("변경할 영업 시간 : ");
		String operating_hours = sc2.nextLine();
		
		model.updateCafe(operating_hours, cafeid);

		System.out.println("카페 정보가 수정되었습니다.");
	}
	
	public void deleteCafes() {
		Scanner sc = new Scanner(System.in);
		System.out.println("카페를 삭제 하겠습니다");
		System.out.print("삭제할 카페의 ID를 입력하세요 : ");
		int cafeid = sc.nextInt();
		
		model.deleteCafe(cafeid);
		
		System.out.println("카페 정보가 삭제되었습니다.");
	}
	
	public void deleteMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("메뉴를 삭제 하겠습니다");
		System.out.print("삭제할 메뉴의 ID를 입력하세요 : ");
		int menuid = sc.nextInt();
		
		model.deletMenu(menuid);
		
		System.out.println("메뉴 정보가 삭제되었습니다.");
	}
}