
import java.io.*;
import java.net.*;
import java.util.Scanner;

import javax.xml.ws.handler.MessageContext.Scope;


public class MultiClient5 {

	public static void main(String[] args) throws UnknownHostException, 
												  IOException 
	{
		PrintWriter out = null;

		try {
			String ServerIP = "localhost";
			//String ServerIP = args[0];
			Socket socket = new Socket(ServerIP, 9999);
			System.out.println("서버와 연결이 되었습니다.....");
			
			//서버에서 보내는 메시지를 사용자의 콘솔에 출력하는 쓰레드
			Thread receiver = new Reciver5(socket);
			receiver.start();
				 		
			//사용자로부터 얻은 문자열을 서버로 전송해주는 역할을 하는 쓰레드
			Thread sender = new Sender(socket);
			sender.start();

		} catch (Exception e) {
			System.out.println("예외[MultiClient class:]" + e);
		}
	}
}
