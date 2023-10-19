package chap2_MVC;

import java.util.Scanner;

public class cafeView {
	// cafeModel �̶�� Ŭ������ �������� ���� �ɹ� ������ ī�� ���� �߰���
	public cafeModel model;

	// model �Ű������� ���� �� �ִ� �����ڸ� ����������
	public cafeView(cafeModel model) {
		this.model = model;
	}
	
	// name, address, phone_number, operating_hours
	public void addCafeName() {
		Scanner sc = new Scanner(System.in);
		System.out.println("ī�� ������ �Է��ϼ���.");
		System.out.println("��ȣ�� : ");
		String name = sc.nextLine();
		
		System.out.println("ī���ּ� : ");
		String address = sc.nextLine();
		
		System.out.println("ī�� ����ó : ");
		String phone_number = sc.nextLine();
		
		System.out.println("�����ð� : ");
		String operating_hours = sc.nextLine();
		
		// ī�� �𵨿��� insertCafe��� �޼��带 �����;���.
		model.insertCafe(name, address, phone_number, operating_hours);
		
		System.out.println("ī�䰡 ���������� �߰��Ǿ����ϴ�.");
		
	}
	
	public void updateMenu() {
		Scanner sc = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		System.out.println("�޴� ������ ������Ʈ �ϼ���.");
		System.out.print("�޴� ID : ");
		int menuid = sc.nextInt();
		System.out.print("ī�� ID : ");
		int cafeid = sc.nextInt();
		System.out.print("������ �޴� ���� : ");
		String description = sc2.nextLine();
		
		// �޴��� �ִ� UpdateMenu�� ������ �������� �߰��ϱ�
		model.updateMenu(description, menuid, cafeid);
		
		System.out.println("�޴� ������ �����Ǿ����ϴ�.");
	}	
	
	public void updateCafes() {
		Scanner sc = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		
		System.out.println("ī�� �����ð��� ������Ʈ �ϼ���");
		System.out.print("ī�� ID : ");
		int cafeid = sc.nextInt();
		System.out.print("������ ���� �ð� : ");
		String operating_hours = sc2.nextLine();
		
		model.updateCafe(operating_hours, cafeid);

		System.out.println("ī�� ������ �����Ǿ����ϴ�.");
	}
	
	public void deleteCafes() {
		Scanner sc = new Scanner(System.in);
		System.out.println("ī�並 ���� �ϰڽ��ϴ�");
		System.out.print("������ ī���� ID�� �Է��ϼ��� : ");
		int cafeid = sc.nextInt();
		
		model.deleteCafe(cafeid);
		
		System.out.println("ī�� ������ �����Ǿ����ϴ�.");
	}
	
	public void deleteMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("�޴��� ���� �ϰڽ��ϴ�");
		System.out.print("������ �޴��� ID�� �Է��ϼ��� : ");
		int menuid = sc.nextInt();
		
		model.deletMenu(menuid);
		
		System.out.println("�޴� ������ �����Ǿ����ϴ�.");
	}
}