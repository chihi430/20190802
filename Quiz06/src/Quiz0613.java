import java.util.Scanner;

public class Quiz0613 {

	public static void main(String[] args) {

		System.out.println("구구단 만들기");
		
		System.out.println("가로");
		for (int i = 1; i < 10; i++) {
			for (int j = 2; j < 10; j++) {
				System.out.print(j + "*" + i + "= " + (i * j)+"\t");
			}
			System.out.print("\n");

		}
		
		System.out.print("\n");
		System.out.println("세로");
		for (int i = 2; i < 10; i++) {
			for (int j = 1; j < 10; j++) {
				System.out.print(i + "*" + j + "= " + (j * i)+"\t");				
			}
			System.out.print("\n");

		}
		


	}
}
