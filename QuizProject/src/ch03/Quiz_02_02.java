package ch03;

import java.util.Scanner;

public class Quiz_02_02 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("������ �Է��ϼ���: ");
        int num1 = sc.nextInt();

        System.out.println(num1 + "�Է��� ���� ����: " + (num1 * num1));

        sc.close();
    }
}
