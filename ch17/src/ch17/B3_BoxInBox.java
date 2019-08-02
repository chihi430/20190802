package ch17;

class Box9<T>{ 
	private T ob; 
	public void set(T o) {		
		ob = o;
	}
	public T get() {
		return ob;
	}
}
public class B3_BoxInBox {
	public static void main(String[] args) {
		Box9<String> sBox = new Box9<String>();
		sBox.set("I am so happy");

		Box9<Box9<String>> wBox = new Box9<>();
		wBox.set(sBox);
		
		Box9<Box9<Box9<String>>> zBox = new Box9<>();
		zBox.set(wBox);
		
		System.out.println(zBox.get().get().get());
	}
}
// 타입 인자로 기본 자료형이 올 수 없으므로 컴파일 오류 발생
// Box<int> box = new Box<int>(); 타입 인자로 기본 자료형이 올 수 없으므로 컴파일 오류 발생
// 래퍼클래스가 필요한 이유!