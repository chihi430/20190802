import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Set;

public class Sender5 extends Thread {
	Socket socket;
	PrintWriter out = null;
	int choice;
	String id;
	String password;
	Scanner s = new Scanner(System.in);

	static {
		try {
			Class.forName("orcle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			// cnfe.printStackTrace();
		}
	}
	Connection con = null;

	public Sender5(Socket socket, String id, String password) {
		// 디비 선택값 입력을 하는게 낫겟다

		connectdatabase();
		this.socket = socket;

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
				}
				if (rs.getString(1).equals(id) && rs.getString(2).equals(password) && rs.getString(6).equals("0")) {
					
					// 서버접속
					try {
						out = new PrintWriter(this.socket.getOutputStream(), true);
						this.id = id;
					} catch (Exception e) {
						System.out.println("서버 로그인 접속오류 :" + e);
					}
					System.out.println("환영합니다! "+id + "님 접속완료");
					System.out.println("name : " + rs.getString(3));
					System.out.println("email : " + rs.getString(4));
					System.out.println("phonenum : " + rs.getString(5));
					} 
				else {
					System.out.println("아이디 비밀번호가 틀립니다.");
				}
				
			} else {
				System.out.println("아이디와 비밀번호를 입력해 주세요.");
			}
			
			pstmt.close();
			rs.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("알수 없는 에러가 발생했습니다.");
		}

	}

	@Override
	public void run() throws NullPointerException {

		try {
			out.println(id);

			while (out != null) {
				try {
					String s2 = s.nextLine();
					if (s2.equals("q") || s2.equals("Q")) {
						out.println(s2);
						break;
					} else {
						out.println(id + "=>" + s2);
					}
				} catch (Exception e) {
					System.out.println("예외S1" + e);
				}
			}
			out.close();
			socket.close();

		} catch (Exception e) {
			System.out.println("예외S2: " + e);
		}
		super.run();
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
