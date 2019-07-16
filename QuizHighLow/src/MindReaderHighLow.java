import java.util.Scanner;

public class MindReaderHighLow {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int chance = 0;
		int max = 101;
		int min = 0;
		int setnum = 0;

		board();
		while (true) {
			setnum = (min + max) / 2;

			System.out.println("당신이 선택한 숫자는 " + (setnum) + "입니까?");
			String highlow = sc.next();
			
			if (highlow.equalsIgnoreCase("l")) {
				setnum = max;
			}
			// 하이부분
			else if (highlow.equalsIgnoreCase("h")) {
				setnum = min;
			}

			chance++;
			if (highlow.equalsIgnoreCase("y")) {
				System.out.println("[" + (chance - 1) + "]" + " 회차 ");
				System.out.println("정답입니다.");
				System.out.println("goodbye");
				break;
			}
		}
	}

	public static void board() {
		System.out.println("-------------------------------------");
		System.out.println("0부터 100사이의 값중에 하나를 생각하세요");
		System.out.println("제(컴)가 제시한 숫자가 생각한 숫자보다 크면 h를 입력하세요");
		System.out.println("제(컴)가 제시한 숫자가 생각한 숫자보다 작으면 l를 입력하세요");

	}

}
