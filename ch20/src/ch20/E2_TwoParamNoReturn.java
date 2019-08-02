package ch20;

interface Calculate4 {
	int cal(int a, int b); // 매개변수 하나, 반환 형 void
}

public class E2_TwoParamNoReturn {
	public static void main(String[] args) {
		Calculate4 c;

		c = (a, b) -> {return a + b;};
		System.out.println(c.cal(4, 3));

		c = (a, b) -> a + b;
		System.out.println(c.cal(4, 3));
	}

}
