

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class phoneBook {
	static {
		try {
			Class.forName("orcle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			//cnfe.printStackTrace();
		}
	}
	
	static Scanner sc = new Scanner(System.in);
	static Map<String, Info> map = new HashMap<>();
	static Connection con;
	
	
	public static void showMenu() {
		System.out.println("[메뉴를 선택하세요!]");
		System.out.println("1. 전화번호 입력");
		System.out.println("2. 전화번호 조회");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 전체전화번호 조회");
		System.out.println("5. 종료");
		System.out.println("선택 : ");
	}

	public static void addNumber() {
		PreparedStatement pstmt1 = null;

		System.out.print("이름  : ");
		String name = sc.nextLine();
		System.out.println("전화 번호 : ");
		String phoneNumber = sc.nextLine();
		System.out.println("이메일 : ");
		String email = sc.nextLine();
		
		try {
			System.out.println("db insert");
					
			String sql = "insert into phonebook values (?,?,?)";
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setString(1, name);
			pstmt1.setString(2, phoneNumber);
			pstmt1.setString(3, email);
			int updateCount = pstmt1.executeUpdate();
			
			con.commit();
			pstmt1.close();
			con.close();

		}catch (SQLException sqle) {
			System.out.println("Connetion Error");
			sqle.printStackTrace();
		}
		
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
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;


		System.out.println("조회할 이름: ");
		String name = sc.nextLine();


		try {
			System.out.println("디비연결됨sel");
					
			String sql = "select * from phonebook where pname=?";
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setString(1, name);
			rs = pstmt1.executeQuery();
			while(rs.next()) {
				System.out.println("name : " + rs.getString(1));
				System.out.println("phoneNumber : " + rs.getString(2));
				System.out.println("email : " + rs.getString(3));
			}
			
			
			pstmt1.close();
			rs.close();
			con.close();

		}catch (SQLException sqle) {
			System.out.println("Connetion Error");
			sqle.printStackTrace();
		}
		
		//Info pInfo = map.get(name);
		//pInfo.showPhoneInfo();

	}

	public static void delNumber() {
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;

		System.out.print("삭제할 이름: ");
		String name = sc.nextLine();
		
		try {
			String sql = "delete from phonebook where pname=?";
			pstmt1 = con.prepareStatement(sql);			
			pstmt1.setString(1, name);
			rs = pstmt1.executeQuery();
			while(rs.next()) {
				System.out.println("name : " + rs.getString(1));
				System.out.println("phoneNumber : " + rs.getString(2));
				System.out.println("email : " + rs.getString(3));
			}
			
			pstmt1.close();
			con.close();

		}
		catch (SQLException sqle) {
			System.out.println("Connetion Error");
			sqle.printStackTrace();
		}
		
//		Info pInfo = map.remove(name);
//		if (pInfo != null) {
//			System.out.println("삭제되었습니다.");
//			pInfo.showPhoneInfo();
//		} else {
//			System.out.println("해당 값이 없습니다.");
//		}
	}
	// 전체 전화번호조회
	public static void allphoneNum() {
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		int num =0;
		
		try {
			String sql = "select * from phonebook";
			pstmt1 = con.prepareStatement(sql);			
			rs = pstmt1.executeQuery();
			while(rs.next()) {
				num++;
				System.out.println("["+num+"번]-----------------------");
				System.out.println("name : " + rs.getString(1));
				System.out.println("phoneNumber : " + rs.getString(2));
				System.out.println("email : " + rs.getString(3));
				System.out.println("");
			}
			
			pstmt1.close();
			con.close();

		}
		catch (SQLException sqle) {
			System.out.println("Connetion Error");
			sqle.printStackTrace();
		}
		
		
	}
	// 파일 저장
	public static void saveInfo() {
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
	public static void readInfo() {

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
//---- 메인
	public static void init() throws SQLException {
	

		//readInfo();
		int choice;
		while (true) {
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
			System.out.println("디비연결");
			
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
				allphoneNum();
				break;
			case 5:
				saveInfo();
				System.out.println("프로그램을 종료합니다");
				return;
			default:
				System.out.println("잘못입력하셨습니다.");
				break;
			}
		}
	}
	
	public static void main(String[] args) throws SQLException {
		init();
	}
}
