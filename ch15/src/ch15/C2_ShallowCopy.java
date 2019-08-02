package ch15;

class Point2 implements Cloneable {
	private int xPos;
	private int yPos;

	public Point2(int x, int y) {
		xPos = x;
		yPos = y;
	}

	public void showPosition() {
		System.out.printf("[%d %d]", xPos, yPos);
		System.out.println();
	}

	public void changePos(int x, int y) {
		xPos = x;
		yPos = y;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}

class Rectangle2 implements Cloneable {
	public Point2 upperLeft;
	public Point2 lowerRight;

	public Rectangle2(int x1, int y1, int x2, int y2) {
		upperLeft = new Point2(x1, y1);
		lowerRight = new Point2(x2, y2);
	}

	//좌표 정보를 수정함
	public void changePos(int x1, int y1, int x2, int y2) {
		upperLeft.changePos(x1, y1);  //좌측 상단 좌표
		lowerRight.changePos(x2, y2); // 우측 하단 좌표

	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	//슈퍼 클론을 갔다가 바로만들어진얘를 바로 리턴했는데 새로 만들어서
	// 
	// 변수로 만들어서 
	
	// 직사각형 좌표 정보 출력
	public void showPosition() {
		System.out.println("좌측 상단: ");
		upperLeft.showPosition();

		System.out.println("우측 하단: ");
		lowerRight.showPosition();
		System.out.println();
	}
}

public class C2_ShallowCopy {

	public static void main(String[] args) {
		Rectangle2 org = new Rectangle2(1, 1, 9, 9);
		Rectangle2 cpy;

		try {
			// 인스턴스 복사
			cpy = (Rectangle2) org.clone();
			
			//한 인스턴스의 좌표 정보를 수정
			org.changePos(2, 2, 7, 7);
			org.showPosition();
			cpy.showPosition();

			if (org.equals(cpy))
				System.out.println("aaaaa.");
			else
				System.out.println("bbbbbb.");
			if (org.lowerRight.equals(cpy.lowerRight))
				System.out.println("ccccc.");
			else
				System.out.println("ddddd.");
			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

}
// 접근 수준지시자를 prtecteed 에서 public 으로 바꾸기 위한 메서드 오버라이딩

//clone() 메서드를 사용하면 객체의 정보(멤버변수 값)가 같은 인스턴스가 또 생성되는 것이므로 ,
// 객체 지향 프로그램의 정보은닉, 객체보호 관점에서 위배 될 수 있음
//그러므로 클래스 정의 시, clone 메서드의 호출을 허용하려면 Cloneable 인터페이스를 구현해야한다.
// Cloneable 인터페이스는 구현해야 할 추상 메서드가 없는 마커 인터페이스 이다.
