package ch22;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Scanner;

public class D3_TextWirter {

	public static void main(String[] args) {

		
		try(Writer out = new FileWriter("data1.txt")){
			for(int ch = (int)'A'; ch<(int)('Z'+1);ch++)
				
				out.write(ch);
			out.write(13);
			out.write(10);
			
			for(int ch = (int)'A'+32; ch<(int)('Z'+1+32); ch++) {
				out.write(ch);
			}
		}
			catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

// 읽을 파일이 있어야 에러가 발생하지 않는다