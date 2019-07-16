import java.util.Scanner;

public class Quiz1 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int num = 0;
		int[] inputnum = new int[5];
		int max, min;
		int sum = 0;

		while (true) {
			System.out.println("정수를 입력하시오");
			inputnum[num] = sc.nextInt();
			num++;
			if (num == 5) {
				break;
			}
		}
		min = getMinNum(inputnum);
		max = getMaxNum(inputnum);
		sum = getSumNum(inputnum);

		System.out.println("최소값 : "+min);
		System.out.println("최대값 : " +max);
		System.out.println("합계 : "+sum);

	}

	private static int getMinNum(int[] inputnum) {
		int min = inputnum[0];

		for (int i = 0; i < inputnum.length; i++) {
			if (min > inputnum[i]) {
				min = inputnum[i];
			}
		}
		return min;
	}

	private static int getMaxNum(int[] inputnum) {
		int max = inputnum[0];
		for (int i = 0; i < inputnum.length; i++) {
			if (max < inputnum[i]) {
				max = inputnum[i];
			}
		}
		return max;
	}

	private static int getSumNum(int[] inputnum) {
		int sum = inputnum[0];
		for (int i = 0; i < inputnum.length; i++) {
			sum += inputnum[i];
		}
		return sum;

	}

}