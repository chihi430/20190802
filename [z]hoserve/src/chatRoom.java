import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

// 채팅방 정보를 갖고있는 클래스
public class chatRoom {
	// DB select information
	private Integer roomNumber;
	private String roomTitle="";
	private Integer roomNumTotalMember;
	private boolean roomAuthority=true;		// ture: 공개방/ false: 비공개방
	private String roomPassword=null;
	
	// 
	Map <String, PrintWriter> member = new HashMap<>();
	private Integer n_roomEnteredMemeber=0;
	private String leader;
	String [] roomInfo;
	DBmanager chatRoomDBm;
	
	public chatRoom(Integer roomNumber, String leader) {
		super();
		chatRoomDBm = new DBmanager();
		
		this.roomNumber = roomNumber;
		this.leader=leader;
		//DB에서 방 번호를 기준으로 방정보를 가져옴. 
		roomInfo=chatRoomDBm.getChatRoomInfo(this.roomNumber);
	
		roomTitle=roomInfo[1];
		roomNumTotalMember=Integer.parseInt(roomInfo[2]);
		if(roomInfo[3].equals("0")) {
			roomAuthority=true;		// ture: 공개방/ false: 비공개방
		}else {
			roomAuthority=false;
		}
		roomPassword=roomInfo[4];
		//System.out.println(this.roomNumber+"/"+roomTitle+"/" +roomNumTotalMember+"/" +roomAuthority+"/" +roomPassword);
	}
	//== 방 입장 & 퇴장
	public boolean enterChatRoom(String user, PrintWriter userOut) {
		Integer rn=this.roomNumber+1;
		if(this.roomNumber!=0) {
			if(this.getRoomNumTotalMember()>this.n_roomEnteredMemeber) {
				member.put(user,userOut);
				chatRoomDBm.setLoginStatus(user, rn.toString());
				//DB에 로그인 스테이터스 번호 'roomNumber+1' 로 수정
				
				updateChatRommstatus();
				return true;
			}else {
				//DB에 로그인 스테이터스 번호 '1' 로 수정
				chatRoomDBm.setLoginStatus(user, "1");
				updateChatRommstatus();
				return false;
			}
		}else {
			member.put(user,userOut);
			chatRoomDBm.setLoginStatus(user, "1");
			updateChatRommstatus();
		}
		return true;
	}
	
	public Integer getN_roomEnteredMemeber() {
		return n_roomEnteredMemeber;
	}
	public boolean exitChatRoom(String user,String rm) {
		boolean isFire=false;
		member.remove(user);
		if(this.leader.equals(user)) {
			chatRoomDBm.deleteRoom(rm);
			isFire=true;
		}
		updateChatRommstatus();
		//DB에 로그인 스테이터스 번호 '1'로 수정
		chatRoomDBm.setLoginStatus(user, "1");
		return isFire;
	}
	
	public void updateChatRommstatus() {
		n_roomEnteredMemeber= member.size();
		//DB에 로그인 스테이터스 번호 '1'로 수정
	}
	//==

	@Override
	public String toString() {
		return "chatRoom [room NO=" + roomNumber + ", 방제목: "+ roomTitle +", 방장: " +this.leader+ ", ("
				+n_roomEnteredMemeber+"/"+ roomNumTotalMember +")"+ ",공개여부:" + roomAuthority + "]";
	}
	
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public String getLeader() {
		return leader;
	}
	public String getRoomTitle() {
		return roomTitle;
	}

	public void setRoomTitle(String roomTitle) {
		this.roomTitle = roomTitle;
	}

	public Integer getRoomNumTotalMember() {
		return roomNumTotalMember;
	}

	public void setRoomNumTotalMember(Integer roomNumTotalMember) {
		this.roomNumTotalMember = roomNumTotalMember;
	}

	public boolean isRoomAuthority() {
		return roomAuthority;
	}

	public void setRoomAuthority(boolean roomAuthority) {
		this.roomAuthority = roomAuthority;
	}

	public String getRoomPassword() {
		return roomPassword;
	}

	public void setRoomPassword(String roomPassword) {
		this.roomPassword = roomPassword;
	}
	
	
	
	
	
	
	
	

}
