import java.util.Random;

public class AZ {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A;
		int Z;
		Random randomV1 = new Random();


		while (true) {
			A = randomV1.nextInt(10);
			Z = randomV1.nextInt(10);
			if (A != Z) {
				System.out.println(" "+A+""+Z);
				System.out.println("+"+Z+""+A);
				System.out.println("-----------");
				System.out.println((A+Z)+""+(Z+A));
				break;
			}		
		}
	}
}
