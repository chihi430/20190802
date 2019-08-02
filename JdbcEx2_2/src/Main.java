import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class Main {
	static {
		try {
			Class.forName("orcle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			//cnfe.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
			Statement stmt = con.createStatement();
			
			StringBuffer sb = new StringBuffer();
			sb.append("select * from employee");
			
			ResultSet rs = stmt.executeQuery(sb.toString());
			
			while(rs.next()) {
				System.out.print("eno : " +rs.getInt(1)+", ");
				System.out.println("eno : " +rs.getString("ename")+", ");
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException sqle) {
			System.out.println("Connetion Error");
			sqle.printStackTrace();
		}
	}
}
