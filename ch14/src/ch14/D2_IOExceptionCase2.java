package ch14;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class D2_IOExceptionCase2 {
	public static void main(String[] args) {
		
		Path file = Paths.get("D:\\ChangHwan\\java\\Simple.txt");
		BufferedWriter writer = null;
		
		try {
			writer = Files.newBufferedWriter(file);
			
			writer.write('A');
			writer.write('Z');
			
			if(writer != null)
				writer.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
