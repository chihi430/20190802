package ch18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class F4_CopyList {

	public static void main(String[] args) {
		List<String> src = Arrays.asList("Box","Apple","Toy","Robot");
		
		List<String> dest = new ArrayList<>(src);
		
		Collections.sort(dest);
		
		System.out.println(dest);

		Collections.copy (dest, src);
		System.out.println(dest);
	}

}
//어제보다 더
//내일보다 덜
