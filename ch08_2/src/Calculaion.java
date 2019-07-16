
class Calc {

	int add(int a, int b) {
		return a + b;
	}
}

public class Calculaion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calc calc = new Calc();

		int nRtn = calc.add(3, 9);

		System.out.println("3 + 9 = " + nRtn);

	}

}
