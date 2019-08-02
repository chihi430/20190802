package ch03;

import java.util.Scanner;

public class Quiz_02_04 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("두개의 정수를 입력하세요 :");
        int num1 = sc.nextInt();
        int num2 = sc.nextInt();

        int big = (num1 >= num2) ? num1 : num2;

        System.out.println("두개의 정수중에 " + num1 + " " + num2 + " 큰수는 " + big + "입니다." );

        sc.close();
    }
}
