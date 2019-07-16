import java.util.Random;
import java.util.Scanner;

public class RPS_Game {

    public static String Gen_Computer(){

        Random rd = new Random();
        String computer =" ";
        int T = (int) rd.nextInt(3);

        switch (T){
            case 0 :{
                computer = "가위";
                break;
            }

            case 1 :{
                computer = "바위";
                break;
            }
            case 2 :{
                computer = "보";
                break;
            }

            default:{
                break;
            }

        }

        return computer;
    }

    public static String getNum(){
        Scanner sc = new Scanner(System.in);
        String player = "";

        while (true){
            System.out.println("--------------------------------");
            System.out.println("------1.가위 2.바위. 3.보-------");
            System.out.println("--------------------------------");

            System.out.println("1 , 2 , 3 혹은 가위 , 바위 , 보를 입력해주세요");
            player = sc.nextLine();
            break;


        }

        return player;
    }                       //7월 5일 금요일 - 여기 작업중 7월8일 월요일에 여기 마무리 지을것

    public static int Det(int T, int num){
        if(T >  num){
            return 1;
        }
        else if(T == num){
            return 2;
        }
        else{
            return 3;
        }

    }

    public static void start(){
        System.out.println("High - Low 게임에 오신것을 환영합니다.");
        System.out.println("기본 환경 설정중");
        int count = 0;
        int target;
        int InNum;
        boolean flag = true;

        target = Gen_Target();

        while (flag){
            InNum = getNum();
            System.out.println("당신이 입력한 수는 " + InNum  + "입니다.");
            count++;

        }

    }

    public static void main(String[] args) {
        start();
    }
}
