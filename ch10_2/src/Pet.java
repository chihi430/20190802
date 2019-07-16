class Animal {

	String name;
	int age;

	void printPet() {
		System.out.println("이름 : " + name);
		System.out.println("나이 : " + age);
	}
}

class Cat extends Animal {
	String variety;

	void printPet() {
		super.printPet();
		System.out.println("종류 : " + variety);
	}
}

public class Pet {

	public static void main(String[] args) {
		Cat cat = new Cat();
		cat.name = "양순이";
		cat.age = 5;
		cat.variety = "페르시안";
		cat.printPet();

	}

}

//  오버로딩 : 대등한 관계 똑같은 (메소드여러개 파라미터가 달라 예 int랑 double 등등 같이 쓰고싶다)
// 오버라이딩 : 위에서 아래로 덮어 쓰는것(부모와 나와의 관계에 덮어 쓰는것)
