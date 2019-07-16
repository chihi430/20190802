import java.util.Scanner;

public class Quiz0606{

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("입력받을 정수의 개수를 설정해주세요");
		int num = sc.nextInt();
		
		 
		

		int sum =0;
		double avg;
		
		for(int i=0; i< num ; i++) {
			System.out.println("정수를 입력하세요");
			int num1 = sc.nextInt();
			sum += num1;

		}
		avg = sum/num;

		System.out.println("결과의 합산 값은 : "+sum);
		System.out.println("결과의 평균 값은 : "+avg);
		




	}
}
