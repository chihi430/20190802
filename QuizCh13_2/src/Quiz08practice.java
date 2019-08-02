import java.util.Scanner;

public class Quiz08practice {

	public static void main(String[] args) {

		
		Scanner sc = new Scanner(System.in);
		 

		int[][] score = new int[4][4];
		int sum1[] = new int[4];
		int sum2[] =new int[4];

		String[][] str = {{"구분", "이순신","강감찬","을지문덕","권율","총점"},
				{"국어","영어","수학","국사","총점"," "}};
		
		for(int i =0 ; i<4 ; i++) {			
					System.out.println(str[0][i+1]+"학생 국영수,국사 점수입력 ");
			for(int j=0;j<4; j++) {
				score[i][j] = sc.nextInt(); 
			}
		}
		for(int i =0; i<4; i++) {
			for(int j =0; j<4 ; j++) {
				sum1[i] = score[i][j];
			}
		}
	}

}
