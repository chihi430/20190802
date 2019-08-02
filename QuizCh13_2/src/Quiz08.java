import java.util.Scanner;

public class Quiz08 {

	public static void main(String[] args) {

		
		Scanner sc = new Scanner(System.in);
		int score =  sc.nextInt();
		 

		int[][] arr = new int[6][6];
		int num = 1;
		int sum =0;
		for(int i =0; i<6; i++) {
			for(int j=0; j<6; j++) {
				arr[i][j] = num;
				num++;
			}
		}
		String[][] arr2 = new String[6][6];
		arr2[0][0] = "구분";
		arr2[0][1] = "이순신";
		arr2[0][2] = "강감찬";
		arr2[0][3] = "을지문덕";
		arr2[0][4] = "권율";
		arr2[0][5] = "총점";
		arr2[1][0] = "국어";
		arr2[2][0] = "영어";
		arr2[3][0] = "수학";
		arr2[4][0] = "국사";				
		arr2[5][0] = "총점";
		int num1 = 1;
	
		
		for(int i =1; i<6; i++) {
			for(int j=1; j<6; j++) {
				score = arr[i][j];
				System.out.print(arr[i][j]+"\t");
			}
			System.out.println("");
		}
	
	}

}
