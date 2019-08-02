package ch19;

enum Person4{
	MAN,WOMAN;
	
	@Override
	public String toString() {
		return "I am a cat person";
	}
}

//열거형 값 (Enumerated Values)
//앞의 예제와 비교해보면,
//class가 enum으로 변경되었을 뿐 같은 결과가 나온다.

public class B2_EnumConst {
	public static void main(String[] args) {
		
		System.out.println(Person4.MAN);
		System.out.println(Person4.WOMAN);
	}

}

//모든 열거형은 java.lang.Enum<E> 클래스를 상속한다.
//그리고 Enum<E>는 Object 클래스를 상속한다.
//이런측면에서 볼 때 열거형은 클래스이다.