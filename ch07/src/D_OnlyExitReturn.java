
public class D_OnlyExitReturn {

	public static void main(String[] args) {
		divide(4, 2);
		divide(6, 2);
		divide(9, 0);
	}

	public static void divide(int num1, int num2) {
		if (num2 == 0) {
			System.out.println("0으로 나눌수 없습니다.");
			return;
		}
		else if(num2 == 2) {
			System.out.println("2로 나눗셈한 결과" + (num1/num2));
			return;
		}
		
		System.out.println("나눗셈 결과 :" + (num1/num2));
	}
	
}
	

