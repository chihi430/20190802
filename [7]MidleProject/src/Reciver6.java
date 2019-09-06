import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URLDecoder;

public class Reciver6 extends Thread {

	Socket socket;
	BufferedReader in = null;

	public Reciver6(Socket socket) {
		this.socket = socket;
		try {
			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream(),"UTF-8"));
		} catch (Exception e) {
			System.out.println("Reciver6 예외1:" + e);
		}
	}

	@Override
	public void run() {
		while (in != null) {
			try {
				System.out.println(">>" + URLDecoder.decode(in.readLine(),"UTF-8"));
			} catch (java.net.SocketException ne) {
				break;
			} catch (Exception e) {
				System.out.println("Reciver6 예외2 : " + e);
				break;
			}
		}
	}
}
