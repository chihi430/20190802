package ch19;


interface Viewable1{
	public void showIt(String str);
}

class Viewer1 implements Viewable1{

	@Override
	public void showIt(String str) {
		System.out.println(str);
	}
	
}

public class D1_AtOverride {
	public static void main(String[] args) {
		Viewable1 view = new Viewer1();
		
		view.showIt("Hello Annotations");
	}


}

// 오버라이딩을 올바르게 했는지 컴파일러가 체크하게 한다.
// 오버라이딩 할 때 메서드 이름을 잘못적는 실수를 하는 경우가 많은데 이런점을 방지!