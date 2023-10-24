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
			
			System.out.println("�ȳ��ϼ��� �ֹ������Դϴ� ������ �Ͻðڽ��ϱ�?");
			System.out.print("1. ���� �ֹ��ϱ�		");
			System.out.print("2. �ֹ����� Ȯ���ϱ�		");
			System.out.println("3. �����ϱ�");
			
			choice = nSc.nextInt();
			System.out.println();
			
			switch(choice) {
			case 1 :
				System.out.println("�ֹ��� ī�並 �������ּ���");
				cafeList = cafeController.getCafeList();
				cafeView.showCafeList(cafeList);
				choice = nSc.nextInt();
				System.out.println();

				System.out.println("�ֹ��� �޴��� �������ּ���");
				menuList = menuController.getMenuList(choice);
				menuView.showMenuList(menuList);
				menuChoice = nSc.nextInt();
				System.out.println();
				
				System.out.println("�ֹ��� ������ �������ּ���");
				countChoice = nSc.nextInt();
				System.out.println();
				
				orderController.order(choice, menuChoice, countChoice);

				System.out.println("������ ���¹�ȣ�� �Է����ּ���");
				accountNumber = nSc.nextInt();
				
				break;
				
			case 2 :
				break;
			case 3:
				System.out.println("�����ϰڽ��ϴ�.");
				appEnd = true;
				break;
			default :
				System.out.println("�߸� �����߽��ϴ� �ٽ� ������ �ּ���");
				break;
			}
		}

		
	}
}
