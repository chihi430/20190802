import java.util.Random;
import java.util.Scanner;

public class QuizGame {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Random randomV1 = new Random();
		
		int num1;

		while (true) {
			System.out.println("-------------------------------------");
			System.out.println("1. 가위 2. 바위 3. 보 를 선택하십시오(0.종료)");
			int YourCase = sc.nextInt();

			num1 = randomV1.nextInt(3) + 1;

			// 가위바위보 조건문
			if (YourCase == num1) {				
				// 사용자의 수와 랜덤수의 갯수가 같으면 비기는 것
				System.out.println("비겼습니다.");
				
			} else if ((YourCase == 1 && num1 == 2) || (YourCase == 2 && num1 == 3) || (YourCase == 3 && num1 == 1)) {				
				// 사용자가 가위,바위,보를 냈을 때 졌을 때의 경우를 생각하여 작성
				System.out.println("졌습니다.");
				
			} else if ((YourCase == 1 && num1 == 3) || (YourCase == 2 && num1 == 1) || (YourCase == 3 && num1 == 2)) {				
				// 사용자가 가위,바위,보를 냈을 때 이겼을 때의 경우를 생각하여 작성
				System.out.println("이겼습니다.");
				
			} else if (YourCase == 0) {			
				// 사용자가 0를 입력할 때 브레이크
				System.out.println("*******게임을 종료합니다******");
				break;
				
			} else {				
				System.out.println("다른것을 입력하셨습니다 다시 선택해 주세요");
				continue;// 사용자가 다른것을 입력할시 continue를 통해 다시 조건문으로
			}
		}
	}
}
