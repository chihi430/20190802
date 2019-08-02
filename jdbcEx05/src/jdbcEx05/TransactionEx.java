// 자동커밋된당.
// 여기서 트랜잭션 처리를 할 수 있다.
// sql문만보면 2번문제까지가 끝이다.
// 트랜잭션 이용은 커밋과 롤백을 하는것이 다이다.

package jdbcEx05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionEx {
	static {
		try {
			Class.forName("orcle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			// cnfe.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean success = false;

		try {
			System.out.println("디비연결됨");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");

			con.setAutoCommit(false);

			String sql = "insert into test3 values('홍길동', '1111')";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			System.out.println("11111");

			sql = "insert into test3 values('전우치', '2222')";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			System.out.println("22222");

			sql = "insert into test3 values('손오공', '3333')"; // 괄호 하나를 없애서 에러를 유도
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			System.out.println("33333");

			success = true;

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				if (success) {
					System.out.println("44444");
					con.commit();
				} else {
					System.out.println("55555");
					con.rollback();
				}

				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException sqle) {

			}
		}
	}
}
