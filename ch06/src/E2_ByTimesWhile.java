public class E2_ByTimesWhile {

	public static void main(String[] args) {
		int i = 1;
		

		while (i < 9) {			
			int j=1;
			i++;
			System.out.println("-------------------");
			System.out.println(i + " 단 ");
			
			
			while (j < 10) {
			System.out.print(i + " * " + j + " = " + (i * j));
			System.out.print('\n');
			j++;
			}
			if(i >9) {
				break;
			}
			
		}
	}
}
