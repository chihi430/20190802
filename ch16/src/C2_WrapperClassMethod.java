
public class C2_WrapperClassMethod {

	public static void main(String[] args) {
		
		Integer n1 = Integer.valueOf(5);
		Integer n2 = Integer.valueOf("1024");
		Integer n3 = Integer.valueOf(1025);
		int b = Integer.max(n1, n2);
		if(b>n3) {
			System.out.println(b);
		}else {
			System.out.println(n3);
		}
		
		System.out.println("큰 수 : "+Integer.max(n1, n2));
		System.out.println("작은 수 : "+Integer.min(n1, n2));
		System.out.println("합 : "+Integer.sum(n1, n2));
		System.out.println();
		
		
		System.out.println("12의 2진 표현 : " + Integer.toBinaryString(12));
		System.out.println("12의 8진 표현 : " + Integer.toOctalString(12));
		System.out.println("12의 16진 표현 : " + Integer.toHexString(12));
	}

}
//API를 보면.. Integer.valueOf(String)는 String이 Integer.parseInt(String)한거랑 똑같이 해석됩니다. 
//그러나, valueOf(String)은 new Integer()으로 객체를 반환하고 parseInt(String)은 int 기본 자료형을 반환합니다.
//Integer.valueOf(int)로 어떤 효율적인 코드를 작성하고 싶으시다면 아래같이 눈에 거슬리는 코드를 짜야됩니다.
//Integer k = Integer.valueOf(Integer.parseInt("123"))
//결론적으로 문자열을 변환할때 기본 자료형이아닌 객체로 받아오고 싶을때는 valueOf(String)을 쓰시면 되고. 
//그게 아닐경우는 parseInt(String)을 쓰시면 됩니다