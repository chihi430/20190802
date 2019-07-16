public class test {

    public static void main(String[] args) {

    }

    long sum(int[] a){
        long sum = 0;

        for(int i = 0 ; i <a.length ; i++)
        {
            sum += a[i];
        }
        return sum;
    }
}
