package ch20;

interface PrintableB{
	void print(String s);
}

public class C2_Lambda2 {
	public static void main(String[] args) {
		PrintableB prn = new PrintableB() {
			
			@Override
			public void print(String s) {
				System.out.println(s);
				
			}
		};
		prn.print("What is Lambda?");

	}

}
