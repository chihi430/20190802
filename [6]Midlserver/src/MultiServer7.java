
import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MultiServer7 {

	int choice;
	Scanner sc = new Scanner(System.in);
	ServerSocket serverSocket = null;
	Socket socket = null;
	Map<String, PrintWriter> clientMap;
	DB_server db = new DB_server();

	// 생성자
	public MultiServer7() {
		// 클라이언트의 출력스트림을 저장할 해쉬맵 생성
		clientMap = new HashMap<String, PrintWriter>();

		// 해쉬맵 동기화 설정.
		Collections.synchronizedMap(clientMap);
	}

	public void checkjoin() {
		//db.joinMemberserver();
	}

	public void init() {
		try {
			serverSocket = new ServerSocket(9999);
			System.out.println("서버가 시작되었습니다....");

			while (true) {
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress() + ":" + socket.getPort());
				Thread mst = new MultiServerT(socket);
				mst.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				serverSocket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void list(PrintWriter out) {
		Iterator<String> it = clientMap.keySet().iterator();
		String msg = "사용자 리스트 [";
		while (it.hasNext()) {
			msg += (String) it.next() + ",";
		}
		msg = msg.substring(0, msg.length() - 1) + "]";
		try {
			out.println(URLEncoder.encode(msg, "UTF-8"));
		} catch (Exception e) {
		}

	}

	// 접속된 모든 클라이언트들에게 메시지를 전달.
	public void sendAllMsg(String user, String msg) {
		Iterator<String> it = clientMap.keySet().iterator();

		while (it.hasNext()) {
			try {
				PrintWriter it_out = (PrintWriter) clientMap.get(it.next());
				if (user.equals(""))
					it_out.println(URLEncoder.encode(msg, "UTF-8"));
				else
					it_out.println("[" + URLEncoder.encode(user, "UTF-8") + "]" + URLEncoder.encode(msg, "UTF-8"));
			} catch (Exception e) {
				System.out.println("예외" + e);
			}
		}
	}

	public static void main(String[] args) {
		MultiServer7 ms = new MultiServer7();
		ms.init();
	}

//////////////////////////////////////////////////////////////////
// 내부 클래스
// 클라이언트로부터 읽어온 메시지를 다른 클라이언트(socket)에 보내는 역할을 하는 메서드

	class MultiServerT extends Thread {
		Socket socket;
		PrintWriter out = null;
		BufferedReader in = null;

		public MultiServerT(Socket socket) {
			this.socket = socket;
			try {
				out = new PrintWriter(this.socket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), "UTF-8"));
			} catch (Exception e) {
				System.out.println("예외 : " + e);
			}
		}

		@Override
		public void run() {
			// String s = "";
			String name = "";
			String password = "";
			String choice = ""; 
			String id = "";
			String email ="";
			String phoneNumber="";
			String RS = "FALSE";
			StringTokenizer st; 
			try {
				choice =in.readLine();
				//choice1 = st.nextToken(choice,"");
				choice = URLDecoder.decode(choice, "UTF-8");
				System.out.println(choice+"선택한 옵션");
				
				
				if(choice.equals("1")) {
					id = in.readLine();
					id = URLDecoder.decode(id, "UTF-8");
					System.out.println("ID 중복체크  : "+id);
					
					if(db.checkID(id) == true) {
						RS="TRUE";							
						out.println(RS);
						System.out.println("중복입니다.");
					}
					password = in.readLine();
					password = URLDecoder.decode(password, "UTF-8");
					
					name = in.readLine();
					name = URLDecoder.decode(name, "UTF-8");
					
					email = in.readLine();
					email = URLDecoder.decode(email, "UTF-8");
					
					phoneNumber = in.readLine();
					phoneNumber = URLDecoder.decode(phoneNumber, "UTF-8");
					
					db.joinDB(id, password, name, email, phoneNumber);
				}
				
				else if(choice.equals("2")) {
					id = in.readLine();
					id = URLDecoder.decode(id, "UTF-8");
					System.out.println("ID : " + id);
					
					password = in.readLine();
					password = URLDecoder.decode(password, "UTF-8");
					System.out.println("패스워드  :" + password);
					//db.loginDB(id, password);
					if(db.loginDB(id, password)==true) {
						clientMap.put(name, out);
						System.out.println("현재 접속자 수는" + clientMap.size() + "명 입니다.");
					}else {
						System.out.println("정보가 일치하지않습니다.");
					}
					
				}

				// sendAllMsg("",name + "님이 입장하셨습니다.");

				// 현재 사용자를 서버에 뿌려줌


				// 명령어
				String s = "";

				while (in != null) {
					s = in.readLine();
					s = URLDecoder.decode(s, "UTF-8");
					System.out.println(s);
					if (s.equals("/list"))
						list(out);
					else {
					}
					// sendAllMsg(name,s);
				}

			} catch (Exception e) {
				System.out.println("예외" + e);
			} finally {
				clientMap.remove(name);
				System.out.println(name + "님이 퇴장하셨습니다.");
				// sendAllMsg("",name + "님이 퇴장하셨습니다.");
				System.out.println("현재 접속자 수는 " + clientMap.size() + "명 입니다.");

				try {
					in.close();
					out.close();

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}
}