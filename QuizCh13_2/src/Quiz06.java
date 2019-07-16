
public class Quiz06 {

	public static void main(String[] args) {

		int[][] arr = new int[3][9];
		int num = 1;
		for(int i =0; i<3; i++) {
			for(int j=0; j<9; j++) {
				arr[i][j] = num;
				num++;
			}
		}
		
		
		for(int i =2; i<5; i++) {
			System.out.println(i+"단");
			for(int j=1;j<10;j++) {
				System.out.print(i+"*"+j+"="+i*j+"\t");
			}
			System.out.println();	
			System.out.println("--------------------------------------------------------------------");		

		}

	}

}
