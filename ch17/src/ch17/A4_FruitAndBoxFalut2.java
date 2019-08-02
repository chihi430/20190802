package ch17;

class Apple4 {
	public String toString() {
		return "I am an apple.";
	}
}

class Orange4 {
	public String toString() {
		return "I am an orange.";
	}
}

class Box4 {
	private Object ob;
	
	public void set(Object o) {
		ob = o;
	}

	public Object get() {
		return ob;
	}
}
public class A4_FruitAndBoxFalut2 {
	public static void main(String[] args) {
		Box4 aBox = new Box4();
		Box4 oBox = new Box4();

		//과일을 박스에 담는다.클래스를 오브젝트에 답는다
		aBox.set("Apple");
		oBox.set("Orange");
		
		//Object(박스)에서 과일을 꺼낸다.
		System.out.println(aBox.get());
		System.out.println(oBox.get());
	}
}

// 프로그래머의 실수가 실행과정에서 조차 발견되지 않을 수 있다. 
// 불편함이라 하면 상자에서 물건을 꺼낼 때 형 변환을 해야한다는 것이고,
// 문제점이라고 하면 프로그래머가 실수를 해도 그 실수가 드러나지 않을 수도 있다는 것이다.
