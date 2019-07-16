import java.util.Scanner;

public class Quiz0705 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		while (true) { // 조건이 참이 아닐때 다시 와서 입력
		Scanner sc = new Scanner(System.in);
		System.out.println("첫번째 입력 받을 정수");
		int num1 = sc.nextInt();

		System.out.println("두번째 입력 받을 정수");
		int num2 = sc.nextInt();

		System.out.println("연산자를 선택해 주세요");
		int num3 = sc.nextInt();

		
			if (num3 == 1) { // 숫자 입력을 통한 연산 +-*/ 조건문
				int nCalcPuls = calcplus(num1, num2);
				break;
			} else if (num3 == 2) {
				int nCalcMinus = calcminus(num1, num2);
				break;
			} else if (num3 == 3) {
				int nCalcMulti = calcmul(num1, num2);
				break;
			} else if (num3 == 4) {
				int nCalcDiv = calcdiv(num1, num2);
				break;
			} else {
				System.out.println("1,2,3,4 중에 입력해주세요 !!");
				continue;// 설정 조건이 참이아닐때 다시 입력하기 위함
			}
		}

	}

	// ----------------연산 호출 메소드------------------------------
	public static int calcplus(int num1, int num2) {

		int nResult = num1 + num2;
		System.out.println(nResult);
		return nResult;
	}

	public static int calcminus(int num1, int num2) {

		int nResult = num1 - num2;
		System.out.println(nResult);
		return nResult;
	}

	public static int calcmul(int num1, int num2) {

		int nResult = num1 * num2;
		System.out.println(nResult);
		return nResult;
	}

	public static int calcdiv(int num1, int num2) {

		int nResult = num1 / num2;
		System.out.println(nResult);
		return nResult;
	}

}
