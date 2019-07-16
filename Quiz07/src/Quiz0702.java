import java.util.Scanner;

public class Quiz0702 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MyAdd(10,7,13);
		



	}

	public static void MyAdd(int num1, int num2, int num3) {
        
        if(num1>num2 && num1>num3) {
            System.out.println("큰 수 : " + num1);        	        	
        } else if(num2>num1 && num2>num3) {
        	System.out.println("큰 수 : " + num2);
        } else {
        	System.out.println("큰 수 : " + num3);
        }

		}

}
