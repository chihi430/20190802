package ch14;

public class C3_NullPointer {
	public static void main(String[] args) {
		
		String str = null;
		System.out.println(str); // null 출력 널값이 출력되면 
		int len = str.length(); // Exception! 예외 되는 부분.
		
		if(str != null) {
			len=str.length();
		}
	}
}
