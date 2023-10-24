import java.util.List;
import java.util.Scanner;

import com.kh.cafe.cafeController;
import com.kh.cafe.cafeDAO;
import com.kh.cafe.cafeDTO;
import com.kh.cafe.cafeView;
import com.kh.cafe.menu.menuController;
import com.kh.cafe.menu.menuDAO;
import com.kh.cafe.menu.menuDTO;
import com.kh.cafe.menu.menuView;
import com.kh.cafe.order.orderController;
import com.kh.cafe.order.orderDAO;
import com.kh.cafe.order.orderView;

public class Main {

	
	public static void main(String[] args) {
		
		cafeDAO cafeDao = new cafeDAO();
		cafeController cafeController = new cafeController(cafeDao);
		cafeView cafeView = new cafeView();
		List<cafeDTO> cafeList;
		
		menuDAO menuDao = new menuDAO();
		menuController menuController = new menuController(menuDao);
		menuView menuView = new menuView();
		List<menuDTO> menuList;
		
		orderDAO orderDao = new orderDAO();
		orderController orderController = new orderController(orderDao);
		orderView orderView = new orderView();
		
		int choice, menuChoice, countChoice, addorder, accountNumber;
		
		Scanner sSc = new Scanner(System.in);
		Scanner nSc = new Scanner(System.in);

		boolean appEnd = false;
		
		while(!appEnd) {
			
			System.out.println("안녕하세요 주문어플입니다 무엇을 하시겠습니까?");
			System.out.print("1. 음료 주문하기		");
			System.out.print("2. 주문내역 확인하기		");
			System.out.println("3. 종료하기");
			
			choice = nSc.nextInt();
			System.out.println();
			
			switch(choice) {
			case 1 :
				System.out.println("주문할 카페를 선택해주세요");
				cafeList = cafeController.getCafeList();
				cafeView.showCafeList(cafeList);
				choice = nSc.nextInt();
				System.out.println();

				System.out.println("주문할 메뉴를 선택해주세요");
				menuList = menuController.getMenuList(choice);
				menuView.showMenuList(menuList);
				menuChoice = nSc.nextInt();
				System.out.println();
				
				System.out.println("주문할 개수를 선택해주세요");
				countChoice = nSc.nextInt();
				System.out.println();
				
				orderController.order(choice, menuChoice, countChoice);

				System.out.println("결제할 계좌번호를 입력해주세요");
				accountNumber = nSc.nextInt();
				
				break;
				
			case 2 :
				break;
			case 3:
				System.out.println("종료하겠습니다.");
				appEnd = true;
				break;
			default :
				System.out.println("잘못 선택했습니다 다시 선택해 주세요");
				break;
			}
		}

		
	}
}
