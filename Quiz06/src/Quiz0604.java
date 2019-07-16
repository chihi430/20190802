import java.util.Scanner;

public class Quiz0604 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("정수를 입력해주세요");
		int num = sc.nextInt();
		int sum = 1;

		do {
			System.out.print(num + "+");
			sum += num;
			num--;
			if (num == 1) {
				break;
			}

		} while (num < 1000);

		System.out.println("1 =" + sum);

	}
}
