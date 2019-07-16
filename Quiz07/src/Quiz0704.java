import java.util.Scanner;

public class Quiz0704 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 섭씨 입력 받기 sc 스캐너 화씨로 변환 하는 리턴 함수
		// 화씨를 입력받아서 섭씨로 변환하는는 리턴 함수
		Scanner sc = new Scanner(System.in);
		System.out.println("입력받을 섭씨를 설정");
		int subTemp = sc.nextInt();

		System.out.println("입력받을 화씨를 설정");
		int hwaTemp = sc.nextInt();

		int HWATemp = Hwa(subTemp);
		int SubTemp = Sub(hwaTemp);

	}

	public static int Hwa(int subTemp) {
		int hwa = (int) (1.8 * (subTemp) + 32);
		System.out.println(subTemp + "받은 온도의 섭씨는" + hwa);
		return hwa;
	}

	public static int Sub(int hwaTemp) {
		int sub = (int) ((int) (hwaTemp - 32) / 1.8);
		System.out.println(hwaTemp + "받은 온도의 섭씨는" + sub);
		return sub;
	}

}
