import java.util.Scanner;

public class Quiz0609 {

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("정수를 입력해주세요");
        int num = sc.nextInt();

		System.out.println("구구단 만들기");
		for (int i = num; 0 < i && i < 10; ) {
			System.out.println("------------------------");
			System.out.println(i + "단");

			for (int j = 9; 0 < j && j < 10; j--) {
				System.out.println(i + " * " + j + " = " + (i * j));
			}
			if(num==i) {
				break;
			}

		}

	}
}
