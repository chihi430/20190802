import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChatWin extends JFrame {

	private static final long serialVersionUID = 1L;
	JTextField tf;
	JPanel p;
	TextHandler handler = null;

	Socket socket;
	PrintWriter out = null;
	BufferedReader in = null;

	String s_id;
	String s_password;
	String passcheck;
	String choice;
	String s_name;
	String s_email;
	String s_phoneNumber;

	Scanner s = new Scanner(System.in);

	String name;

	ChatWin(Socket socket) {

		// -------------------------------------------------------------------

		this.socket = socket;
		try {
			out = new PrintWriter(this.socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			// this.name = name;
			init();
			// 서버에 입력한 사용자이름을 보내준다.
			// out.println(URLEncoder.encode(name, "UTF-8"));

		} catch (Exception e) {
			System.out.println("예외S3:" + e);
		}

	}

	// Inner Class TextHandler
	class TextHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String msg = tf.getText();
			if ("".equals(msg))
				return;

			if (msg.equals("q") || msg.equals("Q")) {
				try {
					out.close();
					socket.close();
				} catch (IOException e1) {
				}
			} else {
				try {
					out.println(URLEncoder.encode(msg, "UTF-8"));
				} catch (UnsupportedEncodingException e1) {
				}
			}

			tf.setText("");
		}
	}

	public void showMenu() {
		System.out.println("[메뉴를 선택하세요!]");
		System.out.println("1/ : 회원가입");
		System.out.println("2/ : 로그인");
		System.out.println("9/ : 종료");
		System.out.println("0/ : 관리자 로그인");
		System.out.println("선택 : ");
	}

	public void init() {

		try {
			// String ServerIP = "localhost";
			// String ServerIP = args[0];
			// out = new PrintWriter(socket.getOutputStream(), true);

			while (true) {
				showMenu();
				choice = s.nextLine();
				out.println(choice);

				switch (choice) {
				case "1/":
					join(s_id, s_password, s_name, s_email, s_phoneNumber);
					break;
				case "2/":
					login(s_id, s_password);
					break;
				case "3/":
					// delMember();
					break;
				case "9/":
					// delMember();
					socket.close();
					return;
				default:
					System.out.println("잘못입력하셨습니다 다시입력하세요");
					continue;
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
			//비밀번호 공백입력시
			if(s_password.equals("")) {
				System.out.println("다시입력하세요");
				continue;
			}
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
			//아이디 공백 입력시
			while(true) {
				System.out.print("id  : ");
				s_id = s.nextLine();
				if(s_id.equals("")) {
					System.out.println("공백 입력! 다시입력하세요");
					continue;
				}
				out.println(s_id);
				in.readLine();
				System.out.println(in.readLine());
				
				break;
			}


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
			System.out.println("회원가입 샌더 에러");
		}

	}

	// 로그인 샌더 메소드
	public void login(String name, String password) {
		try {
			System.out.println("아이디 입력");
			s_id = s.nextLine();
			System.out.println("비밀번호 입력");
			s_password = s.nextLine();

			// 서버로 입력받은 데이터 전송
			// out = new PrintWriter(socket.getOutputStream(), true);
			out.println(s_id);
			out.println(s_password);

			while (true) {

				System.out.println("@1.대화방 입장");

				System.out.println("@2. etc");
				choice = s.nextLine();
				s.nextLine();
				switch (choice) {
				case "1@":
					System.out.println("대화방에 입장하였습니다.");
					this.setTitle("Chat Window");
					this.setSize(600, 100);
					this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

					p = new JPanel();
					p.setLayout(new FlowLayout());

					tf = new JTextField(40);
					p.add(tf);

					this.setContentPane(p);
					this.setVisible(true);

					handler = new TextHandler();
					tf.addActionListener(handler);
					// joinmember();
					break;
				case "2@":
					System.out.println("2. etc");
					// login(socket, s_name, s_password);
					break;
				case "3@":
					// delMember();
					break;
				default:
					System.out.println("잘못 입력하셨습니다.");
				}
			}
		} catch (Exception e) {
			System.out.println("로그인 예외  Sender.java : " + e);
		}
	}

}
