import java.util.Arrays;
import java.util.Scanner;

public class Quiz09 {

	public static void main(String[] args) {

		// 
		int arr[] = new int[10];

		Scanner sc = new Scanner(System.in);

		System.out.println("정수를 입력해주세요");
		for (int i = 0; i < 5; i++) {
			arr[i] = sc.nextInt();
		}
		System.out.print("입력한값 : ");
		for (int j = 0; j < 5; j++) {
			System.out.print(arr[j] + ",");
		}
		System.out.println();

		//내림차순
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] < arr[j]) {
					int sort = arr[i];
					arr[i] = arr[j];
					arr[j] = sort;
				}
			}
		}
		
		System.out.print("내림차순 : ");
		for (int i = 0; i < 5; i++) {
			System.out.print(arr[i] + ",");
		}
		//줄바꿈
		System.out.println();

		//오름차순
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					int sort = arr[i];
					arr[i] = arr[j];
					arr[j] = sort;
				}
			}
		}
		
		System.out.print("오름차순 : ");
		for (int i = 5; i < arr.length; i++) {
			System.out.print(arr[i] + ",");
		}
		System.out.println();
		
		for(int i = 0; i<arr.length;i++) {
			for(int j = 0  ; j <arr.length-1-i; j++) {
				if(arr[j]>arr[j+1]) {
					int sort = arr[i];
					arr[i]=arr[j+1];
					arr[i+1]=sort;
					
				}
			}
		}
		System.out.print("버블 정렬 : ");
		for(int i =5;i<arr.length; i++) {
			System.out.print(arr[i]+",");
		}
	}
}
