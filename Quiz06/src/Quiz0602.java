import java.util.Scanner;

public class Quiz0602 {
	
	   public static void main(String[] args) {
	        
	        Scanner sc = new Scanner(System.in);

	        System.out.println("국어 점수를 입력해주세요");
	        int num1 = sc.nextInt();
	        System.out.println("수학 점수를 입력해주세요");
	        int num2 = sc.nextInt();
	        System.out.println("영어 점수를 입력해주세요");
	        int num3 = sc.nextInt();
	        
	        
	        int sum = num1 + num2 +num3;
	        double avg = sum /3;
	        
	        System.out.println("당신의 성적은 : " +avg);
	        if(avg >= 90) {	        	
	        	System.out.println("당신의 성적은 A 입니다");
	        } else if(avg>=80) {
	        	System.out.println("당신의 성적은 B 입니다");
	        } else if(avg>=70) {
	        	System.out.println("당신의 성적은 c 입니다");
	        } else if(avg>=50) {
	        	System.out.println("당신의 성적은 D 입니다");
	        } else {
	        	System.out.println("당신의 성적은 F 입니다");
	        }
	        	
	        
	   }

}
