import java.util.Random;

public class AZ2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A;
		int Z;

		for (A = 1; A < 10; A++) {
			for (Z = 1; Z < 10; Z++) {
				if (A + Z == 9) {
					if (A != Z) {
						System.out.print("  "+A + "" + Z + "\n");
						System.out.print("+ "+Z + "" + A + "\n");
					}
					System.out.println("---------------------");
					System.out.print("  "+(Z+A) +""+(A+Z)+"\n\n");
				}
			}
		}
	}
}
