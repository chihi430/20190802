package ch20;

interface Printable2 {  // '로컬 클래스'는 바로 위에서 소개한 '멤버 클래스와'상당부분 유사
	void print();		// 지역내에 정의된다는 점에서만 차이를 보임
}

class Papers2 {
	private String con;

	public Papers2(String s) {
		con = s;
	}

	public Printable2 getPrinter() {  
		class Printer2 implements Printable2 { //<-- 메서드 안으로 더 깊이 감췄다.
			public void print() {
				System.out.println(con);
			}
		}
		return new Printer2();
	}
}

public class B3_UseLocalInner {

	public static void main(String[] args) {

		Papers2 p = new Papers2("서류내용 : 행복합니다.");
		Printable2 prn = p.getPrinter();
		prn.print();

		// 멤버 클래스는 클래스의 정의를 감추어야 할 때 유용하게 사용이 된다.
		// 클래스 사용자 입장에서 Printable 인터페이스는 알지만 Printer 클래스는 모른다! 알 필요도 없다!

	}

}
