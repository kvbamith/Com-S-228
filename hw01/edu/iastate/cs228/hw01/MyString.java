package edu.iastate.cs228.hw01;

/**
 * @author
 *
 */
public class MyString
{
	private char[] chars;

	public MyString(char[] chars)
	{
		if(chars == null || chars.length == 0) throw new IllegalArgumentException();
		
		//TODO
	}

	//https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#length--
	public int length()
	{
		//TODO
	}
	
	//https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#charAt-int-
	public char charAt(int index)
	{
		//TODO
	}

	// https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#substring-int-int-
	public MyString substring(int begin, int end)
	{
		//TODO
	}

	//It is ok to use
	//https://docs.oracle.com/javase/8/docs/api/java/lang/Character.html#toLowerCase-char-
	public MyString toLowerCase()
	{
		//TODO
	}

	
	//You can assume only positive values.
	//https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#valueOf-int-
	public static MyString valueOf(int i)
	{
		//TODO
	}

	public char[] toCharArray()
	{
		return chars;
	}
}