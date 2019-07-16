package ch14;

import java.util.Scanner;
public class A2_ExceptionCase6 {

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
		catch(Exception e) {
			e.getMessage();
		}		
		System.out.println("Good bye ~~!");
	}
	 

}
