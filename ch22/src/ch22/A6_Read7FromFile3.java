package ch22;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class A6_Read7FromFile3 {

	public static void main(String[] args) throws IOException{
		
		try(InputStream in = new FileInputStream("data.dat")) {
			
			int dat = in.read();
						
			System.out.println(dat);
			System.out.printf("%c",dat);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
