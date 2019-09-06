import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URLDecoder;

public class Reciver5 extends Thread{
	
	Socket socket;
	BufferedReader in = null;
	
	public Reciver5(Socket socket) {
		this.socket = socket;
		try {
			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream(),"UTF-8"));
		} catch (Exception e) {
			System.out.println("예외1:"+e);
		}
	}
	@Override
	public void run() {
		while(in!=null) {
			try {
				String s = in.readLine();
				if(s.equals("q") || s.equals("Q")) {
					break;
				}else if(s.equals("TRUE")){
					System.out.println("중복입니다.");
				}else {
					System.out.println("Thread Receive : " + URLDecoder.decode(s,"UTF-8"));
				}
				System.out.println(">>" + URLDecoder.decode(in.readLine(),"UTF-8"));
			} catch (java.net.SocketException ne) {
				break;
			}catch(Exception e) {
				System.out.println("예외2 : " + e);				
			}
		}
		
		try {
			in.close();
		} catch (Exception e) {
			System.out.println("예외 3 : " + e);
		}
	}
}
