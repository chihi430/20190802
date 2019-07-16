import java.util.Scanner;

public class Quiz0610 {

	public static void main(String[] args) {

		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("첫번째 정수를 입력해주세요");
			int num1 = sc.nextInt();
			System.out.println("두번째 정수를 입력해주세요");
			int num2 = sc.nextInt();
			System.out.println("세번째 정수를 입력해주세요");
			int num3 = sc.nextInt();
			System.out.println("네번째 정수를 입력해주세요");
			int num4 = sc.nextInt();
			System.out.println("다섯번째 정수를 입력해주세요");
			int num5 = sc.nextInt();

			int sum = num1 + num2 + num3 + num4 + num5;

			if ((num1 > 1) && (num2 > 1) && (num3 > 1) && (num4 > 1) && (num5 > 1)) {
				System.out.println("결과값은 " + sum);
				break;
			} else {
				System.out.println("다시 입력 해주세요");
				continue;
			}
		}
	}
}
