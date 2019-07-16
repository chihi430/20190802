import java.util.Scanner;

public class Quiz0611 {

	public static void main(String[] args) {
		
//		int sum = 0;
//		for(int i=0 ; i <= 100 ; i=i+2) {
//			sum = sum+i;
//		}
//		System.out.println(sum);
		
		int i = 0;		
		int sum = 0;
		do {		
			i=i+2;
			sum = sum+i;
		}while(i<100);
		System.out.println("짝수 정수의 합 : "+sum);
	}
}
