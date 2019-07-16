import java.util.Random;

class QuizAZ3 {
	public static void main(String args[]) {
		System.out.println("(A,Z)\n");

		for (int A = 0; A < 5; A++) {
			for (int Z = 9; Z > 4; Z--) {
				if ((A + Z) == 9) {
					if (A != Z)
						System.out.println("(" + A + "," + Z + ")");
				}
			}
		}
	}
}

