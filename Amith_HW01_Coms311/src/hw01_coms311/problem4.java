package hw01_coms311;

public class problem4 {

	public static void main(String[] args) {
		long timegcdinitial= System.currentTimeMillis();
		//long x = gcd(104395301,122949823);
		long x=gcd(1000000007,1500450271);
		long timegcdfinal= System.currentTimeMillis();
		System.out.println(timegcdfinal-timegcdinitial);
		
		long timefastgcdinitial= System.currentTimeMillis();
		//long x2 = fastgcd(104395301,122949823);
		long x2=fastgcd(1000000007,1500450271);
		long timefastgcdfinal= System.currentTimeMillis();
		System.out.println(timefastgcdfinal-timefastgcdinitial);
	}
	public static long gcd(long a, long b) {
		long n = Math.min(a, b);
		long i=0;
				for (i = n; i >=1; i--) {
					if  (a%i == 0 && b%i ==0) {
						return i;
					}
				}
				return 0;
	}
	public static long fastgcd (long a, long b) {
		if( b ==0) {
			return a;	
		}else{
			return fastgcd(b, a % b);
		}
	}
}
