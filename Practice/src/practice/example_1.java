package practice;

public class example_1 {

	public static void main(String[] args) {
		System.out.println(fib(5));

	}
	public static long fib(long index)
	{
	 if(index == 0) return 0;
	 else if(index == 1) return 1;
	 else return fib(index - 1) + fib(index - 2);
	}

}
