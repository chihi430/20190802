import java.util.Random;
import java.util.Scanner;

public class BaseBall {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Random randomV1 = new Random();

		int num1, num2, num3;


		while (true) {
			
			num1 = randomV1.nextInt(9) + 1;
			num2 = randomV1.nextInt(10);
			num3 = randomV1.nextInt(10);
			
			if (num1 != num2 && num1 != num3 && num2 != num3) {
				System.out.println(num1 + "" + num2 + "" + num3);
				int YourCase1 = sc.nextInt();
				int YourCase2 = sc.nextInt();
				int YourCase3 = sc.nextInt();
				
								

				if(YourCase1 == num1 && YourCase2 ==num2 && YourCase3==num3) {
					System.out.println("3 strike 0 Ball");
					System.out.println("You Win");
					break;
					
				}else if(YourCase1 != num1 && YourCase1 != num2 && YourCase1 != num3 
						&& YourCase2 != num1 && YourCase2!= num2 && YourCase2!= num3 
						&& YourCase3!= num1 && YourCase3!= num2 && YourCase3!= num3) {
					System.out.println("out");
				
						//num1번에대한 케이스
				}else if(((YourCase1 == YourCase2 && YourCase2 != num2 && YourCase3 != num3) ||
						(YourCase1 == YourCase3 && YourCase2 != num2 && YourCase3 != num3) &&
						(YourCase1 == num2 && YourCase2 != num2 && YourCase3 != num3) ||
						(YourCase1 == num3 && YourCase2 != num2 && YourCase3 != num3)) ||
						
						//num2번에대한 케이스
						((YourCase1 != num1 && YourCase2 == YourCase1 && YourCase3 != num3) ||
						(YourCase1 != num1 && YourCase2 == YourCase3 && YourCase3 != num3) &&
						(YourCase1 != num1 && YourCase2 == num3 && YourCase3 != num3)	||
						(YourCase1 != num1 && YourCase2 == num1 && YourCase3 != num3)) ||
					
						//num3번에대한 케이스
						((YourCase1 != num1 && YourCase2 != num2 && YourCase3 == YourCase1) ||
						(YourCase1 != num1 && YourCase2 != num2 && YourCase3 == YourCase2)&&
						(YourCase1 != num1 && YourCase2 != num2 && YourCase3 == num1) ||
						(YourCase1 != num1 && YourCase2 != num2 && YourCase3 == num2))) {
					
					System.out.println("1ball");
					
				} else if((YourCase1 == YourCase2 && YourCase2 != num2 && YourCase3 == num2)) {
					System.out.println("2ball");
				}

				break;
			}
		}
		
	}

}
