package ch17;

class Apple3 {
	public String toString() {
		return "I am an apple.";
	}
}

class Orange3 {
	public String toString() {
		return "I am an orange.";
	}
}

class Box3 {
	private Object ob;
	
	public void set(Object o) {
		ob = o;
	}

	public Object get() {
		return ob;
	}
}
public class A3_FruitAndBoxFalut {
	public static void main(String[] args) {
		Box3 aBox = new Box3();
		Box3 oBox = new Box3();

		//과일을 박스에 담는다.
		aBox.set("Apple");
		oBox.set("Orange");
		
		//박스에서 과일을 꺼낸다.
		Apple3 ap = (Apple3)aBox.get();
		Orange3 og = (Orange3)oBox.get();

		System.out.println(ap);
		System.out.println(og);
	}
}

// 어쩔수 없이 형 변환의 과정이 수반된다.
// 그리고 이는 컴파일러의 오류 발견 가능성을 낮추는 결과로 이어진다.
