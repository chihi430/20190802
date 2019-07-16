abstract class Unit{
	int HP;
	int MP;
	
	abstract void move();
	abstract void attack();
}
class Marine extends Unit{
	public Marine() {
		System.out.println("마린이 마린답게 생성됨");
	}

	void move() {
		System.out.println("한 칸 움직이기"); // 유닛의 공통기능
		System.out.println("두 발로 걷기"); // 저글링 답게 꾸민것		
	}
	
	void attack() {
		System.out.println("상대 HP 1감소시키기");// 유닛의 공통기
		System.out.println("총으로 쏘기"); // 저글링답게 꾸민 것		
	}
}
class Zergling extends Unit{
	Zergling() {
		System.out.println("저글링 생성됨");
	}
	
	void move() {
		System.out.println("한칸 움직이기");
		System.out.println("네 발로 걷기");		
	}

	void attack() {
		System.out.println("상대 HP 1 감소시키기");
		System.out.println("앞 발로 찍기");		
	}
	
}

public class Polymorphism4 {

	public static void main(String[] args) {
		Unit unit1 = new Marine();
		unit1.move();
		unit1.attack();
		System.out.println("============================");
		Unit unit2 = new Zergling();
		unit2.move();
		unit2.attack();
	}

}
