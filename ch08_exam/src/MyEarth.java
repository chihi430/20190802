class 사람 {
	String 이름;
	int 나이;
	int 키;

	// 사람{}; 이런 메소드가 굳이 적지 않아도 하나 생겨져 있다.
	void 인사() {
		System.out.println("안녕하세요");
	}
}
class MyEarth {

	public static void main(String[] args) {

		// '사람'이란 설계도(클래스)를 이용해 사람(객체) 만들기
		// 만들기 --> 객체를 메모리에 적재
		// 사람 --> 지구에 살게~

		사람 man1 = new 사람();
		man1.이름 = "홍길동";
		man1.나이 = 20;
		man1.키 = 180;

		System.out.println(man1.이름);
		System.out.println(man1.나이);
		System.out.println(man1.키);
		man1.인사();
	}

}
