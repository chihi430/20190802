package ch22;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class C4_BufferDataOutputStream {

	public static void main(String[] args) {
		try(DataOutputStream out=
				new DataOutputStream(new FileOutputStream("data.dat"))) {
			out.writeInt(370);
			out.flush();
			out.writeDouble(3.14);
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
