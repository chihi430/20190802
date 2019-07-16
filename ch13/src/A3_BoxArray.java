class BoxA3 {
	public String conts;

	BoxA3(String cont) {
		this.conts = cont;
	}	
}

public class A3_BoxArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BoxA3[] ar = new BoxA3[3];
		int[] ar2 = new int[3];
		
		
		ar2[0] = 7;
		ar2[1] = 8;
		ar2[2] = 9;
		
		ar[0] = new BoxA3("First");
		ar[1] = new BoxA3("Second");
		ar[2] = new BoxA3("Third");
		

		int num = ar2[0] + ar2[1] + ar2[2];
		System.out.println(ar2[0] + "," + ar2[1] + "," + ar2[2]);
		System.out.println(num);
		
		System.out.println(ar[0].conts);
		System.out.println(ar[1].conts);
		System.out.println(ar[2].conts);
		

	}

}
