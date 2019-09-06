import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Sender extends Thread {
	Socket socket;
	PrintWriter out = null;
	String s_id;
	String s_password;
	String passcheck;
	String choice;
	String s_name;
	String s_email;
	String s_phoneNumber;

	Scanner s = new Scanner(System.in);

	public Sender(Socket socket) {
		this.socket = socket;
		init();
		
	}

	public void showMenu() {
		System.out.println("[메뉴를 선택하세요!]");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("9. 종료");
		System.out.println("0. 관리자 로그인");
		System.out.println("선택 : ");
	}

	public void init() {

		try {
			String ServerIP = "localhost";
			// String ServerIP = args[0];
			out = new PrintWriter(socket.getOutputStream(), true);

			while (true) {
				showMenu();
				choice = s.nextLine();
				out.println(choice);

				switch (choice) {
				case "1":
					join(s_id, s_password, s_name, s_email, s_phoneNumber);
					break;
				case "2":
					login(s_id, s_password);
					break;
				case "3":
					// delMember();
					break;
				case "9":
					// delMember();
					socket.close();
					return;
				}
			}
		} catch (Exception e) {
			System.out.println("소켓에러");
		}
	}

	public void passcheck(String password) {
		while (true) {
			System.out.println("비밀 번호 : ");
			s_password = s.nextLine();
			System.out.println("비밀 번호 확인 : ");
			passcheck = s.nextLine();
			if (s_password.equals(passcheck)) {
				break;
			} else {
				System.out.println("비밀번호가 맞지 않습니다. 다시 입력하세요");
			}
		}
	}

	// 회원가입 샌더 메소드
	public void join(String id, String password, String name, String email, String phoneNumber) {
		// 아이디 중복체크,디비 체크하기위해 서버 전송
		try {
			System.out.println("회원가입을 시작합니다.");
			System.out.print("id  : ");
			s_id = s.nextLine();
			out = new PrintWriter(socket.getOutputStream(), true);
			out.println(s_id);

			// 비밀번호 확인
			passcheck(s_password);
			out.println(s_password);

			System.out.print("이름  : ");
			s_name = s.nextLine();
			out.println(s_name);

			System.out.println("이메일 : ");
			s_email = s.nextLine();
			out.println(s_email);

			System.out.println("전화 번호 : ");
			s_phoneNumber = s.nextLine();
			out.println(s_phoneNumber);

		} catch (Exception e) {
		}

	}
	
	//로그인 샌더 메소드
	public void login(String name, String password) {
		try {
			System.out.println("아이디 입력");
			s_id = s.nextLine();
			System.out.println("비밀번호 입력");
			s_password = s.nextLine();

			// 서버로 입력받은 데이터 전송
			out = new PrintWriter(socket.getOutputStream(), true);
			out.println(s_id);
			out.println(s_password);

			while (true) {

				System.out.println("1.대화방 입장");

				System.out.println("2. etc");
				choice = s.nextLine();
				s.nextLine();
				switch (choice) {
				case "1":
					System.out.println("대화방에 입장하였습니다.");
					// joinmember();
					break;
				case "2":
					// login(socket, s_name, s_password);
					break;
				case "3":
					// delMember();
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("로그인 예외  Sender.java : " + e);
		}
	}

	@Override
	public void run() {
		Scanner s = new Scanner(System.in);

		try {
			out.println(choice);
			out.println(s_id);
			out.println(s_password);

			while (out != null) {
				try {
					String s2 = s.nextLine();
					if (s2.equals("q") || s2.equals("Q")) {
						out.println(s2);
						break;
					} else {
						out.println(s_id + "=>" + s2);
					}
				} catch (Exception e) {
					System.out.println("예외S1" + e);
				}
			}
			out.close();
			socket.close();

		} catch (Exception e) {
			System.out.println("예외S2: " + e);
		}
		super.run();
	}

}
