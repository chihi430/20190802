
public class E3_PartiallyFilledArray {

	public static void main(String[] args) {
		// 33이겠거니 해버리면 에러난다. 
		// 이런식으로 i 값이 증가함에 따라 j 값이 증가하는 로직을 통해
		// 2차 배열 초기화 예
		int[][] arr = {
				{11},
				{22, 33},
				{44, 55, 66}
		};
		
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				System.out.print(arr[i][j]+"\t");
			}
			System.out.println();
		}
	}
}
