import java.util.Scanner;

public class Quiz0703 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MyAdd(0, 7, 1);

	}

	public static void MyAdd(int num1, int num2, int num3) {

		if (num1 > num2 && num1 > num3) {
			if (num2 > num3) {
				System.out.println("작은수  : " + num3);
			}
		} else if (num3 > num1 && num3 > num2) {
			if (num1 > num2) {
				System.out.println("작은수  : " + num2);
			}
		} else if (num2 > num1 && num2 > num3) {
			if (num3 > num1) {
				System.out.println("작은수  : " + num1);
			}
		}
	}

}
