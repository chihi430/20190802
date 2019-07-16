import java.util.Scanner;

public class Quiz6 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String str;
		System.out.println("단어를 입력하시오");
		str = sc.nextLine();

		StringBuffer sb = new StringBuffer(str);
		StringBuffer re = sb.reverse();
		//System.out.println(sb.reverse());

		if (str.equals(re.toString())) {
			System.out.println("회문");
		} else {
			System.out.println("회문아님");
		}
	}
}

