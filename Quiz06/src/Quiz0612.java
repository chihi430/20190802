import java.util.Scanner;

public class Quiz0612 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("정수를 입력해주세요");
		int num1 = sc.nextInt();
		System.out.println("정수를 입력해주세요");
		int num2 = sc.nextInt();
		int sum = 0;

		if (num1 < num2) {
			for (int i = num1; i <= num2; i++) {
				if (i < num2) {
					System.out.print(i + "+");
				} else {
					System.out.print(i + "=");
				}
				sum = sum + i;
			}
		} else {
			for (int i = num1; i >= num2; i--) {
				if (i > num2) {
					System.out.print(i + "+");
				} else {
					System.out.print(i + "=");
				}
				sum = sum + i;
			}
		}
		System.out.println("" + sum);

	}
}
