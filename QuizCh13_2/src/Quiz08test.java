import java.util.Scanner;

public class Quiz08test {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[][] score = new int [4][4];
		int[]sum1 = new int[4];
		int[]sum2 = new int[4];
		
		String[][] str = {{"구분 ","이순신 ","강감찬 ","을지문덕 ","권율 ","총점"},
		{"국어","영어","수학","국사","총점"," " }};
		
		for(int i=0; i<4; i++) {
			System.out.print(str[0][i+1]+"학생 국영수,국사 성적입력 : ");
			for(int j=0;j<4; j++) {
				score[j][i] =sc.nextInt();
			}
		}
		for(int i=0; i<4 ;i++) {
			for(int j=0;j<4;j++) {
				sum1[i] += score[j][i];
				sum2[i] += score[i][j];		
			}
		}
		
		for(int i=0;i<6 ; i++) {
			System.out.printf("%s\t",str[0][i]);
		}
		System.out.println();
		
		for(int i=0;i<4;i++) {
			System.out.print(str[1][i]+"\t");
			for(int j=0;j<4;j++) {
				System.out.print(score[i][j]+"\t");
			}
			System.out.println(sum1[i]);
		}
		System.out.print("총점\t");
		for(int i =0;i<4;i++) {
			System.out.print(sum2[i]+"\t");
		}
	}
}
