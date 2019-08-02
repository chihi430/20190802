package ch22;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class D1_SimpleWriter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try(Writer out = new FileWriter("data.txt")){
			
			out.write('A');
			out.write('한');
		}catch (IOException e) {
			e.printStackTrace();
		}

	}

}
//이클립스 설정에서 문자셋을 UTF-8로 지정한 경우