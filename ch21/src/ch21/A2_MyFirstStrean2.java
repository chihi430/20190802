package ch21;

import java.util.Arrays;


public class A2_MyFirstStrean2 {

	public static void main(String[] args) {
		int[] ar = { 1, 2, 3, 4, 5 };

		int sum = Arrays.stream(ar).filter(n -> n % 2 == 1).sum();

		long div = Arrays.stream(ar).count();

		System.out.println(sum);
		System.out.println(div);

	}

}
// dot chain 문법