package ch03;

import java.util.Scanner;

public class Quiz_02_02 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("정수를 입력하세요: ");
        int num1 = sc.nextInt();

        System.out.println(num1 + "입력한 값의 곲은: " + (num1 * num1));

        sc.close();
    }
}
