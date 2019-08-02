package ch15;

public class B2_StringEquality {
	public static void main(String[] args) {
		String str1 = new String("So Simple");
		String str2 = new String("So Simple");
		
		
		//String 클래스는 내용 비교를 하는 형태로 equals 메서드를 오버라이딩 하고 있음
		
		

		if(str1 ==str2)
			System.out.println("str1,str2 참조 대상 동일하다.");
		else
			System.out.println("str1,str2  참조 대상 다르다");
		

		if(str1.equals(str2))
			System.out.println("str1, str2  내용 동일하다");
		else
			System.out.println("str1,str2 내용 다르다");
	}

}
