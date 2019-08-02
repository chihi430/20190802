package projectHashMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;


public class phoneBook {

	public static void main(String[] args) {
		PhBook a = new PhBook();
	}
}

class PhBook {

	Scanner sc = new Scanner(System.in);
	String name, phone, email,PhoneInfo,me;
	HashMap<String, PhoneInfo> map = new HashMap<String, PhoneInfo>();
	int num;
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
		System.out.println("1. 전화번호 입력\t2. 전화번호조회\t3. 전화번호삭제\t4. 전체번호 \t5. 종료");
		se = sc.nextInt();
		me = sc.nextLine();
		System.out.println();
	}

	void select() {
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
			System.out.println("4. 전체전화번호부");
			allnum();
			break;
		case 5:
			System.out.println("GoodBye~");
			run = false;
			break;
		default:
			clearwindow();
			System.out.println("잘못 입력하셨습니다.");
			break;
		}

	}

	public void inputPhoneNum() {
		System.out.print("이름을 입력하세요 : ");
		name = sc.nextLine();
		System.out.print("전화번호를 입력하세요 : ");
		phone = sc.nextLine();
		System.out.print("이메일을 입력하세요: ");
		email = sc.nextLine();
		map.put(name,new PhoneInfo(name,phone,email) );
	}

	void searchPhoneNum() {
		System.out.println("찾으실 이름을 입력해 주세요");
		String searchname = sc.nextLine();

		if (map.get(searchname)==null) {
			System.out.println("찾는 전화번호가 없습니다.");
			return;
		}
		System.out.println(searchname + " 님의 전화번호 부입니다.");
		System.out.println();
		PhoneInfo p =map.get(searchname);
		System.out.println(p.toString());
		
	}
	void allnum() {
		Set<String> keylist = map.keySet();
		Iterator<String> itr=keylist.iterator();
		while(itr.hasNext()) {
			String name = itr.next();
			System.out.println(map.get(name));
		}
	}

	void removePhoneNum() {
		System.out.println("삭제할 이름 입력");
		String deleteName = sc.nextLine();
		if(map.get(deleteName) == null) {
			System.out.println("존재하지 않는 이름입니다.");
		}
		map.remove(deleteName);

	}

	void clearwindow() {
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}

}
