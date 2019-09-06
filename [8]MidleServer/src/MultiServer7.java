import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

//import MultiServer.MultiServerRec;

public class MultiServer7 {

	ServerSocket serverSocket = null;
	Socket socket = null;
	Map<String, PrintWriter> clientMap;

	// 지역별 해쉬맵을 관리하는 해시맵
	HashMap<String, HashMap<String, MultiServerT>> globalMap;
	String toNameTmp = null;// 1:1대화 상대
	String choice;
	Scanner sc = new Scanner(System.in);
	DB_server db = new DB_server();
	Chatroom[] chat = new Chatroom[6];
	int num = 1;
	String chatingNo = null;
	String id;

	public class Chatroom {
		String chatno;
		String chatname;
		String chatboss;
		int member;
		int memberMax;
		HashMap<String, PrintWriter> clientMap2 = new HashMap<String, PrintWriter>();

		Chatroom() {

			Collections.synchronizedMap(clientMap2);

		}
	}

	// 생성자
	public MultiServer7() {
		// 클라이언트의 출력스트림을 저장할 해쉬맵 생성
		clientMap = new HashMap<String, PrintWriter>();
		// 해쉬맵 동기화 설정
		Collections.synchronizedMap(clientMap);

		chat[0] = new Chatroom(); // 대기실
		chat[1] = new Chatroom(); // 채팅방 1,2,3,4,5
		chat[2] = new Chatroom();
		chat[3] = new Chatroom();
		chat[4] = new Chatroom();
		chat[5] = new Chatroom();

	}

	public void init() {
		try {
			serverSocket = new ServerSocket(9999); // 9999포트로 서버소켓 객체생성
			System.out.println("서버가 시작되었습니다.");

			while (true) {
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress() + ":" + socket.getPort());

				Thread msr = new MultiServerT(socket); // 쓰레드 생성
				msr.start(); // 쓰레드 시동
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

	// 접속자 리스트 보내기
	public void list(PrintWriter out) {
		// 출력스트림을 순차적으로 얻어와서 해당 메시지를 출력한다.
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

	// 해당 클라이언트가 속해있는 그룹에 대해서만 메시지 전달.
	public void sendGroupMsg(String loc, String msg) {

		// 출력 스트림을 순차적으로 얻어와서 해당 메시지를 출력한다.
		HashMap<String, MultiServerT> gMap = globalMap.get(loc);
		Iterator<String> group_it = globalMap.get(loc).keySet().iterator();
		while (group_it.hasNext()) {
			try {
				MultiServerT st = gMap.get(group_it.next());

				if (!st.chatMode) {
					st.out.println(msg);
				}

			} catch (Exception e) {
				System.out.println("채팅예외");
			}
		}
	}

	// 1:1 대화
	public void sendPvPMsg(String loc, String toName, String fromName, String msg) {

		try {
			globalMap.get(loc).get(toName).out.println(msg);
			globalMap.get(loc).get(fromName).out.println(msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// sendPvPMsg()-----------

	// 귓속말
	public void sendToMsg(String loc, String fromName, String toName, String msg) {

		try {

			globalMap.get(loc).get(toName).out.println("귓:from(" + fromName + ")=>" + msg);
			globalMap.get(loc).get(fromName).out.println("귓:to(" + toName + ")=>" + msg);

		} catch (Exception e) {
			System.out.println("예외:" + e);
		}

	}// sendAllMsg()-----------

	// 접속된 모든 클라이언트들에게 메시지를 전달
	public void sendAllMsg(String user, String msg) {
		// 출력스트림을 순차적으로 얻어와서 해당 메시지를 출력한다.
		Iterator<String> it = clientMap.keySet().iterator();

		while (it.hasNext()) {
			try {
				PrintWriter it_out = (PrintWriter) clientMap.get(it.next());
				if (user.equals(""))
					it_out.println(URLEncoder.encode(msg, "UTF-8"));
//                  it_out.println(msg);
				else
					it_out.println("[" + URLEncoder.encode(user, "UTF-8") + "]" + URLEncoder.encode(msg, "UTF-8"));
//                  it_out.println("["+user+"]"+msg);
			} catch (Exception e) {
				System.out.println("예외:" + e);
			}
		}
	}

	public static void main(String[] args) {
		// 서버객체 생성
		MultiServer7 ms = new MultiServer7();
		ms.init();
	}

	/////////////////////////////////////////////////////////////////////
	// 내부 클래스
	// 클라이언트로부터 읽어온 메시지를 다른 클라이언트(socket)에 보내는 역할을 하는 메서드

	class MultiServerT extends Thread {
		Socket socket;
		PrintWriter out = null;
		String loc = ""; // 지역 저장

		String toNameTmp = null;// 1:1대화 상대
		BufferedReader in = null;
		boolean chatMode; // 1:1대화모드 여부

		// 생성자
		public MultiServerT(Socket socket) {
			this.socket = socket;
			try {
				out = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream(), StandardCharsets.UTF_8),
						true);
				// out = new PrintWriter(this.socket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), "UTF-8"));
			} catch (Exception e) {
				System.out.println("예외:" + e);
			}
		}

		// 채팅방1에 접속된 클라이언트들에게 메시지 출력 메소드
		public void sendChatNo_1(String user, String msg) {
			// 출력스트림을 순차적으로 얻어와서 해당 메시지를 출력한다.
			Iterator<String> it_chat = chat[1].clientMap2.keySet().iterator();

			while (it_chat.hasNext()) {
				try {
					PrintWriter it_out = (PrintWriter) chat[1].clientMap2.get(it_chat.next());
					if (user.equals(""))
						it_out.println(URLEncoder.encode(msg, "UTF-8"));
//	                  it_out.println(msg);
					else
						it_out.println(chat[1].chatno + "Room : " + "[" + URLEncoder.encode(user, "UTF-8") + "] "
								+ URLEncoder.encode(msg, "UTF-8"));
//	                  it_out.println("["+user+"]"+msg);
				} catch (Exception e) {
					System.out.println("예외:" + e);
				}
			}
		}
	       public void sendmsg(String msg){

	             int start = msg.indexOf(" ") +1;
	             int end = msg.indexOf(" ", start);
	            
	             if(end != -1){
	                    String to = msg.substring(start, end);
	                    String msg2 = msg.substring(end+1);
	                    Object obj = clientMap.get(to);

	                    if(obj != null){

	                           out = (PrintWriter)obj;
	                           out.println(id + "님이 다음의 귓속말을 보내셨습니다. :" +msg2);
	                           out.flush();

	                    }
	             }
	       }

		// 채팅방 정보 리스트 보내기
		public void chatlist(PrintWriter out) {

			int i = 0;
			Iterator<String> it = null;
			while (i < 5) {
				it = chat[i].clientMap2.keySet().iterator();
				//String msg = "채팅방 정보 리스트 \n[";
				while (it.hasNext()) {
					it.next();
				}

				//msg = msg.substring(0, msg.length()) + "]";
				try {
					if (chat[i].chatname.equals(null)) {
						System.out.print("");
					} else {
						// out.println(URLEncoder.encode(msg, "UTF-8"));
						out.println("["+chat[i].chatno+"번방] -----------------------------");
						//out.println("방번호 : " + chat[i].chatno);
						out.println("방이름 : " + chat[i].chatname);
						out.println("방장 : " + chat[i].chatboss);												
						out.println("방인원 : " + chat[i].member + "/" + chat[i].memberMax);						
					}
				} catch (Exception e) {
				}
				i++;
			}
		}

		// 그 로그인 메뉴

		public void watingroom() {
			out.println();
			out.println(".ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ.");
			out.println("                                       ");
			out.println("                "+id+"님  입장 환영합니다!!      ");
			out.println("                                       ");
			out.println("                           *현재 대기실입니다");
			out.println("'ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ'");
			out.println();

			sendAllMsg("", id + "님이 대기실에 입장하셨습니다.");
			
			out.println(">>대화방 입장하기                                   ex) /gochat   ");
			out.println(">>대화방 만들기                                      ex) /chatmake ");
			out.println(">>대화방 리스트 조회                               ex) /chatlist ");
			out.println(">>사용자 리스트 조회                               ex) /list     ");
			out.println();
		}

		// 쓰레드를 사용하기 위해서 run()메서드 재정의
		@Override
		public void run() {

			String op = ""; // 클라이언트로부터 받은 이름을 저장할 변수
			String op1 = "";
			String name = "";
			String password = "";
			// String id = "";
			StringTokenizer st;

			try {
				while (true) {
					out.println("[메뉴를 선택하세요!]");
					out.println("1/ : 회원가입");
					out.println("2/ : 로그인");
					out.println("9/ : 종료");
					out.println("0/ : 관리자 로그인");

					op = in.readLine(); // 클라이언트가 처음으로 보내는 메시지는 이름
					op = URLDecoder.decode(op, "UTF-8"); // 한글번역
					// 명령어 짜르기
					st = new StringTokenizer(op);
					op1 = st.nextToken();

					// 회원가입
					if (op1.endsWith("/")) { // "/"로 끝나는 조건으로 명령구분
						if (op1.equals("1/")) {
							while (true) {
								out.println("회원가입 시작");
								out.println("아이디입력 해주세요 : ");
								id = in.readLine();
								id = URLDecoder.decode(id, "UTF-8");
								System.out.println("ID 중복체크  : " + id);
								if (db.checkID(id) == true) {
									out.println("중복입니다. 아이디를 다시입력해주세요");
									// 사용자에게 뿌려줌
									continue;
								}

								System.out.println("비밀번호 입력 중..");
								out.println("비밀번호 입력해주세요 : ");
								password = in.readLine();
								password = URLDecoder.decode(password, "UTF-8");

								System.out.println("이름 입력 중..");
								out.println("닉네임을 입력해주세요 :");
								name = in.readLine();
								name = URLDecoder.decode(name, "UTF-8");

								db.joinDB(id, password, name);
								break; // 회원가입 되고나서 반복문 종료
							}
							continue; // 회원가입 끝나고 다시 메인 화면 복귀
						}
						// 로그인
						else if (op1.equals("2/")) {
							while (true) {
								out.println("로그인 페이지");
								out.println("ID : ");
								id = in.readLine();
								id = URLDecoder.decode(id, "UTF-8");
								out.print(id);

								out.print("PASSWORD  :");
								password = in.readLine();
								password = URLDecoder.decode(password, "UTF-8");
								out.println(password);

								if (db.loginDB(id, password) == true) {
									clientMap.put(id, out);
									System.out.println("현재 접속자수는" + clientMap.size() + "명 입니다.");
									break;
								} else {
									out.println("정보가 일치하지않습니다.");
									continue;
								}
							}
						} else if (op1.equals("9/")) {
							System.out.println("");
							System.out.println("프로그램을 종료합니다.");
							socket.close();
							return;
						} else {
							out.println("옵션 선택 : 잘못입력하였습니다.");
							continue;
						}
						break;
					} else {
						System.out.println("명령방식이 잘못되었습니다. 다시입력해주세요");
						continue;
					}
				}

				// 입력스트림이 null이 아니면 반복.
				// 대기실 화면
				watingroom();

				String s = "";
				while (in != null) {

					s = in.readLine();
					s = URLDecoder.decode(s, "UTF-8");
					System.out.println(s);

					if (s.startsWith("/")) { // 슬래시로 시작할때 명령어 처리
						// 대기실에 사용자 리스트 뿌리기
						if (s.equals("/list")) {
							list(out);
						}
						// 채팅방 만들기
						else if (s.equals("/chatmake")) {
							while (true) {

								String anum = String.valueOf(num);
								if (chat[num].clientMap2.size() > 0) {
									num++;
								}
								out.println("대화방 만들기");
								out.println("방제목을 설정해주세요 : ");
								chat[num].chatname = in.readLine();
								chat[num].clientMap2.put(id, out); // 0번방에 나의 id를 담는다.
								chat[num].chatno = anum;
								chat[num].chatboss = id;
								chat[num].member = 1;
								chat[num].memberMax = 5;
								chat[num].clientMap2.remove(id);
								num++;

								System.out.println(id + " 님이 채팅방개설!");
								out.println("채팅방이 개설되었습니다.");

								// System.out.println("클라이언트맵 사이즈"+chat[0].clientMap2.size());
								System.out.println(clientMap.size());

								break;
							}
							// 채팅방 입장!
						} else if (s.equals("/gochat")) {

							out.println("방 번호를 설정해 주세요(0,1,2,3,4,5)");
							out.println("0: 대기실");

							chatingNo = in.readLine();
							int a = Integer.parseInt(chatingNo); // 배열로 선언된 방번호를 입력받고 정수형으로 반환
							chat[a].chatno = chatingNo;
							System.out.println(chat[a].clientMap2.containsKey(id));

//							 if(chat[a].clientMap2.containsKey(chat[a].chatboss)) {
//								System.out.println("해당방은 입장 하실 수 없습니다.");
//								continue;
//							}

							if ("0".equals(chatingNo)) {
								chat[0].clientMap2.put(id, out);
								out.println(id + "님 대기실 입장!");
								watingroom();

							} else if ("1".equals(chatingNo)) {
								chat[1].clientMap2.put(id, out);
								out.println(id + "님  대화방 입장!");
								chat[1].member = chat[a].clientMap2.size();
								if (chat[1].member == chat[a].memberMax) {  // 방멤버가 정원초과일경우의 조건문
									System.out.println("정원초과 다른방을 이용해주세요");
									continue;
								}
								if("1".equals(chatingNo)==false) {
									chat[num].clientMap2.remove(id);	
								}	
								// sendChatNo_1(id,s); // 0번방 채팅

							} else if ("2".equals(chatingNo)) {
								chat[2].clientMap2.put(id, out);
								out.println(id + "님 대화방 입장!");
								chat[2].member = chat[a].clientMap2.size();
								if (chat[2].member == chat[0].memberMax) {
									System.out.println("정원초과 다른방을 이용해주세요");
									continue;
								}
								if("2".equals(chatingNo)==false) {
									chat[2].clientMap2.remove(id);	
								}	
							}
						} else if (s.equals("/chatlist")) {
							out.println("채팅방 리스트 조회!");
							chatlist(out);
						} else if(s.indexOf("/to")==0) {
							System.out.println("귓속말");
							sendmsg(s);
							continue;
						}
					} if ("1".equals(chatingNo)) {
						sendChatNo_1(id, s); // 채팅방 1번에서 채팅
					} else {
						sendAllMsg(id, s); // 전체 채팅
					}

				}

				// System.out.println("Bye...");
			} catch (Exception e) {
				System.out.println("예외:" + e);
			} finally {
				// 예외가 발생할 때 퇴장. 해쉬맵에서 해당 데이터 제가.
				// 보통 종료하거나 나가면 java.net.SocketException: 예외발생
				clientMap.remove(id);
				sendAllMsg("", id + "님이 퇴장하셨습니다.");
				System.out.println("현재 접속자 수는 " + clientMap.size() + "명 입니다.");

				try {
					in.close();
					out.close();
					socket.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
////////////////////////////////////////////////////////////
}