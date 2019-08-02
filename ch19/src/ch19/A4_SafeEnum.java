package ch19;

enum Animal2{
	DOG,CAT
}
enum Person2 {
	MAN ,WOMAN
}

//열거형 값 (Enumerated Values)

public class A4_SafeEnum {
	public static void main(String[] args) {
		// 정상적인 메소드 홀출
		who(Person2.MAN);
		
		//비정상적 메소드 호출
		//who(Animal2.DOG);
	}
	
	public static void who(Person2 man) {

		switch (man) {
		case MAN:
			System.out.println("남성 손님입니다.");
			break;
		case WOMAN:
			System.out.println("여성 손님입니다.");
			break;
		}

	}
}



//case문에서는 표현의 간결함을 위해 DO 와 같이 '열거형 값'의 이름만 명시하기로 약소되어 있다.