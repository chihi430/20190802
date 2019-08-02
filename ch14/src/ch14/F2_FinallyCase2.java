package ch14;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class F2_FinallyCase2 {
	public static void main(String[] args) {

		Path file = Paths.get("D:\\ChangHwan\\java\\Simple.txt");
		BufferedWriter writer = null;

		try {
			// 아래문장에서 IOException 발생가능
			writer = Files.newBufferedWriter(file);

			writer.write('A'); //IOException 발생가능
			writer.write('Z'); //IOException 발생가능

			
			// writer.close(); //  IOException 발생가능
		} catch (IOException e) {
			e.printStackTrace();
		} finally { // finnally 코드의 실행이 try안으로 진입하면, 무조건 실행된다.
			try {
				if (writer != null)
					writer.close(); //IOException 발생가능	
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
// 실행의 흐름이 try 구문안에들어왔을때 반드시 실행해야 하는 문장을 finally 구문에 들 수 있다.