package ch12;

public class B3_IndexOf {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String st1 = "AppleBananaOrange";
		int num1 = st1.indexOf("B");
		int num2 = st1.indexOf("O");
		
		String st2 = st1.substring(num1,num2);
		System.out.println(st2);
	}

}
