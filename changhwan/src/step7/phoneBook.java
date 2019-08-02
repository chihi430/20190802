package step7;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
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

		System.out.print("이름  : ");
		String name = sc.nextLine();
		System.out.println("전화 번호 : ");
		String phoneNumber = sc.nextLine();
		System.out.println("이메일 : ");
		String email = sc.nextLine();

		Info pInfo;
		
		if (email != null) {
			pInfo = new Info(name, phoneNumber, email);
		} else {
			pInfo = new Info(name, phoneNumber);
		}
		pInfo.showPhoneInfo();
		map.put(name, pInfo);
		System.out.println("맵의 크기 : " + map.size());

	}

	
	public static void selNumber() {
		System.out.println("조회할 이름: ");
		String name = sc.nextLine();

		Info pInfo = map.get(name);
		pInfo.showPhoneInfo();
		
	}

	
	public static void delNumber() {
		System.out.print("삭제할 이름: ");
		String name = sc.nextLine();

		Info pInfo = map.remove(name);
		if (pInfo != null) {
			System.out.println("삭제되었습니다.");
			pInfo.showPhoneInfo();
		} else {
			System.out.println("해당 값이 없습니다.");
		}
	}

	//파일 저장
	public static void saveInfo() {
		System.out.println("저장실행");
		try (ObjectOutputStream oo = 
				new ObjectOutputStream(new FileOutputStream("Object.bin"))) {
			
			Set<String> ks = map.keySet();
			for (String s : ks) {
				Info pInfo = map.get(s);
				oo.writeObject(pInfo);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//파일 읽기
	public static void readInfo() {
		
		try(ObjectInputStream oi = 
				new ObjectInputStream(new FileInputStream("Object.bin"))){
			
			while(true) {
				Info pInfo = (Info) oi.readObject();
				if(pInfo == null)
					break;
				map.put(pInfo.name,pInfo);
			}
			
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		catch (IOException e) {
			//e.printStackTrace();
		}
	}

	public static void main(String[] args) {				
		readInfo();
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
				saveInfo();
				System.out.println("프로그램을 종료합니다");
				return;
			default:
				System.out.println("잘못입력하셨습니다.");
				break;
			}
		}
	}
}
