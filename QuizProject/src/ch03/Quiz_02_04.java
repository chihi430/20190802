package ch03;

import java.util.Scanner;

public class Quiz_02_04 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("�ΰ��� ������ �Է��ϼ��� :");
        int num1 = sc.nextInt();
        int num2 = sc.nextInt();

        int big = (num1 >= num2) ? num1 : num2;

        System.out.println("�ΰ��� �����߿� " + num1 + " " + num2 + " ū���� " + big + "�Դϴ�." );

        sc.close();
    }
}
