package ch19;

public class C1_Varargs {
	public static void ShowAll(String... vargs) {
		System.out.println("LEN"+vargs.length);
		
		for(String s : vargs)
			System.out.print(s+'\t');
		System.out.println();
		

	}
	
	public static void main(String[] args) {
		ShowAll("Box");
		ShowAll("Box","Toy");
		ShowAll("Box","Toy","Apple");
		
	}

}
