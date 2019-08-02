package ch14;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class G_TryWithResource {
	public static void main(String[] args) {
		Path file = Paths.get("D:\\ChangHwan\\java\\Simple.txt");
		
		try(BufferedWriter writer = Files.newBufferedWriter(file);){
			writer.write('A'); 		//IOException 발생가능
			writer.write('f');	    //IOException 발생가능
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
