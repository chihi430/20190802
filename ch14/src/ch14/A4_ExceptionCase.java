package ch14;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.omg.PortableInterceptor.TRANSPORT_RETRY;

public class A4_ExceptionCase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner kb = new Scanner(System.in);
		
		try {
		System.out.println("a/b....a?");
		int n1=kb.nextInt();
		
		System.out.println("a/b....b?");
		int n2=kb.nextInt();
		
		
		System.out.printf("%d / %d = %d \n",n1,n2,n1/n2);
		System.out.println("Good bye~~!");
		}
		catch(ArithmeticException e) {
			String str = e.getMessage();
			if(str.equals("/ by zeor"))
			System.out.println("분모에 0입력 오류");
		}
		catch(InputMismatchException e) {
			System.out.println("문자입력오류");
		}
		System.out.println("Good bye ~~!");
	}
	 

}
