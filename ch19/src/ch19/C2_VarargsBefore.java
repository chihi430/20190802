package ch19;

public class C2_VarargsBefore {
	public static void ShowAll(String... vargs) {
		System.out.println("LEN"+vargs.length);
		
		for(String s : vargs)
			System.out.print(s+'\t');
		System.out.println();
		

	}
	
	public static void main(String[] args) {
		ShowAll(new String[]  {"Box"});
		ShowAll(new String[]  {"Box","Toy"});
		ShowAll(new String[]  {"Box","Toy","Apple"});
		
	}

}
