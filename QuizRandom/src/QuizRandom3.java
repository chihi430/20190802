import java.util.Random;

public class QuizRandom3 {

	public static void main(String[] args) {
		

		Random randomV1 = new Random();

		int num1, num2, num3;

		for (;;) {
			num1 = randomV1.nextInt(9) + 1;
			num2 = randomV1.nextInt(10);
			num3 = randomV1.nextInt(10);
			if ((num1 != num2) && (num1 != num3) && (num2 != num3)) {
				System.out.println(num1 + "" + num2 + "" + num3);
				break;
			}
		}
	}
}
