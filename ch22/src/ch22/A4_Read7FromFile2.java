package ch22;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class A4_Read7FromFile2 {

	public static void main(String[] args) throws IOException{
		InputStream in = null;
		try {
			 in = new FileInputStream("data.dat");
			int dat = in.read();
						
			System.out.println(dat);
			System.out.printf("%c",dat);
			
		}finally {
			if(in != null)
				in.close();	
		}
	}
}
