
import java.io.*;
import java.net.*;

public class MultiServer5 {

	ServerSocket serverSocket = null;
	Socket socket = null;

	public MultiServer5() {

	}

	public void init() {
		try {
			serverSocket = new ServerSocket(9999);
			System.out.println("서버가 시작되었습니다.");

			socket = serverSocket.accept();
			System.out.println(socket.getInetAddress() + ":" + socket.getPort());

			while (true) {
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress() + ":" + socket.getPort());
				Thread msr = new MultiServerT(socket);
				msr.start();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				serverSocket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		MultiServer5 ms = new MultiServer5();
		ms.init();
	}

}
//////////////////////////////////////////////////////////////////
// 내부 클래스
// 클라이언트로부터 읽어온 메시지를 다른 클라이언트(socket)에 보내는 역할을 하는 메서드

class MultiServerT extends Thread {
	Socket socket;
	PrintWriter out = null;
	BufferedReader in = null;

	public MultiServerT(Socket socket) {
		this.socket = socket;
		try {
			out = new PrintWriter(this.socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		} catch (Exception e) {
			System.out.println("예외 : " + e);
		}
	}

	@Override
	public void run() {
		String s = "";
		try {
			while (in != null) {
				s = in.readLine();
				if (s == null)
					break;
				if (s.equals("q") || s.equals("Q"))
					break;

				System.out.println(s);
				sendAllMsg(s, out);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				in.close();
				out.close();
				socket.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	// 접속된 모든 클라이언트들에게 메시지를 전달.
	public void sendAllMsg(String msg, PrintWriter out) {
		try {
			out.println(msg);
		} catch (Exception e) {
			System.out.println("예외" + e);
		}
	}
}