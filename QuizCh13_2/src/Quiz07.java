
public class Quiz07 {

	public static void main(String[] args) {

		int[][] ar1 = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 } };
		int[][] ar2 = new int[4][2];

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
				ar2[j][i] = ar1[i][j];

			}
		}
		for(int i =0; i<4; i++) {
			for(int j=0; j<2; j++) {
				System.out.print(ar2[i][j]+"\t");
			}
			System.out.println();
		}

	}

}
