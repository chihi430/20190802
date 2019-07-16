interface Cry {
	void cry();
}
class Cat implements Cry{
	public void cry() {
		System.out.println("야옹");
	}
}
class Dog implements Cry{
	public void cry() {
		System.out.println("멍멍");
	}
}

public class CheckCry {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cry test1 = new Cat();
		Cry test2 = new Dog();//test2의 객체와 Dog class가 맞는지 확인 필요하다
		
		if(test2 instanceof Dog) { // 객체 test 와 Dog class와 맞는 선언이 일치할때 true
			test2.cry();
		}
		else if(test1 instanceof Cat) {
			System.out.println("고양이가 아닙니다.");
		}

	}

}
