package ch22;

import java.io.DataInputStream;
import java.io.FileInputStream;

import java.io.IOException;


public class C2_DataFilterInputStream {
	public static void main(String[] args) {

		try (DataInputStream in = new DataInputStream(new FileInputStream("data.dat"))){
			int num1 = in.readInt();
			double num2 = in.readDouble();
			
			System.out.println(num1);
			System.out.println(num2);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}


// 기본 자료형 데이터의 입력을 위한 필터 스트림
// InputStream in = new FileInputStream("data.dat");  // 입력 슽트림 생성
// DataInputStream fIn = new DataInputStream(in);  // 필터 스트림 생성 및 연결



// 기본 자료형 데이터의 출력을 위한 필터 스트림
// OutputStream out = new FileOutputStream("data.dat"); // 출력 스트림 생성
// DataOutputStream fOut = new DataOutputStream(out); // 필터 스트림 생성 및 연결