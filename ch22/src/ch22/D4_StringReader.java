package ch22;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class D4_StringReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try(BufferedReader br = new BufferedReader(new FileReader("박창환.txt"))){
			String str;
			
			while(true) {
				str = br.readLine();
				if(str == null)
					break;
				System.out.println(str);
			}			
		}catch (IOException e) {
			e.printStackTrace();
		}

	}

}
