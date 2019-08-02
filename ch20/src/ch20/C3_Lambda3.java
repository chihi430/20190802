package ch20;

interface PrintableC{
	void print(String s);
}

public class C3_Lambda3 {
	public static void main(String[] args) {
		PrintableC prn = (s) -> { System.out.println(s); };
		prn.print("what is Lambda?");
	}
}
