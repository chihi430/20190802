import java.util.Scanner;

public class Quiz0603 {
	
	   public static void main(String[] args) {
	        
	        Scanner sc = new Scanner(System.in);
	        System.out.println("정수를 입력해주세요");
	        int num1 = sc.nextInt();
	        
	        int sum = 1;
	       
	        

	        while(num1>0) {	        	
	        	System.out.print(num1+"*");
	        	sum *= num1;
	            num1--;
	           if(num1 == 1) {        	   
	        	   break;
	           }
	        }
	        System.out.print("1 = "+sum);
	        	
	        
	   }

}
