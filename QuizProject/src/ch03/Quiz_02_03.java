package ch03;

import java.util.Scanner;

public class Quiz_02_03 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("�ΰ��� ������ �Է��ϼ��� :");
        int num1 = sc.nextInt();
        int num2 = sc.nextInt();

        System.out.println(num1 + " / " + num2 + " = " + (num1 / num2) + " �Դϴ�.");
        System.out.println(num1 + " % " + num2 + " = " + (num1 % num2) + " �Գ���.");

        sc.close();
    }
}
