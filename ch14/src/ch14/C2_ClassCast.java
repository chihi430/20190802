package ch14;


class Board{} // 클래스 선언
class PBoard extends Board{} // 클래스 상속
public class C2_ClassCast {
	public static void main(String[] args) {
		
		Board pbd1 = new PBoard();
		PBoard pbd2 = (PBoard)pbd1; //Ok!
		
		System.out.println("..intermediate location..");
		Board ebd1 = new Board();
		PBoard ebd2 = (PBoard)ebd1;

	}

}
