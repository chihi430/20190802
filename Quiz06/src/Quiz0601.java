import java.util.Scanner;

public class Quiz0601 {
	
	   public static void main(String[] args) {
	        
	        Scanner sc = new Scanner(System.in);

	        
	        System.out.println("첫번째 정수를 입력해주세요 ");
	        int num1 = sc.nextInt();
	        System.out.println("두번째 정수를 입력해주세요 ");
	        int num2 = sc.nextInt();
	        int diff;
	        int sum = num1 - num2;
	        
	        if(sum>0) {
	        	
	        	System.out.println("두수의 차는"+sum);
	        }
	        else if(sum<0) {
	        	diff = (sum>0)?(sum):(-sum);
	        	System.out.println("두수의 차 절대값"+diff);
	        }
	        	
	        
	   }

}
