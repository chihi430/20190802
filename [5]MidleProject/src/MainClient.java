
import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class MainClient {
	

	String s_id;
	String s_password;
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
	public void joinMemberserver() {
		
	}
	
	////////// 서버 로그인
	public void serverlogin() {
		System.out.println("ID를 입력해 주세요");
		s_id = s.nextLine();
		System.out.println("비밀번호를 입력해 주세요");
		s_password = s.nextLine();

		PrintWriter out = null;

		try {
			
			String ServerIP = "localhost";
			//String ServerIP = args[0];
			Socket socket = new Socket(ServerIP, 9999);
			out = new PrintWriter(socket.getOutputStream(), true);
			System.out.println("서버와 연결이 되었습니다.....");
			
			out.println(s_id);
			out.println(s_password);
			//서버에서 보내는 메시지를 사용자의 콘솔에 출력하는 쓰레드
			//Thread receiver = new Reciver5(socket);
			//receiver.start();
						
			//사용자로부터 얻은 문자열을 서버로 전송해주는 역할을 하는 쓰레드			
			//Thread sender = new Sender5(socket, s_id, s_password);
			//sender.start();
				
		} catch (Exception e) {
			System.out.println("예외[MultiClient class:]" + e);
		}
		
	}

	public void init() {

		int choice;

		while (true) {
			//connectdatabase();
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
				//test();
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
