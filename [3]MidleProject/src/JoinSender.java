import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JoinSender extends Thread {
	
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



	public JoinSender(Socket socket, String id, String password,
						 String name,String email,String phoneNumber) {

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
		}
		catch (Exception e) {
			System.out.println("데이터 베이스 입력오류!.");
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
