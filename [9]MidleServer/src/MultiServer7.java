import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
	// Map<Integer,Chatroom> clientChatroom;

	String toNameTmp = null;// 1:1대화 상대
	String choice;
	Scanner sc = new Scanner(System.in);
	DB_server db = new DB_server();
	Chatroom[] chat = new Chatroom[6];
	int selectRoomnum;
	int nmaxMamber; // 방정원
	int nMember; // 멤버수
	int num = 1;
	String id;
	// ArrayList roomList;

	public class Chatroom {
		String chatno;
		String chatname;
		String chatboss;
		String chatpassword;
		boolean openroomcheck = true; // 공개 비공개
		boolean existroomcheck = true;
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
		// clientChatroom = new HashMap<Integer,Chatroom>();
		// 해쉬맵 동기화 설정
		Collections.synchronizedMap(clientMap);
		// clientChatroom.put(0, new Chatroom(0,"admin"));

		chat[0] = new Chatroom(); // 대기실
		chat[0].chatno = "0";
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

	// 접속된 모든 클라이언트들에게 메시지를 전달
	public void roomControl(String user, String exituser) {
		int nNum=1;
		Iterator<String> it = null;
		// 방번호 위치찾는 중
		for (int i = 0; i < chat.length; i++) {
			if (chat[i] != null) {
				it = chat[i].clientMap2.keySet().iterator();
			} else {
				break;
			}
			while (it.hasNext()) {
				if (user.equals(it.next())) {
					nNum = i;
				}
			}
		}		
		it = chat[nNum].clientMap2.keySet().iterator();

		
			try {
				PrintWriter it_out = (PrintWriter) chat[nNum].clientMap2.get(exituser);			
				it_out.println(URLEncoder.encode("/exit", "UTF-8"));
				
 			} catch (Exception e) {
				System.out.println("예외:" + e);
			}
		}
	

	// 접속된 모든 클라이언트들에게 메시지를 전달
	public void sendAllMsg(String user, String msg) {

		// 출력스트림을 순차적으로 얻어와서 해당 메시지를 출력한다.
		Iterator<String> it = chat[0].clientMap2.keySet().iterator();

		while (it.hasNext()) {
			try {
				PrintWriter it_out = (PrintWriter) chat[0].clientMap2.get(it.next());
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

////////////////////////////////////////////////////////--main/////////
	public static void main(String[] args) {
		// 서버객체 생성
		MultiServer7 ms = new MultiServer7();
		ms.init();
	}

////////////////////////////////////////////////////////////////////////
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

		// 채팅방에 접속된 클라이언트들에게 메시지 출력 메소드
		public void sendChatRoomMember(String user, String msg) {
			// 출력스트림을 순차적으로 얻어와서 해당 메시지를 출력한다.
			int nNum = 0;
			Iterator<String> it = null;
			// 방번호 위치찾는 중
			for (int i = 0; i < chat.length; i++) {
				if (chat[i] != null) {
					it = chat[i].clientMap2.keySet().iterator();
				} else {
					break;
				}
				while (it.hasNext()) {
					if (user.equals(it.next())) {
						nNum = i;
					}
				}
			}
			it = chat[nNum].clientMap2.keySet().iterator();
			while (it.hasNext()) {
				try {
					PrintWriter it_out = (PrintWriter) chat[nNum].clientMap2.get(it.next());
					if (user.equals(""))
						it_out.println(URLEncoder.encode(msg, "UTF-8"));
//	                  it_out.println(msg);
					else if (chat[nNum].chatno.equals("0")) {
						it_out.println("대기실  :  " + "[" + URLEncoder.encode(user, "UTF-8") + "] "
								+ URLEncoder.encode(msg, "UTF-8"));
					} else {
						it_out.println(chat[nNum].chatno + "번방  :  " + "[" + URLEncoder.encode(user, "UTF-8") + "] "
								+ URLEncoder.encode(msg, "UTF-8"));
//	                  it_out.println("["+user+"]"+msg);
					}
				} catch (Exception e) {
					System.out.println("채팅방 대화 예외:" + e);
				}
			}
		}

		// 귓속말
		public void sendmsg(String id, String msg) {

			int start = msg.indexOf(" ") + 1;
			int end = msg.indexOf(" ", start);

			if (end != -1) {
				String to = msg.substring(start, end);
				String msg2 = msg.substring(end + 1);
				Object obj = clientMap.get(to);

				if (obj != null) {
					out = (PrintWriter) obj;
					out.println(id + "님이 다음의 귓속말을 보내셨습니다. :" + msg2);
					out.flush();
				}
			}
		}

		// 채팅에 접속자 리스트 보내기
		public void member(PrintWriter out) {

			int i = 0;
			Iterator<String> it = null;
			while (i < 5) {
				it = chat[i].clientMap2.keySet().iterator();

				String msg = "채팅방 정보 리스트 \n방에 등록된 아이디 : [";
				while (it.hasNext()) {
					msg += (String) it.next() + ", ";
				}

				msg = msg.substring(0, msg.length()) + "]";
				try {
					if (chat[i].chatname.equals(null)) {
						System.out.print("");
					} else {
						out.println(URLEncoder.encode(msg, "UTF-8"));
						out.println("인원 : " + chat[i].member + "/" + chat[i].memberMax);
						out.println("방이름 : " + chat[i].chatname);
						out.println("방장 : " + chat[i].chatboss);
					}
				} catch (Exception e) {
				}

				i++;
			}

		}

		// 채팅방 정보 리스트 보내기
		public void chatlist(PrintWriter out) {

			int i = 0;
			Iterator<String> it = null;
			while (i < 5) {
				it = chat[i].clientMap2.keySet().iterator();
				// String msg = "채팅방 정보 리스트 \n[";
				while (it.hasNext()) {
					it.next();
				}

				// msg = msg.substring(0, msg.length()) + "]";
				try {
					if (chat[i].chatname.equals(null)) {
						System.out.print("");
					} else {
						// out.println(URLEncoder.encode(msg, "UTF-8"));
						out.println("[" + chat[i].chatno + "번방] -----------------------------");
						// out.println("방번호 : " + chat[i].chatno);
						out.println("방이름 : " + chat[i].chatname);
						out.println("방장 : " + chat[i].chatboss);
						// int realmember = chat[i].member;
						out.println("방인원 : " + (nMember) + "/" + nmaxMamber);
						if (chat[i].openroomcheck == false) {
							out.println("공개여부 : 비공개");
						} else {
							out.println("공개여부 : 공개");
						}

					}
				} catch (Exception e) {
				}
				i++;
			}
		}

		// 로그인 메뉴
		public void waitingroom(String id) {

			out.println();
			out.println(".ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ.");
			out.println("                                       ");
			out.println("                " + id + "님  입장 환영합니다!!      ");
			out.println("                                       ");
			out.println("                           *현재 대기실입니다");
			out.println("'ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ'");
			out.println();
			sendAllMsg("", id + "님이 대기실에 입장하셨습니다.");

			out.println(">>대화방 입장하기                                   ex) /gochat              ");
			out.println(">>대화방 입장하기                                   ex) /exit              ");
			out.println(">>대화방 만들기                                      ex) /chmake            ");
			out.println(">>대화방 리스트 조회                               ex) /chatlist            ");
			out.println(">>사용자 리스트 조회                               ex) /list                ");
			out.println(">>사용자에게 귓속말                                ex) /to [사용자] [메시지]   ");
			out.println();

		}

		// 쓰레드를 사용하기 위해서 run()메서드 재정의
		@Override
		public void run() {

			String op = ""; // 클라이언트로부터 받은 이름을 저장할 변수
			String op1 = "";
			String name = "";
			String password = "";
			String id = "";
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
								out.println("로그인 페이지 입니다 아이디와 비밀번호를 입력해주세요\n");
								out.println("ID : ");
								id = in.readLine();
								id = URLDecoder.decode(id, "UTF-8");
								out.print(id + "\n");

								out.println("PASSWORD  :");
								password = in.readLine();
								password = URLDecoder.decode(password, "UTF-8");
								out.print(password);

								if (db.loginDB(id, password) == true) {
									clientMap.put(id, out);
									System.out.println("현재 접속자수는" + clientMap.size() + "명 입니다.");
									chat[0].clientMap2.put(id, out);
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
				waitingroom(id);

				String s = "";
				String boom = "";
				String roompassword = "";
				String kickname = "";
				while (in != null) {

					s = in.readLine();
					s = URLDecoder.decode(s, "UTF-8");

					// 명령어 처리 부분--------------------------------------------------------------

					if (s.startsWith("/")) { // 슬래시로 시작할때 명령어 처리
						// 대기실에 사용자 리스트 뿌리기
						if (s.equals("/list")) {
							list(out);
						}
						// 채팅방 만들기
						else if ("/chmake".equals(s)) {

							String anum = String.valueOf(num);
							out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
							out.println("                     대화방 만들기                              ");
							out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
							out.println();
							out.println("방제목을 설정해주세요 : ");
							chat[num].chatname = in.readLine();
							chat[num].chatno = anum;
							chat[num].chatboss = id;
							// 정원설정
							out.println("방정원을 설정해주세요");
							String maxMamber = String.valueOf(chat[num].memberMax);
							maxMamber = in.readLine();
							nmaxMamber = Integer.parseInt(maxMamber);

							// 공개 비공개 설정
							out.println("방을 비공개 하시겠습니까?  [y/n]");
							String openclose = in.readLine();

							if ("y".equals(openclose) || "Y".equals(openclose)) {
								// false는 비공개
								chat[num].openroomcheck = false;
								out.println("방 비밀번호를 입력해 주세요 : ");
								chat[num].chatpassword = in.readLine();

							}

							// 맞는 숫자에 번방에 나의 id를 담는다.
							chat[num].clientMap2.put(id, out);// 설정된 방으로 이동
							// 자신이 만든 대화방에 자신의 수 추가
							chat[num].member = chat[num].clientMap2.size();
							nMember = chat[num].member;

							chat[0].clientMap2.remove(id);// 대기실에서 사용자 위치이동
							num++;

							System.out.println(id + " 님이 채팅방개설!");

							out.println("\n 채팅방이 개설되었습니다.");

							// System.out.println("클라이언트맵 사이즈"+chat[0].clientMap2.size());
							// System.out.println(clientMap.size());

						} else if ("/schmake".equals(s)) {

						}

						// 채팅방 입장!
						else if ("/gochat".equals(s)) {
							chat[selectRoomnum].clientMap2.remove(id);// 전에 있던방에서 일단 나가기
							chat[0].clientMap2.put(id, out); // 일단 대기실 초기화

							out.println("방 번호를 설정해 주세요(0,1,2,3,4,5)");
							out.println("* 0: 대기실");
							selectRoomnum = Integer.parseInt(in.readLine()); // 배열로 선언된 방번호를 입력받고 정수형으로 반환
							String S_selectRoomnum = String.valueOf(selectRoomnum);

							// 방이 만들어져있는지 체크하는 과정
							boolean roomcheck = chat[selectRoomnum].clientMap2
									.containsKey(chat[selectRoomnum].chatboss);
							System.out.println("roomcheck 상태 : " + roomcheck);

							// 방선택 조건문 대기실 이동
							if ("0".equals(S_selectRoomnum)) {
								roomcheck = true;
								chat[selectRoomnum].clientMap2.remove(id);
								// System.out.println("0번눌렀을때 조건문 작용!");
								chat[0].clientMap2.put(id, out);

								waitingroom(id);
								continue;
							}
							// 채팅방이 없으면 false
							else if (roomcheck == false) {
								out.println("방이 만들어지지 않아 입장 하실수 없습니다.");
								continue;
							}
							// 비공개 방체크
							if (chat[selectRoomnum].openroomcheck == false) {
								out.println("방 비밀번호를 입력해주세요");
								roompassword = in.readLine();
								if (chat[selectRoomnum].chatpassword.equals(roompassword)) {
									out.println("정답!");
								} else {
									out.println("비밀번호 가 틀렸습니다. 다시입력해주세요 !");
									waitingroom(id);
									continue;
								}

							}
							// 방에있는 사람이 초과되었는 확인
							if (chat[selectRoomnum].member == nmaxMamber) {
								System.out.println("정원초과입니다. 다른 방을 이용해주세요");
								continue;
							}

							chat[selectRoomnum].clientMap2.put(id, out);
							chat[0].clientMap2.remove(id);
							// System.out.println(clientMap.size());
							out.println(id + "님 대화방 입장 하셨습니다.");

							// 채팅방멤버는 해쉬맵에 등록된 사이즈 크기만큼
							System.out.println("채팅방 인원 : " + nMember);

						}

						else if ("/exit".equals(s)) {
							int whoboss = 1;

							System.out.println(" 방장확인 : " + chat[selectRoomnum].chatboss);
							if (chat[selectRoomnum].chatboss.equals(id)) {
								if (chat[selectRoomnum].member == 1) {
									out.println("[" + id + "] 님 혼자남으신 방은 폭파를 해야합니다.");
									out.println("방을 폭파시키시겠습니까? ex) /폭파");
									boom = in.readLine(); // 명령어 조건
									boom = URLDecoder.decode(boom, "UTF-8");
									if ("/폭파".equals(boom)) {
										out.println("방폭발!");
										System.out.println("방터졌나 확인 전 : " + chat[selectRoomnum].clientMap2.isEmpty());
										chat[selectRoomnum].clientMap2.clear();
										System.out.println("방터졌나 확인 후 : " + chat[selectRoomnum].clientMap2.isEmpty());
										
										chat[0].clientMap2.put(id, out);
									}
									continue;
								}
								out.println("당신은 방장 입니다! 채팅방 사용자들에게 임위로 넘기도록 하겠습니다.");
								Iterator<String> it = null;
								for (int i = 1; i < chat.length; i++) {
									if (chat[i] != null) {
										it = chat[i].clientMap2.keySet().iterator();
									} else {
										break;
									}
									while (it.hasNext()) {
										if (id.equals(it.next())) {
											whoboss = i;
										}
									}
								}
								System.out.println("방장 확인:" + chat[whoboss].chatboss);
								it = chat[whoboss].clientMap2.keySet().iterator();
								if (id.equals(chat[whoboss].chatboss)) {
									while (true) {
										chat[whoboss].chatboss = it.next();
										if (id.equals(chat[whoboss].chatboss) == false) {
											System.out.println("방장 못넘겨줌 ㅅㄱ");
											break;
										}
									}
									chat[whoboss].clientMap2.remove(id);
									sendChatRoomMember(chat[whoboss].chatboss, "님이 방장이 되셨습니다.");
								} else {
									System.out.println("방장을 넘겨줄수 없습니다!");
									continue;
								}
							} else {
								System.out.println(id + "님이 대화방을 나갔습니다.");
								chat[selectRoomnum].clientMap2.remove(id);
								chat[0].clientMap2.put(id, out);

								waitingroom(id);
							}

						}

						else if ("/chatlist".equals(s)) {
							out.println("채팅방 리스트 조회!");
							chatlist(out);
						} else if (s.indexOf("/to") == 0) {
							System.out.println("귓속말");
							sendmsg(id, s);
							continue;
						} else if ("/member".equals(s)) {
							System.out.println("명령어 멤버");
							System.out.println(chat[selectRoomnum].clientMap2.containsKey(id));
							if (chat[selectRoomnum].clientMap2.containsKey(id)) {
								System.out.println(chat[selectRoomnum].clientMap2.containsKey(id));
								System.out.println("이방의 사용자는");
								member(out);
							}
						} else if ("/kick".equals(s)) {
							// 방이 존재할경우의 조건문!
							System.out
									.println(chat[selectRoomnum].clientMap2.containsKey(chat[selectRoomnum].chatboss));
							if (chat[selectRoomnum].clientMap2.containsKey(chat[selectRoomnum].chatboss)) {
								// 킥은 방장만 가능하도록!
								if (chat[selectRoomnum].chatboss.equals(id)) {
									out.println("강퇴할 ID를 입력하세요");
									kickname = in.readLine();
									kickname = URLDecoder.decode(kickname, "UTF-8");
									sendChatRoomMember(kickname, " 님을 강퇴하였습니다.");
									roomControl(id, kickname);
								}
							} else {
								out.println("대화방에서 방장만 사용가능한 명령어입니다.");
							}
						} else {
							System.out.println("잘못된 명령어 입니다.");
							continue;
						}
					}

					// sendAllMsg(id, s); // 전체 채팅

					else {
						sendChatRoomMember(id, s); // 채팅방 1번에서 채팅
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