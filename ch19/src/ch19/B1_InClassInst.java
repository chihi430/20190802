package ch19;

class Person3{
	public static final Person3 MAN = new Person3();
	public static final Person3 WOMAN = new Person3();
	
	@Override
	public String toString() {
		return "I am a cat person";
	}
}




public class B1_InClassInst {
	public static void main(String[] args) {
		
		System.out.println(Person3.MAN); //toString 메서드의 반환 값 출력
		System.out.println(Person3.WOMAN);
	}
}

// 모든 열거형은 java.lang.Enum<E> 클래스를 상속한다.
// 그리고 Enum<E>는 Object 클래스를 상속한다.
// 이런측면에서 볼 때 열거형은 클래스이다.