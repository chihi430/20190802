package ch20;

interface PrintableD{
	void print(String s); // 매개변수 하나, 반환 형 void
}

public class E1_OneParamNoReturn {
	public static void main(String[] args) {
		PrintableD p;
		
		p = (String s) ->{System.out.println(s);}; // 줄입 없는 표현
		p.print("Lambda exp one.");
		
		p = (String s) ->System.out.println(s); // 중괄호 생략
		p.print("Lambda exp two.");
		
		p = (s) -> System.out.println(s); // 매개변수 형 생략
		p.print("Lambda exp three.");
		
		p = s -> System.out.println(s);
		p.print("Lambda exp four.");  //매개변수 소괄호 생략
	}

}
// 이 예제는 정렬 기능을 위해 객체를 생성하고 있다.
