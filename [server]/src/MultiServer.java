import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

/*콘솔 멀티채팅 서버 프로그램*/
public class MultiServer {
	HashMap<String, HashMap<String, MultiServerRec>> globalMap; // 지역별 해쉬맵을 관리하는 해시맵
	ServerSocket serverSocket = null;
	Socket socket = null;
	static int connUserCount = 0; // 서버에 접속된 유저 카운트

	// 생성자
	public MultiServer() {
		globalMap = new HashMap<String, HashMap<String, MultiServerRec>>();

		// clientMap = new HashMap<String,DataOutputStream>(); //클라이언트의 출력스트림을 저장할 해쉬맵
		// 생성.
		Collections.synchronizedMap(globalMap); // 해쉬맵 동기화 설정.

		HashMap<String, MultiServerRec> group01 = new HashMap<String, MultiServerRec>();
		Collections.synchronizedMap(group01); // 해쉬맵 동기화 설정.

		HashMap<String, MultiServerRec> group02 = new HashMap<String, MultiServerRec>();
		Collections.synchronizedMap(group02); // 해쉬맵 동기화 설정.

		HashMap<String, MultiServerRec> group03 = new HashMap<String, MultiServerRec>();
		Collections.synchronizedMap(group03); // 해쉬맵 동기화 설정.

		HashMap<String, MultiServerRec> group04 = new HashMap<String, MultiServerRec>();
		Collections.synchronizedMap(group04); // 해쉬맵 동기화 설정.

		HashMap<String, MultiServerRec> group05 = new HashMap<String, MultiServerRec>();
		Collections.synchronizedMap(group05); // 해쉬맵 동기화 설정.

		HashMap<String, MultiServerRec> group06 = new HashMap<String, MultiServerRec>();
		Collections.synchronizedMap(group06); // 해쉬맵 동기화 설정.

		HashMap<String, MultiServerRec> group07 = new HashMap<String, MultiServerRec>();
		Collections.synchronizedMap(group07); // 해쉬맵 동기화 설정.

		globalMap.put("서울", group01);
		globalMap.put("경기", group02);
		globalMap.put("충청", group03);
		globalMap.put("강원", group04);
		globalMap.put("전라", group05);
		globalMap.put("경상", group06);
		globalMap.put("제주", group07);

	}// 생성자----

	public void init() {
		try {
			serverSocket = new ServerSocket(9999); // 9999포트로 서버소켓 객체생성.
			System.out.println("##서버가 시작되었습니다.");

			while (true) { // 서버가 실행되는 동안 클라이언트들의 접속을 기다림.
				socket = serverSocket.accept(); // 클라이언트의 접속을 기다리다가 접속이 되면 Socket객체를 생성.
				System.out.println(socket.getInetAddress() + ":" + socket.getPort()); // 클라이언트 정보 (ip, 포트) 출력

				Thread msr = new MultiServerRec(socket); // 쓰레드 생성.
				msr.start(); // 쓰레드 시동.
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 접속된 모든 클라이언트들에게 메시지를 전달.
	public void sendAllMsg(String msg) {

		// 출력스트림을 순차적으로 얻어와서 해당 메시지를 출력한다.
		Iterator global_it = globalMap.keySet().iterator();

		while (global_it.hasNext()) {
			try {
				HashMap<String, MultiServerRec> it_hash = globalMap.get(global_it.next());
				Iterator it = it_hash.keySet().iterator();
				while (it.hasNext()) {
					MultiServerRec st = it_hash.get(it.next());
					st.out.writeUTF(msg);
				}
			} catch (Exception e) {
				System.out.println("예외:" + e);
			}
		}
	}// sendAllMsg()-----------

	// 해당 클라이언트가 속해있는 그룹에대해서만 메시지 전달.
	public void sendGroupMsg(String loc, String msg) {

		// 출력스트림을 순차적으로 얻어와서 해당 메시지를 출력한다.
		HashMap<String, MultiServerRec> gMap = globalMap.get(loc);
		Iterator<String> group_it = globalMap.get(loc).keySet().iterator();
		while (group_it.hasNext()) {
			try {

				MultiServerRec st = gMap.get(group_it.next());

				if (!st.chatMode) {
					st.out.writeUTF(msg);
				}

			} catch (Exception e) {
				System.out.println("예외:" + e);
			}
		}
	}// sendGroupMsg()-----------

	// 1:1 대화
	public void sendPvPMsg(String loc, String toName, String fromName, String msg) {

		try {
			globalMap.get(loc).get(toName).out.writeUTF(msg);
			globalMap.get(loc).get(fromName).out.writeUTF(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// sendPvPMsg()-----------

	// 각방의 접속자수와 서버에 접속되 유저를 반환하는 메소드
	public String getEachMapSize() {

		// 출력스트림을 순차적으로 얻어와서 해당 메시지를 출력한다.
		Iterator global_it = globalMap.keySet().iterator();
		StringBuffer sb = new StringBuffer();
		// int sum = 0;
		while (global_it.hasNext()) {
			try {
				String key = (String) global_it.next();
				HashMap<String, MultiServerRec> it_hash = globalMap.get(key);
				int size = it_hash.size();
				// sum +=size;
				sb.append(key + ":" + size + "@");

			} catch (Exception e) {
				System.out.println("예외:" + e);
			}
		}
		sb.append("현재 서버에 접속된 유저수 :" + MultiServer.connUserCount);
		// System.out.println(sb.toString());

		return sb.toString();
	}// sendGroupMsg()-----------

	// 지정된 이름에만 메시지 보내기.
	public void sendToMsg(String loc, String fromName, String toName, String msg) {

		try {

			globalMap.get(loc).get(toName).out.writeUTF("귓:from(" + fromName + ")=>" + msg);
			globalMap.get(loc).get(fromName).out.writeUTF("귓:to(" + toName + ")=>" + msg);

		} catch (Exception e) {
			System.out.println("예외:" + e);
		}

	}// sendAllMsg()-----------

	// 문자열 null 값 및 "" 은 대체 문자열로 삽입가능.
	public String NVL(String str, String replace) {
		String output = "";
		if (str == null || str.trim().equals("")) {
			output = replace;
		} else {
			output = str;
		}
		return output;
	}

	// main메서드
	public static void main(String[] args) {
		MultiServer ms = new MultiServer(); // 서버객체 생성.
		ms.init();// 실행.
	}// main()------

	////////////////////////////////////////////////////////////////////////
	// ----// 내부 클래스 //--------//

	// 클라이언트로부터 읽어온 메시지를 다른 클라이언트(socket)에 보내는 역할을 하는 메서드
	class MultiServerRec extends Thread {

		Socket socket;
		DataInputStream in;
		DataOutputStream out;
		String name = ""; // 이름 저장
		String loc = ""; // 지역 저장
		String toNameTmp = null;// 1:1대화 상대
		boolean chatMode; // 1:1대화모드 여부

		// 생성자.
		public MultiServerRec(Socket socket) {
			this.socket = socket;
			try {
				// Socket으로부터 입력스트림을 얻는다.
				in = new DataInputStream(socket.getInputStream());
				// Socket으로부터 출력스트림을 얻는다.
				out = new DataOutputStream(socket.getOutputStream());
			} catch (Exception e) {
				System.out.println("예외:" + e);
			}
		}// 생성자 ------------

		// 접속된 유저리스트 문자열로 반환
		public String showUserList() {

			StringBuilder output = new StringBuilder("==접속자목록==\r\n");
			Iterator it = globalMap.get(loc).keySet().iterator(); // 해쉬맵에 등록된 사용자이름을 가져옴.

			while (it.hasNext()) { // 반복하면서 사용자이름을 StringBuilder에 추가
				try {
					String key = (String) it.next();

					// out.writeUTF(output);
					if (key.equals(name)) {
						key += " (*) ";
					}
					output.append(key + "\r\n");

				} catch (Exception e) {
					System.out.println("예외:" + e);
				}
			} // while---------
			output.append("==" + globalMap.get(loc).size() + "명 접속중==\r\n");
			return output.toString();

		}// showUserList()-----------

		@Override
		public void run() { // 쓰레드를 사용하기 위해서 run()메서드 재정의
			HashMap<String, MultiServerRec> clientMap = null;

			try {
				name = in.readUTF();
				MultiServer.connUserCount++; // 접속자수 증가.
				out.writeUTF(getEachMapSize()); // 접속된 클라이언트에게 그룹목록 제공

				loc = in.readUTF(); // 지역을 입력받아 저장.

				sendGroupMsg(loc, "##" + name + "님이 입장하셨습니다.");

				clientMap = globalMap.get(loc); // 현재그룹의 해쉬맵을 따로 저장.
				clientMap.put(name, this); // 현재 MultiServerRec인스턴스를 클라이언트맵에 저장.
				System.out.println(getEachMapSize());

				while (in != null) { // 입력스트림이 null이 아니면 반복.
					String msg = in.readUTF(); // 입력스트림을 통해 읽어온 문자열을 msg에 할당.

					if (msg.startsWith("/")) {
						if (msg.trim().equals("/접속자")) {
							out.writeUTF(showUserList()); // 접속자 출력
						} else if (msg.startsWith("/귓속말")) {
							// public String[] split(String regex, int limit)
							String[] msgArr = msg.split(" ", 3); // 받아온 msg을 " "(공백)을 기준으로 3개를 분리
							// 공백으로 스플리트 했을시 메시지에서 문제. 하지만 리미트를 정하게 되면 해결.

							if (msgArr == null || msgArr.length < 3) {
								out.writeUTF("##Help:귓속말 사용법이 잘못되었습니다.\r\n /귓속말 [상대방이름] [보낼메시지].");
							} else {
								String toName = msgArr[1];
								// String toMsg = "귓:from("+name+")=>"+((msgArr[2]!=null)?msgArr[2]:"");
								String toMsg = msgArr[2];
								if (clientMap.containsKey(toName)) { // 유저체크
									sendToMsg(loc, name, toName, toMsg);

								} else {
									out.writeUTF("##해당 유저가 존재하지 않습니다.");
								}

							} // if

						} else if (msg.startsWith("/지역")) {

							String[] msgArr = msg.split(" ");
							if (msgArr == null || msgArr.length < 2) {
								out.writeUTF("##Help:명령어 사용법이 잘못되었습니다.\r\n /지역 [변경할지역이름].");

							} else {
								String tmpLoc = msgArr[1]; // 지역

								if (globalMap.containsKey(tmpLoc) && !this.chatMode) { // 지역체크
									out.writeUTF("##지역을 " + loc + "에서 " + tmpLoc + "로 변경합니다. ");

									clientMap.remove(name); // 현재 지역 해쉬맵에서 해당 쓰레드를 제거.
									sendGroupMsg(loc, "##" + name + "퇴장합니다.");
									System.out.println("이전지역(" + loc + ")에서 에서 " + name + "제거");
									loc = tmpLoc;
									clientMap = globalMap.get(loc);
									sendGroupMsg(loc, "##" + name + "입장합니다.");
									clientMap.put(name, this); // 새로변경된 지역에 서버쓰레드 저장.

								} else {
									out.writeUTF("##입력한 지역이 존재하지 않거나 현재 이동할수없는 상태입니다.");
								} // if-----

							} // if------------

						} else if (msg.startsWith("/대화신청")) {

							if (!chatMode) {
								String toName = msg.split(" ")[1];
								out.writeUTF("##" + toName + "님께 대화신청을 합니다. ");
								if (clientMap.containsKey(toName) && !globalMap.get(loc).get(toName).chatMode) { // 유저체크
									globalMap.get(loc).get(toName).out
											.writeUTF("/Q대화신청 ## " + name + "님께서 1:1대화신청을 요청하였습니다 승낙하시겠습니까?(y,n)");
									toNameTmp = toName;
									globalMap.get(loc).get(toNameTmp).toNameTmp = name;
								} else {
									out.writeUTF("##해당 유저가 존재하지않거나 상대방이 1:1대화를 할수없는 상태입니다.");
								}

							} else {
								out.writeUTF("##1:1대화 모드이므로 대화신청을 하실수없습니다.");
							}

						} else if (msg.startsWith("/Q대화신청")) {

							String r = msg.split(" ")[1];
							if (r.equalsIgnoreCase("y")) {
								chatMode = true;
								globalMap.get(loc).get(toNameTmp).chatMode = true;
								System.out.println("##모드 변경");
								try {
									out.writeUTF("##" + toNameTmp + "님과 1:1 대화를 시작합니다.");
									globalMap.get(loc).get(toNameTmp).out.writeUTF("##" + name + "님과 1:1 대화를 시작합니다.");
								} catch (IOException e) {
									e.printStackTrace();
								}
							} else if (r.equalsIgnoreCase("n")) {
								globalMap.get(loc).get(toNameTmp).out.writeUTF("##" + name + " 님께서 대화신청을 거절하셨습니다.");
							}

						} else if (msg.startsWith("/대화종료")) {

							if (chatMode) {
								out.writeUTF("##" + toNameTmp + "님과 1:1대화를 종료합니다.");
								chatMode = false;
								globalMap.get(loc).get(toNameTmp).chatMode = false;
								globalMap.get(loc).get(toNameTmp).out.writeUTF("##" + name + "님께서 1:1대화를 종료하였습니다");

							} else {
								out.writeUTF("##1:1대화중일때만 사용할수있는 명령어입니다. ");
							}

						} else {
							out.writeUTF("##잘못된 명령어입니다.");
						}

					} else {
						// sendAllMsg(msg); //현재 소켓에서 읽어온메시지를 해쉬맵에 저장된 모든
						if (!chatMode) {
							sendGroupMsg(loc, msg);
							// 출력스트림으로 보낸다.
						} else {
							sendPvPMsg(loc, toNameTmp, name, msg);
						}
					}
				} // while()---------
			} catch (Exception e) {
				System.out.println(e + "----> ");
			} finally {
				// 예외가 발생할때 퇴장. 해쉬맵에서 해당 데이터 제거.
				// 보통 종료하거나 나가면 java.net.SocketException: 예외발생
				if (clientMap != null) {
					clientMap.remove(name);
				}

				sendGroupMsg(loc, "## " + name + "님이 퇴장하셨습니다.");
				System.out.println("##현재 서버에 접속된 유저는 " + (--MultiServer.connUserCount) + "명 입니다.");
			}
		}// run()------------
	}// class MultiServerRec-------------
		//////////////////////////////////////////////////////////////////////
}