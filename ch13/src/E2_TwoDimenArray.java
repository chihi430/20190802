
public class E2_TwoDimenArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] arr = new int[3][4];
		int num =1;
		
		for(int i =0; i<3; i++) {
			for(int j=0; j<4; j++) {
				arr[i][j] = num;
				num++;
			}
		}
		
		for(int i =0; i<3; i++) {
			for(int j=0;j<4;j++) {
				System.out.print(arr[i][j] + "\t");//(0,1)~~~~(3,4)까지
			}
			System.out.println();
		}
	}
}
