package step2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class phoneBook {
	static Scanner sc = new Scanner(System.in);
	static Map<String, Info> map = new HashMap<>();

	public static void showMenu() {
		System.out.println("[메뉴를 선택하세요!]");
		System.out.println("1. 전화번호 입력");
		System.out.println("2. 전화번호 조회");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 종료");
		System.out.println("선택 : ");

	}

	public static void addNumber() {

	}

	public static void selNumber() {

	}

	public static void delNumber() {

	}

	public static void main(String[] args) {
		int choice;
		while (true) {
			showMenu();
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				addNumber();
				break;
			case 2:
				selNumber();
				break;
			case 3:
				delNumber();
				break;
			case 4:
				System.out.println("프로그램을 종료합니다");
				break;
			default:
				System.out.println("잘못입력하셨습니다.");
				break;

			}

		}
	}
}
