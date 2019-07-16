import java.util.Random;
import java.util.Scanner;

public class HighLow {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random randomV1 = new Random();

		int num1;
		int chance = 6;
		
		num1 = randomV1.nextInt(101);
		board();
		
		while (0 <= chance) {

			int YourCase = sc.nextInt();

			if (YourCase == num1) {
				System.out.println("축하합니다. 정답은 : " + num1 + " 다시하시겠습니까?(Y/N)");
				String str = sc.next();

				if (!str.equalsIgnoreCase("y")) {
					System.out.println("goodbye");
					break;
				} else {
					board();
				}
			} else if (YourCase > num1) {
				System.out.println("제가 정한 숫자보다 작습니다.");
			} else if (YourCase < num1) {
				System.out.println("제가 정한 숫자보다 큽니다.");
			}

			if (chance == 1) {
				System.out.println("실패하셨습니다.정답은 : " + num1 + " 다시하시겠습니까?(Y/N)");
				String str = sc.next();

				if (!str.equalsIgnoreCase("y")) {
					System.out.println("goodbye");
					break;
				} else {
					board();
					num1 = randomV1.nextInt(101);
					chance = 7;
				}
			}
			chance--;
			System.out.println("남은 기회는 : " + "[" + chance + "]");

		}
	}

	public static void board() {
		System.out.println("-------------------------------------");
		System.out.println("나는 지금 0 부터 100사이의 값중에 하나를 생각하겠습니다.");
		System.out.println("당신은 그 숫자를 6회 안에 맞추시면 됩니다.");

	}

}
