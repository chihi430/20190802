package ch15;

class MyText2 implements Cloneable {
	private String name;

	public MyText2(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}

class MyBook2 implements Cloneable {
	public MyText2 mt;

	public MyBook2(String str) {
		this.mt = new MyText2(str);
	}
	//정보를 수정함
	public void changeText(String str) {
		mt.setName(str);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// object 클래스의 clone 메소드 호출 결과를 얻음
		MyBook2 copy = (MyBook2) super.clone();

		//깊은 복사의 형태로 복사본을 수정
		copy.mt = (MyText2) mt.clone();

		//완성된 복사본의 참조를 반환
		return copy;
	}
		public void showText() {
		System.out.println(mt.getName());
	}
}

public class C4_DeepCopy2 {

	public static void main(String[] args) {
		MyBook2 org = new MyBook2("홍길동");
		MyBook2 cpy;

		try {
			// 인스턴스 복사
			cpy = (MyBook2) org.clone();

			// 인스턴스의 좌표 정보를 수정
			org.changeText("전우치");
			
			// 두 객체의 값이 같이 변경되었음을 확인
			org.showText();
			cpy.showText();

		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

}

// 접근 수준지시자를 prtecteed 에서 public 으로 바꾸기 위한 메서드 오버라이딩
// clone() 메서드를 사용하면 객체의 정보(멤버변수 값)가 같은 인스턴스가 또 생성되는 것이므로 ,
// 객체 지향 프로그램의 정보은닉, 객체보호 관점에서 위배 될 수 있음
// 그러므로 클래스 정의 시, clone 메서드의 호출을 허용하려면 Cloneable 인터페이스를 구현해야한다.
// Cloneable 인터페이스는 구현해야 할 추상 메서드가 없는 마커 인터페이스 이다.
