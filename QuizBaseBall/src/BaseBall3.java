import java.util.Random;
import java.util.Scanner;

public class BaseBall3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Random randomV1 = new Random();

		int num1, num2, num3;		
		int mainuser=0;
		int user1,user2,user3;
		int strike = 0, ball=0;
		int chance=1;
		
		while (true) {			
			num1 = randomV1.nextInt(9)+1;
			num2 = randomV1.nextInt(9)+1;
			num3 = randomV1.nextInt(9)+1;		
			
			if (num1==num2 || num1==num3 || num2==num3) {				
				continue;
			}
			break;
		}
		
		
		while(true) {
			

			System.out.println(num1 + "" + num2 + "" + num3);
			System.out.println("숫자로 하는 야구게임시작");
			System.out.println("세자리 숫자를 입력하세요 ["+ chance+"]");
			mainuser = sc.nextInt();
			
			user1 = mainuser/100; // 입력한 100의 자리숫자 나눈거 1의 자리숫자로 바꾼거
			user2 = (mainuser%100)/10; // 입력한 10의 자리숫자를 1의 자리숫자로 바꾼거
			user3 = mainuser%10; // 1의자리숫자 추출
			
			// 각각의 입력 숫자와 같을 때 strike 값이 하나씩 증가  
			// 이루프에서 증가 할수있는 숫자는 3이다
			if(user1==num1) strike++;
			if(user2==num2) strike++;
			if(user3==num3) strike++;
			
			
			// 숫자의 경우를 계산 했을때 숫자별로 비교 둘중에 하나가 참이면 볼이라는 값이 증가
			// 이루프에서 증가 할수있는 숫자는 3이다
			if(user1==num2) ball++;
			if(user1==num3) ball++;
			
			if(user2==num1) ball++;
			if(user2==num3) ball++;
			
			if(user3==num1) ball++;
			if(user3==num2) ball++;
			
			//strike==3일때 루프를 멈춘다.
			if(strike ==3) {
				System.out.println("정답 :"+num1 + "" + num2 + "" + num3+" 당신의 승리입니다.");
				System.out.println("다시 하시겠습니까??[y/n]");
				String re =sc.next();
				if (!re.equalsIgnoreCase("y")) {					
					System.out.println("goodbye");
					break;
				}
			}	
			//루프에서 참인것이 없을때 out출력
			else if(strike ==0 && ball==0) {
				System.out.println("out");
			}else if(mainuser==num1 || mainuser ==num2 || mainuser ==num3 ) {
				System.out.println("오류 3자리수(1~9) 입력해주시기 바랍니다.");
			}else{
				System.out.println(strike+":Strike\t"+ball+ ":ball");
			}
			strike=0;
			ball=0;
			chance++;		
		}		
	}
}
