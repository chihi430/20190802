package step10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.lang.Character.Subset;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class phoneBook {

	Scanner sc = new Scanner(System.in);
	Map<String, Info> map = new HashMap<>();
	
	public void showMenu() {
		System.out.println("[메뉴를 선택하세요!]");
		System.out.println("1. 전화번호 입력");
		System.out.println("2. 전화번호 조회");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 종료");
		System.out.println("선택 : ");

	}

	public void addNumber() {

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

	public void selNumber() {
		System.out.println("조회할 이름: ");
		String name = sc.nextLine();

		Info pInfo = map.get(name);
		pInfo.showPhoneInfo();

	}

	public void delNumber() {
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

	// 파일 저장
	public void saveInfo() {
		System.out.println("저장실행");
		String s;
		try(BufferedWriter writer = new BufferedWriter
				(new FileWriter("step08.csv"))) {
			

			Set<String> key = map.keySet();

			for (String k : key) {
				Info pInfo = map.get(k);
					s = "\""+pInfo.name + "\","+
						"\""+pInfo.phoneNumber + "\","+
						"\""+pInfo.email + "\"";
	
				writer.write(s);
				writer.newLine();

			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 파일 읽기
	public void readInfo() {

		try(BufferedReader reader = new BufferedReader(new FileReader("step08.csv"))) {
			String str;

			while (true) {
				str = reader.readLine();
				if(str == null)
					break;
				
				StringTokenizer sToken = new StringTokenizer(str,"\",\"");
				String name = sToken.nextToken();
				String nPhone = sToken.nextToken();
				String email = null;
				if(sToken.hasMoreTokens()) {
					email = sToken.nextToken();
				}
				
				Info pInfo;
				if (email != null) {
					pInfo = new Info(name, nPhone, email);
				} else {
					pInfo = new Info(name, nPhone);
				}
				map.put(name, pInfo);		
			}
		}
		catch(IOException e){
		// e.printStackTrace();
		}
	}
	public void dorun() {
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

	public static void main(String[] args) {
		phoneBook a= new phoneBook();
		a.dorun();
	}
}
