package ch19;

enum Scale1{
	DO,RE,MI,FA,SO,RA,TI
}
//열거형 값 (Enumerated Values)

public class A3_SimpleEnum {
	public static void main(String[] args) {
		int sc = Scale.DO;
		System.out.println(sc);

		switch (sc) {
		case Scale.DO:
			System.out.println("도~");
			break;
		case Scale.RE:
			System.out.println("레~");
			break;
		case Scale.MI:
			System.out.println("미~");
			break;
		case Scale.FA:
			System.out.println("파~");
			break;
		default:
			System.out.println("솔~ 라~ 시~");

		}
	}
}



//case문에서는 표현의 간결함을 위해 DO 와 같이 '열거형 값'의 이름만 명시하기로 약소되어 있다.