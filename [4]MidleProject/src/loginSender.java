import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class loginSender extends Thread{
	//데이터 베이스 연결
	static {
		try {
			Class.forName("orcle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			// cnfe.printStackTrace();
		}
	}
	Connection con = null;
	
	Socket socket;
	PrintWriter out = null;
	int choice;
	String id;
	String password;
	Scanner s = new Scanner(System.in);
	
	////////// 서버 로그인	
	public loginSender() {
		connectdatabase();
		System.out.println("ID를 입력해 주세요");
		id = s.nextLine();
		System.out.println("비밀번호를 입력해 주세요");
		password = s.nextLine();

		String sql = "select * from pmember where pid=? and passwd=?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString(6).equals("1")) {
					System.out.println("해당 아이디는 블랙리스트입니다.");
					System.out.println("이용이 불가능 합니다 관리자에게 문의해주세요");
					try {
						String ServerIP = "localhost";
						//String ServerIP = args[0];
						Socket socket = new Socket(ServerIP, 9999);
						out = new PrintWriter(socket.getOutputStream(), true);
						
						out.println(id+"블랙리스트 사용자 서버 접근");						
							
					} catch (Exception e) {
						System.out.println("예외[MultiClient class:]" + e);
					}
					
				}
				if (rs.getString(1).equals(id) && rs.getString(2).equals(password) && rs.getString(6).equals("0")) {

					try {
						String ServerIP = "localhost";
						//String ServerIP = args[0];
						Socket socket = new Socket(ServerIP, 9999);
						System.out.println("서버와 연결이 되었습니다.....");
						out = new PrintWriter(socket.getOutputStream(), true);
						
						out.println(id);
						out.println(password);
							
					} catch (Exception e) {
						System.out.println("예외[MultiClient class:]" + e);
					}
					System.out.println(id + "님 접속완료");
					System.out.println("환영합니다.");
					System.out.println("name : " + rs.getString(3));
					System.out.println("email : " + rs.getString(4));
					System.out.println("phonenum : " + rs.getString(5));
				} else {
					System.out.println("아이디 비밀번호가 틀립니다.");
				}
			} else {
				System.out.println("아이디와 비밀번호를 입력해 주세요.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("알수 없는 에러가 발생했습니다.");
		}
		
		PrintWriter out = null;

		
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
