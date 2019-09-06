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

	Connection con = null;
	String id;
	String name;
	String password;
	String check_login = "select * from pmember where pid=? and passwd=?";
	String insert_member = "insert into pmember values(?,?,?,?,?,'0')";

	public DB_server() {

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
		connectdatabase();
		PreparedStatement pstmt1 = null;
		ResultSet rs;
		// 중복체크
		String sql = "select * from pmember where pid = ?";
		try {
			pstmt1 = con.prepareStatement(sql);
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setString(1, id);
			rs = pstmt1.executeQuery();

			// 중복 쿼리값 가져와서 비교
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
	
	
	public void joinDB(String id ,String password,String name,String email,String phoneNumber) 
	{
		
		connectdatabase();
		String sql = insert_member;
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt = con.prepareStatement(sql);
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

	public boolean loginDB(String name , String password) {
		connectdatabase();
		String sql = check_login;

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				if (rs.getString(6).equals("1")) {
					System.out.println("해당 아이디는 블랙리스트입니다.");
					System.out.println("이용이 불가능 합니다 관리자에게 문의해주세요");
				}
				if (rs.getString(1).equals(name) && rs.getString(2).equals(password) && rs.getString(6).equals("0")) {
					System.out.println(name + "님 접속완료");
					System.out.println("디비 커넥트완료");
					return true;
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
		return false;
	}
	
	
	
}
