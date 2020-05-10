package edu.iastate.cs228.hw1;

import java.math.BigInteger;
/**
 * Build a rational class using BigInteger class' help
 * @author Amith Kopparapu Venkata Boja
 */
public class Rational2 extends Number implements Comparable<Rational2> {
	private BigInteger numerator;
	private BigInteger denominator;
	public Rational2() {
		numerator=BigInteger.valueOf(0);
		denominator=BigInteger.valueOf(1);
	}
	public Rational2(BigInteger numerator, BigInteger denominator)
	{
		this.numerator=numerator;
		this.denominator=denominator;
		int t= denominator.compareTo(BigInteger.valueOf(0));
		
		BigInteger temp= new BigInteger(t+"");
		BigInteger gcd = this.numerator.gcd(this.denominator);
		this.numerator = temp.multiply(numerator.divide(gcd));
		this.denominator = denominator.abs().divide(gcd);
	}
	/** Return numerator */
	public BigInteger getNumerator()
	{
		return numerator;
	}

	/** Return denominator */
	public BigInteger getDenominator()
	{
		return denominator;
	}
	/** Add a rational number to this rational */
	public Rational2 add(Rational2 secondRational)
	{
		BigInteger n = numerator.multiply(secondRational.getDenominator()).add(denominator.multiply(secondRational.getNumerator()));
		BigInteger d = denominator.multiply(secondRational.getDenominator());
		return new Rational2(n, d);
	}

	/** Subtract a rational number from this rational */
	public Rational2 subtract(Rational2 secondRational)
	{
		BigInteger n = numerator.multiply(secondRational.getDenominator()).subtract(denominator.multiply(secondRational.getNumerator()));
		BigInteger d = denominator.multiply(secondRational.getDenominator());
		return new Rational2(n, d);
	}

	/** Multiply a rational number to this rational */
	public Rational2 multiply(Rational2 secondRational)
	{
		BigInteger n = numerator.multiply(secondRational.getNumerator());
		BigInteger d = denominator.multiply(secondRational.getDenominator());
		return new Rational2(n, d);
	}

	/** Divide a rational number from this rational */
	public Rational2 divide(Rational2 secondRational)
	{
		BigInteger n = numerator.multiply(secondRational.getDenominator());
		BigInteger d = denominator.multiply(secondRational.numerator);
		return new Rational2(n, d);
	}

	@Override
	public String toString()
	{
		if (denominator.equals(new BigInteger("1")))
			return numerator + "";
		else
			return numerator + "/" + denominator;
	}

	/** Override the equals method in the Object class */
	public boolean equals(Object parm1)
	{
		if(this == parm1) return true;
		if((parm1 == null) || (parm1.getClass()!=this.getClass())) return false;
			
		//if (this.getNumerator() ==((Rational2)parm1).getNumerator())
		if ((this.subtract((Rational2) parm1)).getNumerator().longValue() == 0)
			return true;
		else
			return false;
	}

	/** Override the abstract intValue method in java.lang.Number */
	public int intValue()
	{
		return (int) doubleValue();
	}

	/** Override the abstract floatValue method in java.lang.Number */
	public float floatValue()
	{
		return (float) doubleValue();
	}

	/** Override the doubleValue method in java.lang.Number */
	public double doubleValue()
	{
		return numerator.doubleValue()/denominator.doubleValue();
	}

	/** Override the abstract longValue method in java.lang.Number */
	public long longValue()
	{
		return (long) doubleValue();
	}

	@Override
	public int compareTo(Rational2 o)
	{
		if (this.subtract(o).getNumerator().longValue() > 0)
			return 1;
		else if (this.subtract(o).getNumerator().longValue() < 0)
			return -1;
		else
			return 0;
	}

}
