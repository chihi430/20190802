import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class PJT_server {

	ServerSocket serverSocket = null;
	Socket socket =null;
	Map <String, PrintWriter> clientMap;
	Map<Integer,chatRoom> clintChatRoom;
	List <String> chatCMD;

	
	 
	
	public PJT_server() {
		//클라이언트의 출력스트림을 저장할 해쉬맵 생성
		clientMap = new HashMap<String,PrintWriter>();
		clintChatRoom = new HashMap<Integer,chatRoom>();
		// 명령어 검증을 위한 ArrayList 생성
		chatCMD = new ArrayList<String>();
		
		clintChatRoom.put(0, new chatRoom(0,"admin"));
		// 해쉬맵 동기화 설정.
		Collections.synchronizedMap(clientMap);
		//대화방 맵
		//Collections.synchronizedMap(clintChatRoom);
		Collections.synchronizedList(chatCMD);
	}

	public void init(){
		try {
			serverSocket = new ServerSocket(9999); 	
			System.out.println("서버가 시작되었습니다.");
			
			while(true) {
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress()+" : "+ socket.getPort());
					
				Thread mst = new MultiServerT(socket);
				mst.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				serverSocket.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//접속자 리스트 보내기
	public void list(PrintWriter out) {
		//출력스트림을 순차적으로 얻어와서 해당 메시지를 출력한다.
		Iterator<String> it = clientMap.keySet().iterator();
		String msg = "사용자 리스트 [";
		while(it.hasNext()) {
			msg+=(String)it.next()+",";
		}
		msg =msg.substring(0,msg.length()-1)+"]";
		try {
			out.println(URLEncoder.encode(msg,"UTF-8"));	
		} catch (Exception e) {

		}
	}
	// 방 사용자 리스트 보내기
	public void roomUserList(Integer n_roomN,PrintWriter out) {
		//출력스트림을 순차적으로 얻어와서 해당 메시지를 출력한다.
		Iterator<String> it = clintChatRoom.get(n_roomN).member.keySet().iterator();
		String msg = "사용자 리스트 [";
		while(it.hasNext()) {
			msg+=(String)it.next()+",";
		}
		msg =msg.substring(0,msg.length()-1)+"]";
		try {
			out.println(URLEncoder.encode(msg,"UTF-8"));	
		} catch (Exception e) {

		}
	}
	
	//접속된 모든 클라이언트들에게 메시지를 전달(공지사항).
	public void sendAllMsg(String user, String msg) {
		Iterator<String> it = clientMap.keySet().iterator();
		while(it.hasNext()) {
			try {
				PrintWriter it_out = (PrintWriter) clientMap.get(it.next());
				if(user.equals(""))
					it_out.println(URLEncoder.encode(msg,"UTF-8"));
				else 
					it_out.println("["+URLEncoder.encode(user,"UTF-8")+"]"+
							URLEncoder.encode(msg,"UTF-8"));
			} catch (Exception e) {
				System.out.println("예외: "+e);
			}
		}
	}
	
	//접속된 작성자 이외의 모든  방내의 클라이언트들에게 메시지를 전달.
	public void sendAllInMsgChatRoom(String user, String msg, chatRoom userRoom) {
		
		chatRoom cR= userRoom;
		Iterator it = userRoom.member.keySet().iterator();
		String key="";
		while(it.hasNext()) {
			try {
				key=(String)it.next();
				if(!user.equals(key)) {
					PrintWriter it_out = (PrintWriter)userRoom.member.get(key);
			 
					it_out.println("["+URLEncoder.encode(user,"UTF-8")+"]"+
							URLEncoder.encode(msg,"UTF-8"));
				}
			} catch (Exception e) {
				System.out.println("예외: "+e);
			}
		}
	}
	

	//지정자에게 귓속말
	public void sendWhisperMsg(String user, String odUser,String msg) {
		clientMap.get(user);
		try {
			PrintWriter outI = (PrintWriter) clientMap.get(user);
			PrintWriter outY = (PrintWriter) clientMap.get(odUser);
			if(user.equals(""))
				outI.println(URLEncoder.encode(msg,"UTF-8"));
			else 
				outI.println("["+URLEncoder.encode(odUser+"에게 귓속말","UTF-8")+"]"+
								URLEncoder.encode(msg,"UTF-8"));
				outY.println("["+URLEncoder.encode(user+"의 귓속말","UTF-8")+"]"+
								URLEncoder.encode(msg,"UTF-8"));
		} catch (Exception e) {
			System.out.println("예외: "+e);
		}
	}

///========================================================================main	
	public static void main(String[] args) {
		PJT_server ms = new PJT_server();
		ms.init();

	}
//=========================================================================
	class MultiServerT extends Thread{
		Socket socket;
		PrintWriter out =null;
		BufferedReader in = null;
		DBmanager dbm = new DBmanager();
		StringTokenizer msSt=null;
		Integer roomNum=0;// 0은 대기실 
		public MultiServerT(Socket socket) {
			this.socket = socket;
			try {
				out = new PrintWriter(this.socket.getOutputStream(),true);
				in = new BufferedReader (new InputStreamReader(this.socket.getInputStream(),"UTF-8"));
				
			} catch (Exception e) {
				System.out.println("예외: "+e);
			}
		}
		
		@Override
		public void run() {
			String name="";
			
			String UserQuery="";
			boolean flg =true;
			
			try {
				while(flg) {
					UserQuery = in.readLine();
					UserQuery = URLDecoder.decode(UserQuery,"UTF-8");
					
					flg=startMenu(UserQuery);
				}
				msSt=new StringTokenizer(UserQuery,"/");
				int idIdx = 0;
				while(msSt.hasMoreTokens()) {
					if(idIdx==2)	break;
					name = msSt.nextToken();//############## 임시로 적용
					//System.out.println("[in]1토크나이저  =>"+name);	
					idIdx++;
				}
				
				sendAllMsg("",name + "님이 입장하셨습니다.");
				
				// 로그인 시장화면 이후 ===============================================
				//현재 객제가 가지고있는 소켓을 제외하고 다른 소켓(클라이언트)들에게 접속을 알림
				
				
				clientMap.put(name, out);
				//key: 유저 id(String)  / value: outputStream
				clintChatRoom.get(0).enterChatRoom(name, out);
				//key: 방번호(Integer)  / value: chatRoom 객체 ==> 채팅방에대한 정보 일체를 포함하는 객체
				
				
				String s = "";
				while(in !=null) {
			
					s=in.readLine();
					s=URLDecoder.decode(s,"UTF-8");
					s=" "+s;
					msSt= new StringTokenizer(s,"/");
				
					//명령어 테스트 ======
					while(msSt.hasMoreTokens()) {
						chatCMD.add(msSt.nextToken());
					}

					if(chatCMD.size()>1) {
						userCmdManager(name, chatCMD.get(1));
					}else {
						System.out.println("[in else]");
						
						//sendAllMsg(name,s);
						System.out.println("[in else] roomNum: " + roomNum);
						System.out.println("[Server in else]"+s);
						
						if(!dbm.getLoginStatus(name).equals("0")) {
							roomNum = Integer.parseInt(dbm.getLoginStatus(name))-1;
						}
						sendAllInMsgChatRoom(name, s, clintChatRoom.get(roomNum));
						//sendAllOthersMsg(name,s);
					}
					
					chatCMD.clear();
				}
			
			} catch (Exception e) {
				System.out.println("예외 : "+ e);
			}finally {
				
				sendAllMsg("",name+"님이 퇴장하셨습니다.");
				
				if(roomNum!=0) {
					roomNum = Integer.parseInt(dbm.getLoginStatus(name))-1;
					if(clintChatRoom.get(roomNum).getLeader().equals(name)) {
						if(clintChatRoom.get(roomNum).exitChatRoom(name,roomNum.toString())) {
							
							Iterator<String> it= clintChatRoom.get(roomNum).member.keySet().iterator();
							while(it.hasNext()) {
								try {
									dbm.setLoginStatus(it.next(), "1");
								}catch(Exception e){}
							}
						}
						
						clintChatRoom.remove(roomNum);
					}else {
						clintChatRoom.get(roomNum).exitChatRoom(name,roomNum.toString());
					}
				}
				
				dbm.setLoginStatus(name, "0");
				clientMap.remove(name);
				System.out.println("현재 접속자 수는 "+ clientMap.size()+"명 입니다.");
				
				try {
					in.close();
					out.close();
					
					socket.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		
		public void userCmdManager(String name, String cmd) {
			StringTokenizer cm = new StringTokenizer(cmd); 
			List<String> cm_list= new ArrayList<String>();
			String msg;
		
		
			while(cm.hasMoreTokens()) {
				cm_list.add(cm.nextToken());
			}


			switch (cm_list.get(0)) {
		
			case "list":
				if(cm_list.size()>1) {
					if(roomNum!=0) {
						switch(cm_list.get(1)) {
						case "lobbey":
							roomUserList(0,out);
							break; 
						case "room":
							roomUserList(roomNum,out);
							break; 

						default :
						break;
						}
	
					}else {
						System.out.println("대화방 안에서 에서 사용하는 명령어 입니다.");
						out.println("대화방 안에서 에서 사용하는 명령어 입니다.");
						//list(out);
					}
				}else{
					System.out.println("명령어가 짧음");
					out.println("'/list' 명령어 포멧 확인 ");
					out.println("'/list lobbey' : 대기실 사용자리스트 보기");
					out.println("'/list room' : 대화방 사용자리스트 보기");
				}
				
				break;
			case "To":
				//System.out.println("귓속말 함수 짜시오");
				msg="";
				if(cm_list.size()>2) {
					for(int i =2; i<cm_list.size();i++ ) {
						msg+=cm_list.get(i);
					}
					sendWhisperMsg(name,cm_list.get(1),msg);
				}else {
					System.out.println("귓말 고정 메서드 구현 ");
				}
				break;
			case "to":
				//System.out.println("귓속말 함수 짜시오");
				msg="";
				if(cm_list.size()>2) {
					for(int i =2; i<cm_list.size();i++ ) {
						msg+=cm_list.get(i);
					}
					sendWhisperMsg(name,cm_list.get(1),msg);
				}else {
					System.out.println("귓말 고정 메서드 구현 ");
				}
				break;
				
			case "공개방만들기":
				//System.out.println("공개방 만들기 함수 짜기/ cm_list.size():"+cm_list.size());
				roomNum=dbm.getRoomNumber();
				System.out.println("현재 방번호 : "+ roomNum);
				if(cm_list.size()>2) {
					createOpenRoom(roomNum ,cm_list.get(1),cm_list.get(2));
					
					clintChatRoom.put(roomNum, new chatRoom(roomNum, name));
					clintChatRoom.get(roomNum).enterChatRoom(name, out);
					clintChatRoom.get(0).member.remove(name);
					System.out.println("대기실  remove! :"+name);
				}else {
					//
					System.out.println("작음");
					out.println(" /방만들기/0");
				}
				clintChatRoom.get(0).updateChatRommstatus();
				clintChatRoom.get(roomNum).updateChatRommstatus();
				break;
			case "비공개방만들기":
				roomNum=dbm.getRoomNumber();
				System.out.println("현재 방번호 : "+ roomNum);
				if(cm_list.size()>2) {
					createCloseRoom(roomNum,cm_list.get(1),cm_list.get(2),cm_list.get(3));
					clintChatRoom.put(roomNum, new chatRoom(roomNum,name));
					clintChatRoom.get(roomNum).enterChatRoom(name, out);
					clintChatRoom.get(0).member.remove(name);
					System.out.println("대기실  remove! :"+name);
					
				}else {
					out.println(" /방만들기/1");
				}
				clintChatRoom.get(0).updateChatRommstatus();
				clintChatRoom.get(roomNum).updateChatRommstatus();
				break;
			case "방리스트보기":
				if(cm_list.size()>=2) {
					roomList(cm_list.get(1));
				}else {
					out.println("'/방리스트보기 전체' ");
					out.println("'/방리스트보기 공개' ");
					out.println("'/방리스트보기 비공개' ");
					out.println("의 포멧으로 입력해주세요 ");
				}
				break;
			case "대화방참여":
				System.out.println("대화방 입장 함수 짜기");
				
				if(cm_list.size()==2) {
					if(cm_list.get(1)!=null) {
						enterChatRoom(cm_list.get(1),name,null);
						roomNum = Integer.parseInt(cm_list.get(1));
					}
					clintChatRoom.get(0).member.remove(name);
					System.out.println("대기실 remove! :"+name);
				}else if(cm_list.size()>2) {
					if(cm_list.get(1)!=null) {
						enterChatRoom(cm_list.get(1),name,cm_list.get(2));
						roomNum = Integer.parseInt(cm_list.get(1));
					}
					clintChatRoom.get(0).member.remove(name);
					
					System.out.println("대기실  remove! :"+name);
				}else {
					out.println("'/대화방참여 방번호'");
					out.println("'/대화방참여 방번호 방암호'");
					out.println("의 포멧을 입력해 주세요");
				}
				
				clintChatRoom.get(0).updateChatRommstatus();
				clintChatRoom.get(roomNum).updateChatRommstatus();
				break;
			case "나가기":
				System.out.println("[나가기 명령어] roomNum: "+roomNum);
				if(roomNum!=0) {
					
					clintChatRoom.get(0).member.put(name,clientMap.get(name));
					clintChatRoom.get(roomNum).member.remove(name);
					clintChatRoom.get(roomNum).updateChatRommstatus();
					
					if(clintChatRoom.get(roomNum).getLeader().equals(name)) {
						clintChatRoom.remove(roomNum);
					}
					
					clintChatRoom.get(0).updateChatRommstatus();
					System.out.println("나가기[in if]");
					roomNum=0;
				}else {
					out.println("당신은 대기실 입니다");
				}
				break;
			case "grant":
				boolean flg=false;
				System.out.println("[방장위임 명령어]: roomNum"+roomNum);				
				
				if(cm_list.size()==3) {
					Iterator<String> it = clintChatRoom.get(roomNum).member.keySet().iterator();
					while(it.hasNext()) {
						if(cm_list.get(2).equals(it.next())){
							flg=true;
							break;
						}
					}
					
					if(roomNum!=0 && clintChatRoom.get(roomNum).getLeader().equals(name)) {
						if(flg) {
							clintChatRoom.get(roomNum).setLeader(cm_list.get(2));
						}else {
							System.out.println("방 내의 유저가 아닙니다.");
							out.println("방 내의 사용자가 아닙니다. 방장위임이 불가능합니다.");
						}
					}else {
						out.println("당신은 대기실 이거나 방장이 아닙니다.");
					}
				}else {
					System.out.println("명령어 포멧을 확인");
					out.println("아래포멧 확인");
					out.println("'/grant leader 방내의유저명'");
				}
				break;
			case "delroom":
				if(roomNum!=0 && clintChatRoom.get(roomNum).getLeader().equals(name)) {
					if(clintChatRoom.get(roomNum).exitChatRoom(name,roomNum.toString())) {
							
						Iterator<String> it= clintChatRoom.get(roomNum).member.keySet().iterator();

						while(it.hasNext()) {
							try {
								dbm.setLoginStatus(it.next(), "1");
								clintChatRoom.get(0).member.put(name,clientMap.get(name));
								clintChatRoom.get(roomNum).member.remove(name);
								clintChatRoom.get(roomNum).updateChatRommstatus();
								
							}catch(Exception e){}
						}
					}

					clintChatRoom.remove(roomNum);
					clintChatRoom.get(0).updateChatRommstatus();
					roomNum=0;
				}

				break;
			
			default:
	
				break;
			}
		}
		
		// user cmd 처리 메서드 ===================================
		public void enterChatRoom(String s_RN, String id, Object rpw) {
			
			roomNum=Integer.parseInt(s_RN);
//			System.out.println("rn: "+rN.intValue());
//			System.out.println("it.hasNext(): "+ it.hasNext());
			try {
				chatRoom cr= (chatRoom)clintChatRoom.get(roomNum);
				
				if(cr.isRoomAuthority()&& cr.getRoomNumTotalMember()>cr.getN_roomEnteredMemeber()) {
					cr.enterChatRoom(id, out);
				}else {
					if(cr.getRoomPassword().equals((String)rpw) && cr.getRoomNumTotalMember()>cr.getN_roomEnteredMemeber()) {
						cr.enterChatRoom(id, out);	
					}else{
						out.println("방에 입장할 수 없습니다.");
					}
				}
			}catch(Exception e) {
				System.out.println("[in enter room ]예외"+ e);
			}

		}
		
		public void roomList(String auth) {
			
			Iterator<Integer> it= clintChatRoom.keySet().iterator();
			
			while(it.hasNext()) {
				try {
					chatRoom crm = (chatRoom) clintChatRoom.get(it.next());
				
					switch(auth) {
					case "전체":
						//방 전체보기 
						System.out.println(crm.toString());
						out.println(crm.toString());
						break;
					case "공개":

						if(crm.isRoomAuthority()) {
							System.out.println(crm.toString());
							out.println(crm.toString());
						}
						break;
					case "비공개":
						if(!crm.isRoomAuthority()) {
							System.out.println(crm.toString());
							out.println(crm.toString());
						}
						break;
					default: 
						break;
					}
				} catch (Exception e) {
					System.out.println("예외: "+e);
				}
			}
		}
		
		public boolean createOpenRoom(int rNum,String title,String num) {

			boolean login_checker=false;
			roomNum=(Integer)rNum;
			
			String roomNumber = roomNum.toString();
			dbm.createOpenRoom(roomNumber, title, num);
			
			//System.out.println(login_checker);
			return login_checker;
		}
		public boolean createCloseRoom(int rNum,String title,String num, String pwd) {

			boolean login_checker=false;
			roomNum=(Integer)rNum;
			
			String roomNumber = roomNum.toString();
			dbm.createCloseRoom(roomNumber, title, num, pwd);
			
			//System.out.println(login_checker);
			return login_checker;
		}	

	//==========================================================================================	
		
		public boolean startMenu(String userquery) {
			boolean b_result=true;
			List <String>ch = new LinkedList<>();
			StringTokenizer st= new StringTokenizer(userquery,"/");
			
			while(st.hasMoreTokens()) {
				ch.add(st.nextToken());
			}
			
			switch(ch.get(0)) {
				case "1":
					//로그인 확인
					if(login_checkOut(ch.get(1), ch.get(2))) {
						out.println("1");
						b_result=false;
					}else {
						out.println("0");
						b_result=true;
					}
						
					break;
				case "2":
					//회원 가입
					b_result=signUp(ch.get(1),ch.get(2)); 
					if(b_result)
						out.println("1");
					else {
						out.println("0");
					}
					break;
				case "3":
					//암호 찾기
					String pw=findPwd(ch.get(1));
					if(pw.equals("")) {
						System.out.println("알수없는 에러!");
			
					}else {
						out.println(pw);
					}
					break;
				case "4":
					//회원 탈퇴
					 if(withDrawal(ch.get(1),ch.get(2)))
						 out.println("1");
					 else 
						 out.println("0");
					break;
				default:	
					System.out.println("명령어가 없음");
				break;
			
			}
			
			return b_result;
			
		}
		

		
// 시작 메뉴 관련 메서드=========================================== 	
		public boolean withDrawal(String id, String password) {

			boolean login_checker=false;
		//	System.out.println("[in loginChecker]");
			
			System.out.println("id= "+id+"/ pw= "+password);
			try {
				if(dbm.idConfirm(id) && dbm.passwordConfirm(id, password)) {
					dbm.withDrawal(id, password);
					login_checker=true;
					System.out.println("탈퇴성공");
				}else {
					System.out.println("회원정보가 일치하지 않습니다.");
				}
			} catch (Exception e) {
				System.out.println("알수 없는 에러가 발생했습니다.");
			}
			
			//System.out.println(login_checker);
			return login_checker;
		}
		
		
		public String findPwd(String id) {
			String pwd="";
			
			//System.out.println("[findPwd]");
			try {
				if(dbm.idConfirm(id)) {	//아이디가 존재하지 않을 경우, 아이디 생성 가능 
					 pwd = dbm.passwordFind(id);
					// System.out.println("[findPwd]"+pwd);
				}else {
					out.println("ID가 존재하지 않습니다.");
				}
			} catch (Exception e) {
				System.out.println("알수 없는 에러가 발생했습니다.");
			}
			//System.out.println("[findPwd]"+pwd);
			return pwd;
		}
		public boolean signUp(String id, String password) {

			boolean login_checker=false;
			int ckr=0;
			System.out.println("[pjt_in signChecker]");
			try {
				if(!dbm.idConfirm(id)) {	//아이디가 존재하지 않을 경우, 아이디 생성 가능 
					System.out.println("아이디 안중복");
					ckr=dbm.signUp(id, password);
				}else {
					out.println("ID  중복! 다른 ID로 생성 해주세요");
				}
			} catch (Exception e) {
				System.out.println("알수 없는 에러가 발생했습니다.");
			}
			//login_checker=dbm.getLoginStatus(id);
			//System.out.println(login_checker);
			if(ckr!=0)
				login_checker =false;
			else 
				login_checker = true;
			
			return login_checker;
		}
		public boolean login_checkOut(String id, String password) {

			boolean login_checker=false;
		//	System.out.println("[in loginChecker]");
			try {
				if(dbm.idConfirm(id) && dbm.passwordConfirm(id, password))
					dbm.setLoginStatus(id, "1");
				else {
					dbm.setLoginStatus(id, "0");
				}
				dbm.blackListCk(id);	//블랙리스트 여부 확인
				if(dbm.getLoginStatus(id).equals("0")) {
					login_checker=false;
				}else {
					login_checker=true;
				}
			} catch (Exception e) {
				System.out.println("알수 없는 에러가 발생했습니다.");
			}

			//System.out.println(login_checker);
			return login_checker;
		}
	}
}
