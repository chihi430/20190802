package projectPhoneBook;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class PhoneBook {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PhBook a = new PhBook();
	}
}

class PhBook {

	Scanner sc = new Scanner(System.in);
	String name, phone, email;
	List<String> list = new ArrayList<>();
	boolean run = true;
	int se;

	PhBook() {

		while (run) {
			print();
			select();
		}

	}

	void print() {
		System.out.println("-------------------------전화번호부------------------------");
		System.out.println("메뉴를 선택하세요!");
		System.out.println("1. 전화번호 입력\t 2. 전화번호조회\t 3. 전화번호삭제\t 4. 종료");
		se = sc.nextInt();
		System.out.println();
	}

	void select() {
//		if (se == 1) {
//			System.out.println("1. 전화번호 입력");
//			inputPhoneNum();
//		} else if (se == 2) {
//			System.out.println("2. 전화번호 조회");
//		} else if (se == 3) {
//			System.out.println("3. 전화번호 삭제");
//		} else if (se == 4) {
//			System.out.println("4. 종료");
//		}
		switch (se) {
		case 1:
			System.out.println("1. 전화번호 입력");
			inputPhoneNum();
			break;
		case 2:
			System.out.println("2. 전화번호 조회");
			searchPhoneNum();
			break;
		case 3:
			System.out.println("3. 전화번호 삭제");
			removePhoneNum();
			break;
		case 4:
			System.out.println("4. 종료");
			run = false;
			break;
		default:
			System.out.println("잘못 입력하셨습니다.");
			break;
		}

	}

	void inputPhoneNum() {
		System.out.print("이름을 입력하세요 : ");
		name = sc.next();
		System.out.print("전화번호를 입력하세요 : ");
		phone = sc.next();
		System.out.print("이메일을 입력하세요: ");
		email = sc.next();

		list.add(name);
		list.add(phone);
		list.add(email);
		
	}

	void searchPhoneNum() {
		System.out.println("찾으실 이름을 입력해 주세요");
		String searchname = sc.next();

		if (searchname.equals(name)) {
			System.out.println(name + " 님의 전화번호 부입니다.");
			System.out.println();

			for (int i = 0; i < list.size(); i++) {
				System.out.print(list.get(i) + '\t');
			}
			System.out.println("\n\n");
		} else {
			System.out.println("찾는 전화번호가 없습니다.");
		}
	}

	void removePhoneNum() {
		list.remove(name);
		list.remove(phone);
		list.remove(email);
		System.out.println("삭제완료");

	}

//	void clearwindow() {
//		for (int i = 0; i < 50; i++) {
//			System.out.println();
//		}
//	}

}