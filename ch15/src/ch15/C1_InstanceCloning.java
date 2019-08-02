package ch15;

class Point1 implements Cloneable{
	private int xPos;
	private int yPos;
	
	public Point1(int x, int y) {
		xPos = x;
		yPos = y;
	}
	
	public void showPosition() {
		System.out.printf("[%d, %d]",xPos,yPos);
		System.out.println();
		
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
}

public class C1_InstanceCloning {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point1 org = new Point1(3,5);
		Point1 cpy;
		try {
			cpy = (Point1)org.clone();
			org.showPosition();
			cpy.showPosition();
			if(org.equals(cpy))
				System.out.println("aaaaa");
			else
				System.out.println("bbbbb");
		}catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

	}

}
// 접근 수준지시자를 prtecteed 에서 public 으로 바꾸기 위한 메서드 오버라이딩

//clone() 메서드를 사용하면 객체의 정보(멤버변수 값)가 같은 인스턴스가 또 생성되는 것이므로 ,
// 객체 지향 프로그램의 정보은닉, 객체보호 관점에서 위배 될 수 있음
//그러므로 클래스 정의 시, clone 메서드의 호출을 허용하려면 Cloneable 인터페이스를 구현해야한다.
// Cloneable 인터페이스는 구현해야 할 추상 메서드가 없는 마커 인터페이스 이다.
