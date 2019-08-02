import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class PreparedStatementEx {
	static {
		try {
			Class.forName("orcle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			//cnfe.printStackTrace();
		}
	}
	public static void main(String[] args) {
		
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		ResultSet rs = null;
		
		try {
			System.out.println("디비연결됨");
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
			
			String sql = "create table test2(id varchar2(10),   password varchar2(10))";
			
			pstmt1 = con.prepareStatement(sql);
			int updateCount = pstmt1.executeUpdate();		
			System.out.println("createCount : " + updateCount);
			//---------------------------------------------------------
			// ? 에만 파라미터로 변수 넣어주면 됨
			// 생성때부터 문법검사가 되어있다.
			sql = "insert into test2 values(?,?)";
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setString(1, "홍길동");
			pstmt2.setString(2, "1111");
			updateCount = pstmt2.executeUpdate();
			System.out.println("insertCount : " + updateCount);
			//---------------------------------------------------------
			
			sql = "select * from test2";
			pstmt3 = con.prepareStatement(sql);
			rs = pstmt3.executeQuery();
			while(rs.next()) {
				System.out.print("id : " + rs.getString(1));
				System.out.println(", password : " + rs.getString(2));
			}
			
			sql = "drop table test2";
			pstmt4 = con.prepareStatement(sql);
			updateCount = pstmt2.executeUpdate();
			System.out.println("dropCount : " + updateCount);
		}catch (SQLException sqle) {
			sqle.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt1 != null) pstmt1.close();
				if(pstmt2 != null) pstmt2.close();
				if(pstmt3 != null) pstmt3.close();
				if(pstmt4 != null) pstmt4.close();
				if(con != null) con.close();
				
			} catch(SQLException sqle) {}
		}
	}
}
//여러번 반복되는 문법검사를 한번만 하게 하기위해