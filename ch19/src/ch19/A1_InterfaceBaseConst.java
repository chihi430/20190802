package ch19;

interface Scale {
	int DO = 0;
	int MI = 2;
	int SO = 4;
	int TI = 6;
	int RE = 1;
	int FA = 3;
	int RA = 5;
	
	//굳이 new 하지않아도 Scale implements 된다 메서드 구현하는 것 아니기때문에
	//static변수이기때문에
	//상수가 final이다
}

public class A1_InterfaceBaseConst {

	public static void main(String[] args) {
		int sc = Scale.RE;

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
