import java.util.Random;

public class QuizRandom2 {

	public static void main(String args[]) {
		Random random = new Random();
		int num, a, b, c;

		while(true) {
			num = random.nextInt(900) + 100;
			a = num / 100;
			b = (num / 10) % 10;
			c = num % 100;

			if ((a != b) && (a != c) && (b != c)) {
				System.out.println(num);
				break;
			}

		}
	}
}
