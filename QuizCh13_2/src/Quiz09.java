import java.util.Arrays;
import java.util.Scanner;

public class Quiz09 {

	public static void main(String[] args) {

		// 
		int arr[] = new int[10];

		Scanner sc = new Scanner(System.in);

		System.out.println("������ �Է����ּ���");
		for (int i = 0; i < 5; i++) {
			arr[i] = sc.nextInt();
		}
		System.out.print("�Է��Ѱ� : ");
		for (int j = 0; j < 5; j++) {
			System.out.print(arr[j] + ",");
		}
		System.out.println();

		//��������
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] < arr[j]) {
					int sort = arr[i];
					arr[i] = arr[j];
					arr[j] = sort;
				}
			}
		}
		
		System.out.print("�������� : ");
		for (int i = 0; i < 5; i++) {
			System.out.print(arr[i] + ",");
		}
		//�ٹٲ�
		System.out.println();

		//��������
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					int sort = arr[i];
					arr[i] = arr[j];
					arr[j] = sort;
				}
			}
		}
		
		System.out.print("�������� : ");
		for (int i = 5; i < arr.length; i++) {
			System.out.print(arr[i] + ",");
		}
		System.out.println();
	}
}
