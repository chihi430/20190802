
abstract class Animal{
	int age;
	abstract void cry(); // 추상 메소드가 있으면 추상 클래스로 해줘야한다.
}

class Dog extends Animal{ // 다른 메소드에서 참조하려고 하면 꼭 그 메소드를 완성 시켜한다.
	void cry() {
		System.out.println("멍멍");
	}
}

class Cat extends Animal{
	void cry() {
		System.out.println("야옹");
	}
}
class Pig extends Animal{
	void cry() {
		System.out.println("야옹");
	}
}

public class ch10_4 {
	public static void main(String[] args) {
		Dog dog = new Dog();
		dog.cry();
		
		Cat cat = new Cat();
		cat.cry();		
	}
}
