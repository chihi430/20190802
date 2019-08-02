
import java.io.*;
import java.net.*;
import java.util.Scanner;


public class MultiClient4 {

	public static void main(String[] args) throws UnknownHostException, 
												  IOException 
	{
		System.out.println("이름을 입력해 주세요");
		Scanner s = new Scanner(System.in);
		String s_name = s.nextLine();

		PrintWriter out = null;

		try {
			String ServerIp = "localhost";
			Socket socket = new Socket(ServerIp, 9999);
			System.out.println("서버와 연결이 되었습니다.....");
			
			Thread receiver = new Reciver3(socket);
			receiver.start();
				
			out = new PrintWriter(socket.getOutputStream(), true);
			

			out.println(s_name);
			
			

			while (out != null) {
				try {
					String s2 = s.nextLine();
					if (s2.equals("q") || s2.equals("Q")) {
						out.println(s2);
						break;
						
					} else {
						out.println(s_name + "=>" + s2);
					}
				} catch (Exception e) {
					System.out.println("예외" + e);
				}
			}
			out.close();
			
			socket.close();

		} catch (Exception e) {
			System.out.println("예외[MultiClient class:]" + e);
		}
	}
}
