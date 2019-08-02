package ch22;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class A2_Read7FromFile {

	public static void main(String[] args){

		try {
			InputStream in = new FileInputStream("data.dat");
			int dat = in.read();
			in.close();
			
			System.out.println(dat);
			System.out.printf("%s",dat);
			
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
