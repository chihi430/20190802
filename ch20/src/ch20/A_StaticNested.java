package ch20;

class Outer1{
	private static int num = 0;
	
	static class Nested1{ //<----- static네스티드 클래스
		void add(int n ) {num+=n;} //<----Outer클래스의 static 변수공유
	}
	
	static class Nested2{
		int get() {return num;}
	}
	
}
public class A_StaticNested {

	public static void main(String[] args) {
		Outer1.Nested1 nst1 = new Outer1.Nested1(); // <--- 인스턴스 생성
		nst1.add(5);

		Outer1.Nested2 nst2 = new Outer1.Nested2();
		System.out.println(nst2.get());
	}

}
//static 네스티드 클래스는 static 선언이 갖는 특성이 반영된 클래스이다.
//따라서 자신을 감싸는 외부 클래스의 인스턴스와 상관없이

//static네스티드 클래스의 인스턴스 생성이 가능하다.

///나만아는 클래스!
