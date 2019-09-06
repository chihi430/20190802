import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DB_Server {

	static {
		try {
			Class.forName("orcle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			// cnfe.printStackTrace();
		}
	}
	String inserdb="";
	
	Socket socket;
	PrintWriter out = null;
	String s_id;
	String s_password;
	Connection con = null;
	Scanner s = new Scanner(System.in);

	public DB_Server() {
		
	}

	public void JoinSender(Socket socket, String id, String password, 
						String name, String email, String phoneNumber) {

		connectdatabase();
		this.socket = socket;

		String sql1 = "insert into pmember values(?,?,?,?,?,'0')";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql1);
			pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.setString(5, phoneNumber);
			int updateCount = pstmt.executeUpdate();
			System.out.println("데이터 베이스 입력되었습니다.");
		} catch (Exception e) {
			System.out.println("데이터 베이스 입력오류!.");
		}
	}

	public void joinMemberserver() {
		connectdatabase();
		
		String s_password = null;
		String s_passcheck = null;
		String s_id = null;
		String s_name = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs;
		
		System.out.println("회원가입 시작");
		while (true) {
			System.out.print("id  : ");
			s_id = s.nextLine();

			// 중복체크
			String sql = "select * from pmember where pid = ?";
			try {
				pstmt1 = con.prepareStatement(sql);
				pstmt1 = con.prepareStatement(sql);
				pstmt1.setString(1, s_id);
				rs = pstmt1.executeQuery();

				// 중복 쿼리값 가져와서 비교
				if (rs.next()) {
					if (rs.getString(1).equals(s_id)) {
						System.out.println("중복된 이름입니다.");
						continue;
					}
				}
				
				pstmt1.close();
				rs.close();
				con.close();
			}

			catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		// 비밀번호 확인
		while (true) {
			System.out.println("비밀 번호 : ");
			s_password = s.nextLine();
			System.out.println("비밀 번호 확인 : ");
			s_passcheck = s.nextLine();
			if (s_password.equals(s_passcheck)) {
				break;
			} else {
				System.out.println("비밀번호가 맞지 않습니다. 다시 입력하세요");
			}
		}

		System.out.print("이름  : ");
		s_name = s.nextLine();
		System.out.println("이메일 : ");
		String s_email = s.nextLine();
		System.out.println("전화 번호 : ");
		String s_phoneNumber = s.nextLine();
		
		JoinSender(socket, s_id, s_password, s_name, s_email, s_phoneNumber);		
		PrintWriter out = null;
		// 서버연결
		try {
			String ServerIP = "localhost";
			//String ServerIP = args[0];
			socket = new Socket(ServerIP, 9999);
			System.out.println("디비에 "+s_id+"님이 회원 가입 되었습니다.");
			
			//서버에서 보내는 메시지를 사용자의 콘솔에 출력하는 쓰레드
//			Thread receiver = new Reciver5(socket);
//			receiver.start();
						
//			//사용자로부터 얻은 문자열을 서버로 전송해주는 역할을 하는 쓰레드							
		} catch (Exception e) {
			System.out.println("예외[MultiClient class:]" + e);
		}
	}
	

	
	// 디비연결
	public void connectdatabase() {
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
