import java.util.Scanner;

public class Quiz0605 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int sum = 0;

		while (true) {
			System.out.println("정수를 입력하세요");
			int num1 = sc.nextInt();

			sum += num1;

			if (num1 == 0) {
				
				break;
			}
		}
		System.out.println("문제의 결과는 : "+sum);
	}
}
