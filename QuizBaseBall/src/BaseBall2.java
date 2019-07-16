import java.util.Random;
import java.util.Scanner;

public class BaseBall2 {

	public static void main(String[] args) {
		Random randNum=new Random();
		Scanner sc = new Scanner(System.in);
		int count=0;
		int nUser=0, nFirstUser, nSecondUser, nThridUser;
		int nPC, nFirstPC, nSecondPC, nThridPC;
		int strike=0, ball=0;
		
		while(true) {
			nPC=randNum.nextInt(1000);

			nFirstPC = nPC/100;
			nSecondPC = (nPC%100)/10; 
			nThridPC = nPC%10;
			if(nFirstPC == nSecondPC ||nFirstPC == nThridPC || nSecondPC == nThridPC) {
				continue;
			}
			break;
		}
		System.out.println(nPC);
		
		
		System.out.println("숫자로 하는 야구게임시작");
		
		while(true) {
			count++;
			System.out.printf("세자리 숫자를 입력하세요. (%d)\n",count);
			nUser=sc.nextInt();
			if( nUser > 999) {
				System.out.println("숫자를 잘못 입력하였습니다. 다시 입력하세요");
				count--;
				continue;
			}
			nFirstUser = nUser/100;
			nSecondUser = (nUser%100)/10; 
			nThridUser = nUser%10;
			if(nFirstUser == nSecondUser ||nFirstUser == nThridUser || nSecondUser == nThridUser) {
				System.out.println("숫자를 잘못 입력하였습니다. 다시 입력하세요");
				count--;
				continue;
			}
			System.out.println(nFirstUser+":"+nSecondUser+":"+nThridUser);
			
			if(nFirstUser == nFirstPC)		strike++;
			if(nSecondUser == nSecondPC)	strike++;
			if(nThridUser == nThridPC)		strike++;
			
			if(nFirstUser == nSecondPC) 	ball++;
			if(nFirstUser == nThridPC) 		ball++;
			if(nSecondUser ==  nThridPC) 	ball++;
			if(nSecondUser ==  nFirstPC) 	ball++;
			if(nThridUser ==  nFirstPC) 	ball++;
			if(nThridUser ==  nSecondPC) 	ball++;
			
			if(strike==0 && ball==0) {
				System.out.println("Out !!");
			}else {
				System.out.printf("%d Strike\t%d Ball\n",strike,ball);
			}
			if(strike == 3) {
				System.out.println("You Win !!");
				break;
			}
			
			strike =0;
			ball=0;
		}
	
	}

}
