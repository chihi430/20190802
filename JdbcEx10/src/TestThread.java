import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestThread extends Thread {

	String noThread = "00";
			
	TestThread(int n ){
		if(n<0)
			noThread = "0"+n;
		else
			noThread = ""+n;
	}
	public void run() {
		ConnectionPool cp = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			cp = ConnectionPool.getInstance("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger", 5, 10);
			con = cp.getConnection();
			pstmt = con.prepareStatement("select * from department");
			rs = pstmt.executeQuery();
			System.out.println("getFreeCons : " + cp.getFreeCons());

			while (rs.next()) {
				System.out.print("deptno : " + rs.getInt(1) + ", ");
				System.out.print("dname : " + rs.getString(2) + ", ");
				System.out.println("lco : " + rs.getString(3) + ", ");
			}
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				cp.releaseConnection(con);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
