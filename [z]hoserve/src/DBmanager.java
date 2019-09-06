import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class DBmanager {
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");			
		}catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}
	Connection con;
	PreparedStatement pstmt=null;
	PreparedStatement pstmt_sub=null;
	//  시작메뉴 쿼리문
	String sql_id_select="select id from projectChatDB where id = ?";
	String sql_id_select_rob="select id from projectChatDB where login_info = '1'";
	String sql_count_rob="select count(id) from projectChatDB where login_info='1'"; 
	
	
	String sql_bL_select="select blacklist from projectChatDB where id = ?";
	String sql_login_select="select login_info from projectChatDB where id = ?";
	String sql_pwd_select="select password from projectChatDB where id = ?";
	String sql_update="update projectChatDB set login_info =?  where id=?";
	String sql_delete=" delete projectChatDB where id = ? ";
	String sql_insert="insert into projectChatDB (id,password) values(?,?)";
	
	// 명령어 쿼리문 
	String rSql_insert_opnR="insert into chatroomDB (roomNo,roomTitle, NofMember) " + 
			"    values(?,?,?)";
	String rSql_insert_clsR="insert into chatroomDB (roomNo,roomTitle, NofMember, roomAuth, roomPwd) " + 
			"    values(?,?,?,?,?)";
	String rSql_select="select roomNo,roomTitle, NofMember, roomAuth,roompwd from chatroomDB where roomNo= ? ";
	String rSql_select_opn="select roomNo,roomTitle, NofMember from chatroomDB where roomauth='0'";
	String rSql_select_cls="select roomNo,roomTitle, NofMember from chatroomDB where roomauth='1'";
	String rSql_select_RN="select roomNo from chatroomDB";
	String rSql_delete_RN="delete chatroomDB where roomNo =?";
	
	
	public DBmanager() {
		// DB 접속 
			try{
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"scott",
					"tiger");		
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String[] robMember() {
		String [] roMember=null;
		int idx=0;
		try {
			pstmt_sub=con.prepareStatement(sql_count_rob);
			ResultSet rs = pstmt_sub.executeQuery();
			if(rs.next()) {
				roMember = new String[rs.getInt(1)];
			}
		}catch(Exception e) {
			
		}
		try {
			pstmt=con.prepareStatement(sql_id_select_rob);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				roMember[idx] = rs.getString(1); 
				idx++;
			}
		}catch(Exception e) {
			System.out.println("알수 없는 에러가 발생했습니다.");
		}
		
		return roMember;
	}
	
	public void deleteRoom(String roomNumber) {
		try {
			pstmt=con.prepareStatement(rSql_delete_RN);
			pstmt.setString(1, roomNumber);
			
			int resultCount= pstmt.executeUpdate();
			
			System.out.println("생성 결과결과:"+ resultCount);

		}catch(Exception e) {
				System.out.println("알수 없는 에러가 발생했습니다.");
		}
		//System.out.println(b_id);
		//System.out.println("[end]idconfirm"+id);
	}
	
	
	public String[] getChatRoomInfo(Integer rn) {
		String []rmInfo=new String[5]; 
		try {
			pstmt=con.prepareStatement(rSql_select);
			pstmt.setString(1, rn.toString());
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				for(int i=0; i<5; i++)
					rmInfo[i]=rs.getString(i+1);
			}
		}catch(Exception e) {
			System.out.println("알수 없는 에러가 발생했습니다.");
		}
//		System.out.println(rn.toString());
//		for(int i =0; i<5;i++)
//			System.out.println(rmInfo[i]);
		
		return rmInfo;
	}
	
	public void createOpenRoom(String roomNumber,String title,String num) {
		try {
			pstmt=con.prepareStatement(rSql_insert_opnR);
			pstmt.setString(1, roomNumber);
			pstmt.setString(2, title);
			pstmt.setString(3, num);
			
			int resultCount= pstmt.executeUpdate();
			
			System.out.println("생성 결과결과:"+ resultCount);

		}catch(Exception e) {
				System.out.println("알수 없는 에러가 발생했습니다.");
		}
		//System.out.println(b_id);
		//System.out.println("[end]idconfirm"+id);
	}
	public void createCloseRoom(String roomNumber,String title,String num, String Rpwd) {
		try {
			pstmt=con.prepareStatement(rSql_insert_clsR);
			pstmt.setString(1, roomNumber);
			pstmt.setString(2, title);
			pstmt.setString(3, num);
			pstmt.setString(4, "1");
			pstmt.setString(5, Rpwd);
			
			int resultCount= pstmt.executeUpdate();
			
			System.out.println("생성 결과결과:"+ resultCount);

		}catch(Exception e) {
				System.out.println("알수 없는 에러가 발생했습니다.");
		}
		//System.out.println(b_id);
		//System.out.println("[end]idconfirm"+id);
	}
	
	
	public Integer getRoomNumber() {
		Integer rNo=0;
		List <Integer> rN_list =new ArrayList<>();
		try {
			pstmt=con.prepareStatement(rSql_select_RN);
			ResultSet rs =pstmt.executeQuery();
			while(rs.next()) {
				rN_list.add(Integer.parseInt(rs.getString(1)));
			}
			if(rN_list.size()<2) {
				rNo=1;
			}else if (rN_list.size()>=2) {
			Collections.sort(rN_list);
				for(int i =0; i< rN_list.size()-1;i++) {
					
					if((rN_list.get(i+1)-rN_list.get(i))>1) {
						rNo= rN_list.get(i)+1;
						return rNo;
					}
				}
				rNo=rN_list.get(rN_list.size()-1)+1;
			}
				
		}catch(Exception e) {
			System.out.println("알수 없는 에러가 발생했습니다.");
		}
		
		System.out.println("방번호 :" + rNo);
		return rNo;
	}
	
	
	
	
	
	
	
	
	
	public boolean idConfirm(String id) {	// 아이디가 DB에 존재 할 경우 true반환 
		String user_ID="";
		boolean b_id=false;
	//	System.out.println("[start]idconfirm"+id);
		try {
			pstmt=con.prepareStatement(sql_id_select);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				user_ID=rs.getString("id");
				b_id=true;
			}else {
				System.out.println("존재하는 id가 없습니다.");
				b_id=false;
			}

		}catch(Exception e) {
				System.out.println("알수 없는 에러가 발생했습니다.");
		}
		//System.out.println(b_id);
		//System.out.println("[end]idconfirm"+id);
		return b_id;
	} 
	
	
	public boolean passwordConfirm(String id, String pwd) {
		String user_Pwd="";
		boolean b_pwd=false;
		try {
			pstmt=con.prepareStatement(sql_pwd_select);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				user_Pwd=rs.getString("password");
			}else {
				b_pwd=false;
			}
			if(user_Pwd.equals(pwd)) {
				//System.out.println("pwd인증완료");
				b_pwd=true;
			}else{
				System.out.println("pw를 다시 확인 하세욧");
				b_pwd=false;
			}
			
		}catch(Exception e) {
				System.out.println("알수 없는 에러가 발생했습니다.");
		}
		return b_pwd;
	} 
	
	
	public String passwordFind(String id) {
		String user_Pwd="";
		boolean b_pwd=false;
		try {
			pstmt=con.prepareStatement(sql_pwd_select);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				user_Pwd=rs.getString("password");
			}else {
				b_pwd=false;
			}
			
		}catch(Exception e) {
				System.out.println("알수 없는 에러가 발생했습니다.");
		}
		return user_Pwd;
	} 
	
	
	
	public void withDrawal(String id, String password) {
		// 인증 기능 추가 
		try {
			pstmt=con.prepareStatement(sql_delete);
			pstmt.setString(1, id);
			
			pstmt.executeUpdate();
			System.out.println("회원탈퇴 성공 ");
			
		} catch (Exception e) {
			System.out.println("회원탈퇴 실패");
		}
	}
	
	
public int signUp(String id, String password) {
		//중복 check 기능 추가
	
		
		int updateCount=0;
		try {
			//System.out.println("[in dbm singup]");
			pstmt=con.prepareStatement(sql_insert);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			
			updateCount=pstmt.executeUpdate();
			System.out.println("환영합니다. 회원가입에 성공하였습니다. "+updateCount);
			
		} catch (Exception e) {
			System.out.println("회원가입 실패");
		}
		return updateCount;
	}
	
	public  String getLoginStatus(String id) {
		String login="";
		boolean b_login=false;
		try {
			pstmt=con.prepareStatement(sql_login_select);
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				login=rs.getString(1);
				
		}catch (Exception e) {
			e.printStackTrace();
		}
		
//		if(login.equals("0"))
//			b_login=false;
//		else 
//			b_login =true;
//		
		return login;
	}
	
	public void setLoginStatus(String id, String st) {
		try {
			pstmt=con.prepareStatement(sql_update);
			pstmt.setString(1, st);
			pstmt.setString(2, id);
			
			pstmt.executeUpdate();
//			if(st.equals("1"))
//				System.out.println("login 성공");
//			else
//				System.out.println("login 실패");
//			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void blackListCk(String id) {
		String blklist="";
		
		try {
			pstmt=con.prepareStatement(sql_bL_select);
			pstmt.setString(1, id);
			
			ResultSet rs=pstmt.executeQuery();
			if(rs.next())
				blklist=rs.getString(1);
			if(blklist.equals("1")) {
				System.out.println("black list로 login이 불가능합니다.");
				setLoginStatus(id, "0");	// 로그 아웃 상태
			}else{
				System.out.println("black list 아님!! 로그인 성공 ");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
