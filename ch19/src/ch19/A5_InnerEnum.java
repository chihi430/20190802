package ch19;

class Customer {
	enum Gender {
		MALE, FEMALE
	}

	private String name;
	private Gender gen;

	Customer(String n, String g) {
		name = n;

		if (g.equals("man"))
			gen = Gender.MALE;
		else
			gen = Gender.FEMALE;
	}

	@Override
	public String toString() {
		if (gen == Gender.MALE)
			return "Thank you, Mr " + name;
		else
			return "Thank you, Mrs " + name;
	}

}

//열거형 값 (Enumerated Values)

public class A5_InnerEnum {
	public static void main(String[] args) {
		Customer cu1 = new Customer("Brown", "man");
		Customer cu2 = new Customer("Susan Hill", "woman");
		
		System.out.println(cu1);
		System.out.println(cu2);
	}

}

//case문에서는 표현의 간결함을 위해 DO 와 같이 '열거형 값'의 이름만 명시하기로 약소되어 있다.