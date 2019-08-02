import java.util.Arrays;
import java.util.Scanner;

public class Quiz10 {

	public static void main(String[] args) {

		int[][] num = new int[4][4];

		int count = 1;

		for (int i = 0; i < num.length; i++) {
			for (int j = 0; j < num.length; j++) {
				num[i][j] = count++;
			}
		}
		for (int i = 0; i < num.length; i++) {
			for (int j = 0; j < num.length; j++) {
				System.out.print(num[i][j] + "\t");
			}
			System.out.println("");
		}
		// ===================90도 회전========================

		System.out.println();

		for (int i = 0; i < num.length; i++) {
			for (int j = num.length - 1; j >= 0; j--) {
				System.out.print(num[j][i]+"\t");
			}
			System.out.println();
		}
		System.out.println();
		
		
		for(int i =num.length-1 ; i>=0 ; i--) {
			for(int j =num.length-1 ; j>=0 ; j--) {
				System.out.print(num[i][j]+"\t");
			}
			System.out.println();
		}
		System.out.println();
		
		
		for(int i=0; i<num.length;i++) {
			for(int j=num.length-1;j>=0;j--) {
				System.out.print(num[i][j]+"\t");
			}
			System.out.println();
		}
	}
}
