package ch22;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class C1_DataFilterOutputStream {
	public static void main(String[] args) {

		try (DataOutputStream out = new DataOutputStream(new FileOutputStream("data.dat"))) {
			out.writeInt(370);
			out.writeDouble(3.14);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}
