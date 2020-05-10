package edu.iastate.cs228.hw1;
/**
 * Test the string  and rational class for debugging
 * @author Amith Kopparapu Venkata Boja
 */
import java.math.BigInteger;
import java.util.Arrays;
public class MyStringTester {

	public static void main(String[] args) {
        char[] str = new char[]{'h', 'e', 'l', 'l', 'o', ' ', 'w', 'o', 'r', 'l', 'd', '!'};
        
		MyString s=new MyString(str);
		//System.out.println(Arrays.toString(s.valueOf(15)));
		Rational2 r2 = new Rational2(BigInteger.valueOf(5), BigInteger.valueOf(1));
		Rational2 r3 = new Rational2(BigInteger.valueOf(5), BigInteger.valueOf(1));
		System.out.println(r2.equals(r3));

	}

}
