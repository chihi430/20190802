package ch14;

import java.util.Scanner;

public class A1_ExceptionCase1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner kb = new Scanner(System.in);
		System.out.println("a/b....a?");
		int n1=kb.nextInt();
		
		System.out.println("a/b....b?");
		int n2=kb.nextInt();
		
		
		System.out.printf("%d / %d = %d \n",n1,n2,n1/n2);
		System.out.println("Good bye~~!");
	}

}
//ArithmeticException : 나누는 자리 분모에 0입력시 예외
//InPutMismatchException : 스캐너에 정수형입력하라고 했는데 정수형 입력할 떄 예외