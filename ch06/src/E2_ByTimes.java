public class E2_ByTimes {

	public static void main(String[] args) {

		System.out.println("구구단 만들기");
		for (int i = 1; i < 10; i++) {
			System.out.println("------------------------");
			System.out.println(i + "단");

			for (int j = 1; j < 10; j++) {
				System.out.println(i + " * " + j + " = " + (i * j));
			}

		}
	}
}
