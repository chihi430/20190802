package ch17;

class Box8<T>{ 
	private T ob; 
	public void set(T o) {		
		ob = o;
	}
	public T get() {
		return ob;
	}
}
public class B2_PrimitivesAndGeneric {
	public static void main(String[] args) {
		Box8<Integer> iBox = new Box8<Integer>();
		// 래퍼클래스(Integer)가 와야한다.

		iBox.set(125);		 	// 오토 박싱 진행
		int num = iBox.get();   // 오토 언박싱 진행
		
		System.out.println(num);
	}
}
// 타입 인자로 기본 자료형이 올 수 없으므로 컴파일 오류 발생
// Box<int> box = new Box<int>(); 타입 인자로 기본 자료형이 올 수 없으므로 컴파일 오류 발생
// 래퍼클래스가 필요한 이유!