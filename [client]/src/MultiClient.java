import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.StringTokenizer;
 
import javax.swing.plaf.basic.BasicScrollPaneUI.HSBChangeListener;
 
public class MultiClient {
   
    static boolean chatmode = false;
   
    public static void main(String[] args) throws UnknownHostException, IOException {
       
       
        try{
            String ServerIP = "localhost";
            Socket socket = new Socket(ServerIP, 9999); //소켓 객체 생성        
            System.out.println("##서버와 연결이 되었습니다......");
            //사용자로부터 얻은 문자열을 서버로 전송해주는 역할을 하는 쓰레드.
           
            /////////////* (테스트용)*///////////////
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            String s_name = "";
            Scanner s = new Scanner(System.in);  
           
            System.out.println("이름을 입력해 주세요.");
            s_name = s.nextLine();
            dos.writeUTF(s_name);
           
            String locStr = dis.readUTF();         
            StringTokenizer st = new StringTokenizer(locStr,"@");
           
            System.out.println("=== 그룹 목록 ===");
            while(st.hasMoreElements()){
                String locsize = (String) st.nextElement();            
                System.out.println(locsize);               
            }
            System.out.println("=== ========= ===");
           
            System.out.println("지역을 입력해 주세요.");
            String s_loc = s.nextLine();
            dos.writeUTF(s_loc);
           
           
            /////////////////////////////////////////////////////////////
           
            Thread sender = new Sender(socket, s_name);            
            //서버에서 보내는 메시지를 사용자의 콘솔에 출력하는 쓰레드.
            Thread receiver = new Receiver(socket);        
            System.out.println("##채팅방 ("+s_loc+") 에 입장하였습니다.");
               
            sender.start(); //스레드 시동
            receiver.start(); //스레드 시동
           
        }catch(Exception e){
            System.out.println("예외[MultiClient class]:"+e);
        }
       
    }//main()-------
}//End class MultiClient
 
 
/////////////////////////////////////////////////////////////////////
 
//서버로부터 메시지를 읽는 클래스
class Receiver extends Thread{
   
    Socket socket;
    DataInputStream in;
   
    //Socket을 매개변수로 받는 생성자.
    public Receiver(Socket socket){
        this.socket = socket;
       
        try{
            in = new DataInputStream(this.socket.getInputStream());
        }catch(Exception e){
            System.out.println("예외:"+e);
        }
    }//생성자 --------------------
   
    @Override
    public void run(){ //run()메소드 재정의
       
        while(in!=null){ //입력스트림이 null이 아니면..반복
            try{        
               
                String msg=in.readUTF();
               
                if(msg.startsWith("/Q대화신청")){                  
                    synchronized (this) { //이 블록을 동기화하고 싶은데.....               
                        MultiClient.chatmode=true; //Sender에게 1:1요청이 들어왔다는것을 알려주기 위함
                        System.out.println(msg.split(" ", 2)[1]); //메세지만 추출
                        System.out.print("선택:");                   
                    }
                }else{
                    System.out.println(msg);   
                }
               
                //서버로부터 읽어온 데이터를 콘솔에 출력
               
            }catch(SocketException e){             
                 System.out.println("예외:"+e);
                 System.out.println("##접속중인 서버와 연결이 끊어졌습니다.");
                return;
                 
            } catch(Exception e){              
                System.out.println("예외:"+e);
             
            }
        }//while----
    }//run()------
}//class Receiver -------
 
 
/////////////////////////////////////////////////////////////////////
 
//서버로 메시지를 전송하는 클래스
class Sender extends Thread {
    Socket socket;
    DataOutputStream out;
    String name;
   
    //생성자 ( 매개변수로 소켓과 사용자 이름 받습니다. )
    public Sender(Socket socket, String name){ //소켓과 사용자 이름을 받는다.
        this.socket = socket;      
        try{
            out = new DataOutputStream(this.socket.getOutputStream());
            this.name = name; //받아온 사용자이름을 전역변수에 저장, 다른 메서드인 run()에서 사용하기위함.
        }catch(Exception e){
            System.out.println("예외:"+e);
        }
    }
   
    @Override
    public void run(){ //run()메소드 재정의
       
        Scanner s = new Scanner(System.in);
        //키보드로부터 입력을 받기위한 스캐너 객체 생성
 
        while(out!=null){ //출력스트림이 null이 아니면..반복
            try { //while문 안에 try-catch문을 사용한 이유는 while문 내부에서 예외가 발생하더라도
                  //계속 반복할수있게 하기위해서이다.                  
               
                String msg = s.nextLine();
               
               
                //명령어 기능 추가. ( /접속자 , /귓속말 상대방아이디 전달할메시지 )               
                if(msg.trim().startsWith("/")){
                   
                    //클라이언트단에서 체크
                    if(msg.equals("/접속자")||msg.startsWith("/귓속말")||msg.startsWith("/지역")||msg.startsWith("/대화신청")||msg.startsWith("/대화종료")){                       
                        out.writeUTF(msg);
                    }else if(msg.equalsIgnoreCase("/exit")){
                          System.out.println("##클라이언트를 종료합니다.");
                          System.exit(0);
                        break;
                    }else{
                        System.out.println("##잘못된 명령어입니다.");
                       
                    }    
                   
                }else if(MultiClient.chatmode){ //chatmode가 true이면 서버에게 다른 메시지를 전달.
                   
                        out.writeUTF("/Q대화신청 "+msg);    
                        MultiClient.chatmode=false;
                   
                }else{//명령어가 아니면 키보드로부터 입력받은 문자열을 서버로 보낸다.
                    out.writeUTF(name+"=>"+msg);               
                }              
               
            }catch(SocketException e){             
                 System.out.println("예외:"+e);
                 System.out.println("##접속중인 서버와 연결이 끊어졌습니다.");
                return;              
           } catch (IOException e) {
                System.out.println("예외:"+e);
           }
        }//while------
     
    }//run()------
}//class Sender-------