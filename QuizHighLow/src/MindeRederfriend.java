import java.util.Scanner;

public class MindeRederfriend {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String user;
		double com = 50;
		double count = 0;
		double n = 1;
		
		System.out.println("0부터 100 사이의 값 중에 하나를 생각하세요.");
		System.out.println("제(컴)가 제시한 숫자가 생각한 숫자보다 크면 h를 입력하세요.");
		System.out.println("제(컴)가 제시한 숫자가 생각한 숫자보다 작으면 l를 입력하세요.");
		System.out.println("제(컴)가 숫자를 맞췄다면 y를 입력해 주세요.");
		
		do {
			count = count + 1;
			n *= 2;
			System.out.println("당신이 선택한 숫자는 " + (int)com + "입니까?");
			user = sc.next();
			if (user.equalsIgnoreCase("h")) {
				com = com - 51/n;
			}
			else if (user.equalsIgnoreCase("l")) {
				com = com + 51/n;
			} 
			else if (user.equalsIgnoreCase("y")) {
				System.out.println("정답입니다. [" + (int)count + "회차]");
				System.out.println("Good Bye~!!");
				break;
			} else {
				System.out.println("잘못 입력하셨습니다.");
				System.out.println("제(컴)가 제시한 숫자가 생각한 숫자보다 크면 h를 입력하세요.");
				System.out.println("제(컴)가 제시한 숫자가 생각한 숫자보다 작으면 l를 입력하세요.");
				System.out.println("제(컴)가 숫자를 맞췄다면 y를 입력해 주세요.");
			}
		} while(true);
	}
}
