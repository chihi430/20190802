import java.util.Scanner;

public class QuizSerachNum2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);


		String str;
		String check = "";
		int sum = 0;
		// int sum = Integer.parseInt(check);

		while (true) {
			
			System.out.println("숫자와 문자를 입력해주세요");
			String sNum = sc.nextLine();
			for (int i = 0; i < sNum.length(); i++) {
				check = sNum.replaceAll("[^0-9]", "");
			}
			System.out.println(check);

			for (int i = 0; i < check.length(); i++) {
				String sCheck = String.valueOf(check.charAt(i));
				int a = Integer.parseInt(sCheck);

				sum = sum + a;
				System.out.print(sCheck + "+");
			}

			System.out.println("=" + sum);
			System.out.println("계속하시려면 아무키나 입력하세요"); 
				
			String whateverKey= sc.nextLine();
			continue;
		
		}
	}
}
