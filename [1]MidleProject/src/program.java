
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class program {

	static {
		try {
			Class.forName("orcle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			// cnfe.printStackTrace();
		}
	}
	Scanner sc = new Scanner(System.in);
	Connection con = null;

	public void showMenu() {
		System.out.println("[메뉴를 선택하세요!]");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("3. 전화번호 삭제");
		System.out.println("9. 종료");
		System.out.println("0. 관리자 로그인");
		System.out.println("선택 : ");
	}

	public void joinmember() {
		String password = null;
		String passcheck = null;
		String id = null;
		String name = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs;
		System.out.println("회원가입을 시작합니다.");

		while (true) {
			System.out.print("id  : ");
			id = sc.nextLine();

			// 중복체크
			String sql = "select * from pmember where pid = ?";
			try {
				pstmt1 = con.prepareStatement(sql);
				pstmt1 = con.prepareStatement(sql);
				pstmt1.setString(1, id);
				rs = pstmt1.executeQuery();

				// 중복 쿼리값 가져와서 비교
				if (rs.next()) {
					if (rs.getString(1).equals(id)) {
						System.out.println("중복된 이름입니다.");
						continue;
					}
				}
			}

			catch (Exception e) {
				e.printStackTrace();
			}
			break;

		}

		// 비밀번호 확인
		while (true) {
			System.out.println("비밀 번호 : ");
			password = sc.nextLine();
			System.out.println("비밀 번호 확인 : ");
			passcheck = sc.nextLine();
			if (password.equals(passcheck)) {
				break;
			} else {
				System.out.println("비밀번호가 맞지 않습니다. 다시 입력하세요");
			}
		}

		System.out.print("이름  : ");
		name = sc.nextLine();
		System.out.println("이메일 : ");
		String email = sc.nextLine();
		System.out.println("전화 번호 : ");
		String phoneNumber = sc.nextLine();

		String sql1 = "insert into pmember values(?,?,?,?,?,'0')";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql1);
			pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.setString(5, phoneNumber);
			int updateCount = pstmt.executeUpdate();
			System.out.println("데이터 베이스 입력되었습니다.");

		} catch (Exception e) {
			System.out.println("데이터 베이스 입력오류!.");
		}
	}
	// 관리자 로그인

	public void adminlogin() {
		int choice;
		String adminname = "ceo";
		String adminpassword = "123";

		System.out.print("관리자 ID  : ");
		adminname = sc.nextLine();
		System.out.println("비밀번호 : ");
		adminpassword = sc.nextLine();

		if (adminname.equals("ceo") == adminpassword.equals("123")) {
			System.out.println("관리자 로그인 성공");			
		}
		else {
			System.out.println("로그인실패 다시 접속해주세요");
		}
		while(true) {
			System.out.println("관리자 메뉴를 선택해주세요");
			System.out.println("1. 블랙리스트 조회");
			System.out.println("2. 블랙리스트 등록");
			System.out.println("3. 블랙리스트 해제");
			System.out.println("4. 전체 회원 조회");
			System.out.println("5. 회원탈퇴");
			System.out.println("6. 메인메뉴");

		choice = sc.nextInt();
		sc.nextLine();
		switch(choice) {
		case 1:
			blasklistsearch();
			break;
		case 2:
			blacklistinsert();
			break;
		case 3:
			blacklistdel();
			break;
		case 4:
			selectAll();
			break;
		case 5:
			delMember();
			break;
		case 6:
			init();
			break;
		default:
			System.out.println("잘못입력하셨습니다. 다시입력해주세요");				
			}
		}
	}

	//회원 로그인
	public void login() {
		System.out.println("id : ");
		String name = sc.nextLine();
		System.out.println("password : ");
		String password = sc.nextLine();

		String sql = "select * from pmember where pid=? and passwd=?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				if(rs.getString(6).equals("1")) {
					System.out.println("해당 아이디는 블랙리스트입니다.");
					System.out.println("이용이 불가능 합니다 관리자에게 문의해주세요");
					init();
				}
				if (rs.getString(1).equals(name) 
						&& rs.getString(2).equals(password) 
						&& rs.getString(6).equals("0")) 
				{
					System.out.println(name+"님 접속완료");
					System.out.println("환영합니다.");
					System.out.println("name : " + rs.getString(3));
					System.out.println("email : " + rs.getString(4));
					System.out.println("phonenum : " + rs.getString(5));
				} else {
					System.out.println("아이디 비밀번호가 틀립니다.");
				}
			} else {
				System.out.println("아이디와 비밀번호를 입력해 주세요.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("알수 없는 에러가 발생했습니다.");
		}
	}

	public void delMember() {
		System.out.print("삭제할 이름: ");
		String name = sc.nextLine();

		String sql = "delete from pmember where pid=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			int updateCount = pstmt.executeUpdate();
			System.out.println(name + "이 데이터 베이스 삭제가 되었습니다.");

		} catch (Exception e) {
			System.out.println("데이터 베이스 삭제오류!.");
		}
	}

	public void selectAll() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		String sql = "select * from pmember";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				num++;
				System.out.println("[" + num + "번]-----------------------");
				System.out.println("name : " + rs.getString(1));
				System.out.println("phoneNumber : " + rs.getString(2));
				System.out.println("email : " + rs.getString(3));

				System.out.println("");
			}

			pstmt.close();
			con.close();

		} catch (SQLException sqle) {
			System.out.println("Connetion Error");
			sqle.printStackTrace();
		}
	}

	// 블랙리스트 등록
	public void blacklistinsert() {
		System.out.print("블랙리스트 ID 입력 : ");
		String id = sc.nextLine();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "update pmember set blacklist = 1 where blacklist = 0  and pid=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			System.out.println("블랙리스트 등록완료");

			rs.close();
			pstmt.close();
			con.close();

		} catch (SQLException sqle) {
			System.out.println("Connetion Error");
			sqle.printStackTrace();
		}

	}

	// 즐겨찾기 해제
	public void blacklistdel() {
		System.out.print("즐겨찾기 해제할 이름: ");
		String name = sc.nextLine();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "update pmember set blacklist = 0 where blacklist = 1  and pid=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			System.out.println("즐겨찾기 해제완료");

			rs.close();
			pstmt.close();
			con.close();

		} catch (SQLException sqle) {
			System.out.println("Connetion Error");
			sqle.printStackTrace();
		}

	}


	// 블랙리스트 조회
	public void blasklistsearch() {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from pmember where blacklist = 1 order by pname asc";
		try {
			int num = 0;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("블랙리스트 회원조회");

			while (rs.next()) {
				num++;
				System.out.println("[" + num + "번]-----------------------");
				System.out.println("이름 : " + rs.getString(3));
				System.out.println("이메일 : " + rs.getString(4));
				System.out.println("핸드폰번호 : " + rs.getString(5));
				System.out.println("");
			}

			rs.close();
			pstmt.close();
			con.close();

		} catch (SQLException sqle) {
			System.out.println("Connetion Error");
			sqle.printStackTrace();
		}

	}

	// 디비연결
	public void connectdatabase() {
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
		} catch (SQLException e) {
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
				joinmember();
				break;
			case 2:
				login();
				break;
			case 3:
				//delMember();
				break;
			case 4:
				//selectAll();
				break;
			case 5:
				//likeinsert();
				break;
			case 6:
				//unlike();
				break;
			case 7:
				//searchlike();
				break;
			case 9:
				System.out.println("프로그램을 종료합니다");
				return;
			case 0:
				adminlogin();
				break;
			default:
				System.out.println("잘못입력하셨습니다.");
				break;
			}
		}
	}

	public static void main(String[] args) {
		program a = new program();
		a.init();
	}
}
