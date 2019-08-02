package ch20;

interface Calculate3{
	void cal(int a ,int b); // 매개변수 하나, 반환 형 void
}

public class E3_TwoParamAndReturn {
	public static void main(String[] args) {
		Calculate3 c;
		
		c = (a,b) -> System.out.println("aaa");
		c.cal(4, 3);
		
		c = (a,b) -> System.out.println(a-b);
		c.cal(4, 3);
		
		c = (a,b) -> System.out.println(a*b);
		c.cal(4, 3);
	}

}
