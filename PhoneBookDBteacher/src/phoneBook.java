import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class phoneBook {

	static {
		try {
			Class.forName("orcle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			//cnfe.printStackTrace();
		}
	}
	Scanner sc = new Scanner(System.in);
	Connection con = null;

	public void showMenu() {
		System.out.println("[메뉴를 선택하세요!]");
		System.out.println("1. 전화번호 입력");
		System.out.println("2. 전화번호 조회");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 전체번호 조회");
		System.out.println("5. 즐겨찾기 등록");
		System.out.println("6. 즐겨찾기 해제");
		System.out.println("7. 즐겨찾기 조회");
		System.out.println("9. 종료");
		System.out.println("선택 : ");
	}

	public void addNumber() {
		
		System.out.print("이름  : ");
		String name = sc.nextLine();
		System.out.println("전화 번호 : ");
		String phoneNumber = sc.nextLine();
		System.out.println("이메일 : ");
		String email = sc.nextLine();
		
		String sql = "insert into phonebook values(?,?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql); 
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, phoneNumber);
			pstmt.setString(3, email);
			pstmt.setString(4, "0");
			int updateCount = pstmt.executeUpdate();
			System.out.println("데이터 베이스 입력되었습니다.");
			
		} catch (Exception e) {
			System.out.println("데이터 베이스 입력오류!.");
		}
	}

	public void selNumber() {
		System.out.println("조회할 이름: ");
		String name = sc.nextLine();
		
		String sql = "select * from phonebook where pname=?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("name : " + rs.getString(1));
				System.out.println("phonenumber : " + rs.getString(2));
				if(rs.getString(3)!= null) {
				System.out.println("email : " + rs.getString(3));
				}
			}else {
				System.out.println("해당 값이 없습니다.");
			}
			
		} catch (Exception e) {
			System.out.println("알수 없는 에러가 발생했습니다.");
		}
	}

	public void delNumber() {
		System.out.print("삭제할 이름: ");
		String name = sc.nextLine();

		String sql = "delete from phonebook where pname=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql); 
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			int updateCount = pstmt.executeUpdate();
			System.out.println(name+"이 데이터 베이스 삭제가 되었습니다.");
			
		} catch (Exception e) {
			System.out.println("데이터 베이스 삭제오류!.");
		}
	}
	public void selectAll() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num =0;
		String sql = "select * from phonebook";
		try {
			pstmt = con.prepareStatement(sql);			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				num++;
				System.out.println("["+num+"번]-----------------------");
				System.out.println("name : " + rs.getString(1));
				System.out.println("phoneNumber : " + rs.getString(2));
				System.out.println("email : " + rs.getString(3));
				
				System.out.println("");
			}
			
			pstmt.close();
			con.close();

		}
		catch (SQLException sqle) {
			System.out.println("Connetion Error");
			sqle.printStackTrace();
		}
	}
	
	// 즐겨찾기 등록
	public void likeinsert() {
		System.out.print("즐겨찾기할 이름: ");
		String name = sc.nextLine();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "update phonebook set favorite = 1 where favorite = 0  and pname=?";
		try {
			pstmt = con.prepareStatement(sql); 
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			System.out.println("즐겨찾기 등록완료");
			
			rs.close();
			pstmt.close();
			con.close();

		}
		catch (SQLException sqle) {
			System.out.println("Connetion Error");
			sqle.printStackTrace();
		}
	
	}
	// 즐겨찾기 해제
	public void unlike() {
		System.out.print("즐겨찾기 해제할 이름: ");
		String name = sc.nextLine();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "update phonebook set favorite = 0 where favorite = 1  and pname=?";
		try {
			pstmt = con.prepareStatement(sql); 
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			System.out.println("즐겨찾기 해제완료");
			
			rs.close();
			pstmt.close();
			con.close();

		}
		catch (SQLException sqle) {
			System.out.println("Connetion Error");
			sqle.printStackTrace();
		}
	
	}
	// 즐겨찾기 해제
	public void searchlike() {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from phonebook where favorite = 1 order by pname asc";
		try {
			int num =0;
			pstmt = con.prepareStatement(sql); 
			rs = pstmt.executeQuery();
			System.out.println("즐겨찾기 친구조회");
			
			while(rs.next()) {
				num++;
				System.out.println("["+num+"번]-----------------------");
				System.out.println("name : " + rs.getString(1));
				System.out.println("phoneNumber : " + rs.getString(2));
				System.out.println("email : " + rs.getString(3));
				System.out.println("");
			}
			
			
			rs.close();
			pstmt.close();
			con.close();

		}
		catch (SQLException sqle) {
			System.out.println("Connetion Error");
			sqle.printStackTrace();
		}
	
	}
	// 파일 읽기
	public void connectdatabase() {
		try {
				con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void init() {
		int choice;
		
		while (true) {
			connectdatabase();
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
				selectAll();
				break;
			case 5:
				likeinsert();
				break;
			case 6:
				unlike();
				break;
			case 7:
				searchlike();
				break;
			case 9:
				System.out.println("프로그램을 종료합니다");
				return;
			default:
				System.out.println("잘못입력하셨습니다.");
				break;
			}
		}
	}

	public static void main(String[] args) {
		phoneBook a = new phoneBook();
		a.init();
	}
}
