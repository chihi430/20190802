package ch14;

public class C3_NullPointer {
	public static void main(String[] args) {
		
		String str = null;
		System.out.println(str); // null ��� �ΰ��� ��µǸ� 
		int len = str.length(); // Exception! ���� �Ǵ� �κ�.
		
		if(str != null) {
			len=str.length();
		}
	}
}
