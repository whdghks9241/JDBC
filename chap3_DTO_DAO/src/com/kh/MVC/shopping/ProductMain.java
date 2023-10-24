package com.kh.MVC.shopping;

import java.util.List;
import java.util.Scanner;

public class ProductMain {

	public static void main(String[] args) {
		ProductDAO dao = new ProductDAO();
		ProductController controller = new ProductController(dao);
		ProductView view = new ProductView();
		
		List<ProductDTO> products = controller.getAllProducts();
		
		Scanner sc = new Scanner(System.in);
		
		// ����īƮ �ν��Ͻ� �����ڸ�
		ShoppingCart cart = new ShoppingCart();
		
		// ���� īƮ �ν��Ͻ� ���� �ڸ�
		int choice = sc.nextInt();
		
		switch (choice) {
			case 1:
				// ��� ��ǰ ����Ʈ ��������
				view.showProductList(products);
				break;
			case 2:
				// �������� ��� �������
				double totalPrice = controller.calculateTotalPrice(products);
				view.showTotalPrice(totalPrice);
				break;
			case 3:
				// ��ǰ �߰�
				System.out.println("��ٱ��Ͽ� �߰��� ��ǰ�� ID�� �Է��ϼ���. : ");
				// ��ǰ id�Է¹ޱ�
				int productId = sc.nextInt();
				// ���� for���� if���� Ȱ���ؼ� ��ǰ ���
				for(ProductDTO p : products) {
					if (p.getProduct_id() == productId) {
						cart.addCart(p);
						System.out.println(p.getProduct_name() + " ��ٱ��Ͽ� ��ҽ��ϴ�.");
						break;
					}
				}
				
			case 4:
				// ��ǰ ����
				System.out.println("��ٱ��Ͽ��� ������ ��ǰ�� ID�� �Է��ϼ���");
				int removeProductId = sc.nextInt();
				for (ProductDTO r : cart.getCartList()/*��ٱ��� ���*/) {
					if (r.getProduct_id() == removeProductId) {
						cart.removeGoods(r);
						break;
					}
				}
				
			case 5:
				// ����
				UserPay pay = new UserPay(cart);
				boolean payResult = pay.payment();
				if (payResult) {
					// ������ �Ϸ�Ǹ� cart ����
					
				}
				break;
			default :
				System.out.println("�߸��� �����Դϴ�.");
		
		}
	}
}
