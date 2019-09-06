
import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class MainClient {
	static {
		try {
			Class.forName("orcle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			// cnfe.printStackTrace();
		}
	}
	Connection con = null;
	Scanner s = new Scanner(System.in);
	Socket socket;

	
	public static void main(String[] args) throws UnknownHostException, 
												  IOException 
	{
		MainClient a = new MainClient();
		a.init();
	}

	public void showMenu() {
		System.out.println("[메뉴를 선택하세요!]");
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("9. 종료");
		System.out.println("0. 관리자 로그인");
		System.out.println("선택 : ");
	}
	
	public void loginmenu() {

		System.out.println("1. 대화방 접속");
		System.out.println("9. 로그아웃");
		System.out.println("선택 : ");
		while(true) {
			
			int choice;
			choice = s.nextInt();
			
			switch(choice) {
			case 1:
				System.out.println("대화방 접속");
				
				break;
			case 9:
				System.out.println("로그아웃");
				return;
			default:
				break;
			}
		}
		
	}
	
	// 서버 회원가입
	public void joinMemberserver() {
		
		String s_password = null;
		String s_passcheck = null;
		String s_id = null;
		String s_name = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs;
		
		System.out.println("회원가입 시작");
		while (true) {
			System.out.print("id  : ");
			s_id = s.nextLine();

			// 중복체크
			String sql = "select * from pmember where pid = ?";
			try {
				pstmt1 = con.prepareStatement(sql);
				pstmt1 = con.prepareStatement(sql);
				pstmt1.setString(1, s_id);
				rs = pstmt1.executeQuery();

				// 중복 쿼리값 가져와서 비교
				if (rs.next()) {
					if (rs.getString(1).equals(s_id)) {
						System.out.println("중복된 이름입니다.");
						continue;
					}
				}
				
				pstmt1.close();
				rs.close();
				con.close();
			}

			catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		// 비밀번호 확인
		while (true) {
			System.out.println("비밀 번호 : ");
			s_password = s.nextLine();
			System.out.println("비밀 번호 확인 : ");
			s_passcheck = s.nextLine();
			if (s_password.equals(s_passcheck)) {
				break;
			} else {
				System.out.println("비밀번호가 맞지 않습니다. 다시 입력하세요");
			}
		}

		System.out.print("이름  : ");
		s_name = s.nextLine();
		System.out.println("이메일 : ");
		String s_email = s.nextLine();
		System.out.println("전화 번호 : ");
		String s_phoneNumber = s.nextLine();
		
				
		PrintWriter out = null;
		// 서버연결
		try {
			String ServerIP = "localhost";
			//String ServerIP = args[0];
			socket = new Socket(ServerIP, 9999);
			System.out.println("디비에 "+s_id+"님이 회원 가입 되었습니다.");
			
			//서버에서 보내는 메시지를 사용자의 콘솔에 출력하는 쓰레드
//			Thread receiver = new Reciver5(socket);
//			receiver.start();
						
//			//사용자로부터 얻은 문자열을 서버로 전송해주는 역할을 하는 쓰레드			
			Thread sender1 = new JoinSender(socket, s_id, s_password,s_name,s_email,s_phoneNumber);
			sender1.start();
				
		} catch (Exception e) {
			System.out.println("예외[MultiClient class:]" + e);
		}
	}
	
	
	////////// 서버 로그인
	public void serverlogin() {
		System.out.println("ID를 입력해 주세요");
		String s_id = s.nextLine();
		System.out.println("비밀번호를 입력해 주세요");
		String s_password = s.nextLine();

		PrintWriter out = null;

		try {
			String ServerIP = "localhost";
			//String ServerIP = args[0];
			Socket socket = new Socket(ServerIP, 9999);
			System.out.println("서버와 연결이 되었습니다.....");
			
			//서버에서 보내는 메시지를 사용자의 콘솔에 출력하는 쓰레드
			//Thread receiver = new Reciver5(socket);
			//receiver.start();
						
			//사용자로부터 얻은 문자열을 서버로 전송해주는 역할을 하는 쓰레드			
			Thread sender = new Sender5(socket, s_id, s_password);
			sender.start();
				
		} catch (Exception e) {
			System.out.println("예외[MultiClient class:]" + e);
		}
		loginmenu();
	}
	
	public void connectdatabase() {
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void init() {
		String id = null;
		String password = null;

		int choice;

		while (true) {
			connectdatabase();
			showMenu();
			choice = s.nextInt();
			s.nextLine();
			switch (choice) {
			case 1:
				serverlogin();
				break;
			case 2:
				joinMemberserver();
				break;
			case 3:				
				Sender5 a = new Sender5(socket, id, password);
				a.run();
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
				//adminlogin();
				break;
			default:
				System.out.println("잘못입력하셨습니다.");
				break;
			}
		}
	}
	
}
