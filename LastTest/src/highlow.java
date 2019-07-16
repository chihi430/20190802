import java.util.Random;
import java.util.Scanner;

public class highlow {

    public static int Gen_Target(){
        System.out.println("-------------목표 값 생성중-----------");

        Random rd = new Random();
        int T = (int) rd.nextInt(100) + 1;
        System.out.println("목표 값 생성 완료");
        System.out.println(T);

        return T;
    }

    public static int getNum(){
        Scanner sc = new Scanner(System.in);
        int num;

        while (true){
            System.out.println("--------------------------------");
            System.out.print("1~100사이의 정수를 입력해 주세요: ");
            num = sc.nextInt();
            if(num > 0 && num < 101){
                break;
            }

            else{
                System.out.println("잘못된 범위의 수 입니다. 다시 입력하세요");
            }

        }

        return num;
    }

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

            switch (Det(target,InNum)){

                case 1:{
                    System.out.println("목표값 보다 작습니다");
                    break;
                }
                case 2:{
                    System.out.println("정답입니다. 승리를 축하합니다");
                    System.out.println("도전 횟수 : " + count);
                    flag = false;
                    break;
                }
                case 3:{
                    System.out.println("목표값 보다 큽니다");
                    break;
                }
                default:{
                    System.out.println("Error");
                    break;
                }
            }

        }

    }

    public static void main(String[] args) {
        start();
    }
}
