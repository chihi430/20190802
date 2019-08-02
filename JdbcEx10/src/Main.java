

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		
		try {
			ConnectionPool cp = ConnectionPool.getInstance
					("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger", 5, 5);


		} catch (Exception sqle) {
			sqle.printStackTrace();
		}
		for(int i =0; i<100l; i++) {
			TestThread test = new TestThread(i);
			test.start();
			Thread.sleep(50);
					
		}
		
	}
}
