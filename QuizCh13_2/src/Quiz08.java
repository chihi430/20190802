import java.util.Scanner;

public class Quiz08 {

	public static void main(String[] args) {

		// �꽦�쟻愿�由� �봽濡쒓렇�옩�쓣 �옉�꽦�븳�떎. 
		// 援��뼱, �쁺�뼱, �닔�븰, 援��궗 4怨쇰ぉ�씠怨� 
		// �븰�깮�� �씠�닚�떊, 媛뺢컧李�, �쓣吏�臾몃뜒, 沅뚯쑉 4紐낆씠�떎. 
		// 4 by 4 諛곗뿴�쓣  �꽑�뼵�븯怨� �궗�슜�옄濡쒕��꽣 4�궗�엺�쓽
		// 4怨쇰ぉ �젏�닔瑜� �엯�젰諛쏆븘 �떎�쓬�쓽 �삎�깭濡� 媛믪쓣 ���옣�븯怨� 
		
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
