package chap2_MVC;

public class Main {

	public static void main(String[] args) {
	
		cafeModel model = new cafeModel();
		cafeView view = new cafeView(model);
		
		cafeController controller = new cafeController(model, view);
		
//		view.addCafeName();
//		view.updateMenu();
//		view.updateCafes();
//		view.deleteCafes();
//		view.deleteMenu();
	}
}
