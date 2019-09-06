import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_server {
	static {
		try {
			Class.forName("orcle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			// cnfe.printStackTrace();
		}
	}
	Socket socket;
	Connection con = null;

	
	String check_login = "select * from pmember where pid=? and passwd=?";
	String insert_member = "insert into pmember values(?,?,?,'0','0')";
	String chat_make = "insert into chatroom values(TMP_SEQ.nextval,?,?,'0','10','0')"; // 채팅방 만드는것
	String chat_list = "select * from chatroom"; // 채팅방 리스트 뽑기
	String chating_in = "select";
	
	
	public DB_server() {
		connectdatabase();
	}
	// 디비 연결
	public void connectdatabase() {
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkID(String id) {
		PreparedStatement pstmt1 = null;
		ResultSet rs;
		// 중복체크
		String sql = "select * from pmember where pid = ?";
		try {
			pstmt1 = con.prepareStatement(sql);
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setString(1, id);
			rs = pstmt1.executeQuery();

			// 중복된 쿼리값 가져와서 비교
			if (rs.next()) {
				if (rs.getString(1).equals(id)) {										
					return true;
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public void joinDB(String id ,String password,String name) 
	{
		String sql = insert_member;
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			pstmt.setString(3, name);
			pstmt.executeUpdate();
			System.out.println("데이터 베이스 입력되었습니다.");
		} catch (Exception e) {
			System.out.println("데이터 베이스 입력오류!.");
		}
	}

	public boolean loginDB(String name , String password) {
		String sql = check_login;

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				if (rs.getString(4).equals("1")) {
					System.out.println("해당 아이디는 블랙리스트입니다.");
					System.out.println("이용이 불가능 합니다 관리자에게 문의해주세요");
					return false;
				}
				if (rs.getString(1).equals(name) && rs.getString(2).equals(password) && rs.getString(4).equals("0")) {
					System.out.println(name + "님 접속완료");
					System.out.println("디비 커넥트완료");
					return true;
				} else {
					System.out.println("아이디 비밀번호가 틀립니다.");
					return false;
				}
			} else {
				System.out.println("아이디와 비밀번호를 입력해 주세요.");
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("알수 없는 에러가 발생했습니다.");
		}
		return false;
	}
	
	public boolean chatroomDBmake(String chatname , String id) {
		String sql = chat_make;
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, chatname);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
			System.out.println("채팅방 등록! 데이터 베이스 입력되었습니다.");
			return true;
		} catch (Exception e) {
			System.out.println("채팅방 등록! 데이터 베이스 입력오류!.");
			return false;
		}
	}
	public void chatgoDB() {
		String sql ="";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt = con.prepareStatement(sql);
			
			pstmt.executeUpdate();

			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void chatlist(Socket socket) {
		String sql = chat_list;
		this.socket = socket;
		PrintWriter out = null;

		try {
			out = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream(), StandardCharsets.UTF_8),
					true);
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();			
			while (rs.next()) {
				out.println("  ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
				
				out.print("| 방번호 : " + rs.getString(1));
				out.println("\t\t\t  |");
				
				out.print("| 방이름 : " + rs.getString(2)+"");
				out.println("\t\t  |");
				
				out.print("| 방장 : " + rs.getString(3)+"");
				out.println("\t\t  |");
				
				out.print("| 현재인원 : " + rs.getString(4)+"/"+rs.getString(5)+"");
				out.println("\t\t\t  |");
				out.println("  ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
				out.println();
			}
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
