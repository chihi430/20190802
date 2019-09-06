
import java.io.*;
import java.net.*;
import java.util.Scanner;


public class MultiClient6 {

	public static void main(String[] args) throws UnknownHostException, 
												  IOException 
	{
		

		try {
			String ServerIP = "localhost";
			//String ServerIP = args[0];
			//if(args.length>0) // ip주소 찾아가는거
			//	ServerIP = args[0];
			Socket socket = new Socket(ServerIP, 9999);
			System.out.println("서버와 연결이 되었습니다.....");
			
			//서버에서 보내는 메시지를 사용자의 콘솔에 출력하는 쓰레드
			Thread receiver = new Reciver6(socket);
			receiver.start();
			
			
			//사용자로부터 얻은 문자열을 서버로 전송해주는 역할을 하는 쓰레드
//			Thread sender = new Sender5(socket, s_name);
//			sender.start();
				
			new ChatWin(socket);

		} catch (Exception e) {
			System.out.println("예외[MultiClient class:]" + e);
		}
	}
}
