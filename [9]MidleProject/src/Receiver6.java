import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

// 서버로 메시지를 전송하는 클래스
public class Receiver6 extends Thread{

   Socket socket;
   BufferedReader in = null;
   PrintWriter out = null;
   
   // Socket을 매개변수로 받는 생성자
   public Receiver6(Socket socket) {
      this.socket = socket;
      
      try {
         in = new BufferedReader(new InputStreamReader( 
                         this.socket.getInputStream(), StandardCharsets.UTF_8));
         out = new PrintWriter(socket.getOutputStream(),true);
      } catch(Exception e) {
         System.out.println("예외:"+e);
      }
   }
   
   // run()메소드 재정의
   @Override
   public void run() {
      while (in != null) {
    	  String s="";
         try {
        	 s= in.readLine();
        	 String st = URLDecoder.decode(s,"UTF-8");
        	 if(st.equals("/exit")) {
        		 out.println("/exit");
        	 }
        	 else {
        		 System.out.println(" " + URLDecoder.decode(s, "UTF-8"));	 
        	 }
            
         } catch (java.net.SocketException ne) {
            break;
         } catch (Exception e) {
            System.out.println("예외:"+e);
            break;
         }
      }
   }
}