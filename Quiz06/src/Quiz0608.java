import java.util.Scanner;

public class Quiz0608 {

	public static void main(String[] args) {

		System.out.println("구구단 만들기");
		for (int i = 2; i < 10; i = i + 2) {
			System.out.println("------------------------");
			System.out.println(i + "단");

			for (int j = 1; j <= i; j++) {
				System.out.println(i + " * " + j + " = " + (i * j));
			}

		}

	}
}
