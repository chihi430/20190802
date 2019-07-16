import java.util.Scanner;

public class Quiz0607 {

	public static void main(String[] args) {

		int num = 1;

		boolean search = false;

		while (1 <= num && num < 100) {
			if (((num % 7) == 0) && ((num % 9) == 0)) {
				search = true;
				break;

			}
			num++;

		}
		if (search) {
			System.out.println("찾는 정수 : " + num);
		} else
			System.out.println("7의 배수이자 9의 배수인 수를 찾지 못했습니다.");
		System.out.println("끝");
	}
	
}
