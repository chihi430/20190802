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
			// �Ʒ����忡�� IOException �߻�����
			writer = Files.newBufferedWriter(file);

			writer.write('A'); //IOException �߻�����
			writer.write('Z'); //IOException �߻�����

			
			// writer.close(); //  IOException �߻�����
		} catch (IOException e) {
			e.printStackTrace();
		} finally { // finnally �ڵ��� ������ try������ �����ϸ�, ������ ����ȴ�.
			try {
				if (writer != null)
					writer.close(); //IOException �߻�����	
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
// ������ �帧�� try �����ȿ��������� �ݵ�� �����ؾ� �ϴ� ������ finally ������ �� �� �ִ�.