package ch22;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class A5_Write7ToFile3 {

	public static void main(String[] args){
		
		try (OutputStream out = new FileOutputStream("data.dat")){
			out.write(65);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
