package edu.iastate.cs228.hw1;

/**
 * Make a duplicate of the String class with limited methods
 * @author Amith Kopparapu Venkata Boja
 */
public class MyString
{
	private char[] chars;

	public MyString(char[] chars)
	{
		if(chars == null || chars.length == 0) throw new IllegalArgumentException();
		
		this.chars=chars;
	}

	//https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#length--
	public int length()
	{
		return chars.length;
	}
	
	//https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#charAt-int-
	public char charAt(int index)
	{
		if(index<0 || index>=chars.length) {
			throw new IndexOutOfBoundsException();
		}else {	
		return chars[index];
		}
	}

	// https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#substring-int-int-
	public MyString substring(int begin, int end)
	{
		if(begin<0 || end>chars.length || end< begin) {
			throw new IndexOutOfBoundsException();
		}else {	
			char[] t= new char[end-begin];
			for(int i=0;i<end-begin;i++) {
				t[i]=chars[i+begin];
			}
			MyString temp=new MyString(t);
			return temp;
		}
	}

	//It is ok to use
	//https://docs.oracle.com/javase/8/docs/api/java/lang/Character.html#toLowerCase-char-
	public MyString toLowerCase()
	{
		char[] t= new char[chars.length];
		for(int i=0;i<chars.length;i++) {
			t[i]=chars[i];
			if(t[i]>= 'A' && t[i]<= 'Z') {
				t[i]+=32;
			}
		}
		MyString temp=new MyString(t);
		return temp;
	}

	
	//You can assume only positive values.
	//https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#valueOf-int-
	public static MyString valueOf(int i)
	{
		int tempNum=i;
		int k=0;
		while(tempNum>0) {
			tempNum= tempNum/10;
			k++;
		}
		char[] n=new char[k];
		int num;
		int j=k-1;
		while(i>0) {
			num=i%10;
			n[j]=(char)('0'+num);
			i=i/10;
			j--;
		}
		MyString temp=new MyString(n);
		return temp;
	}

	public char[] toCharArray()
	{
		char[] temp= new char[chars.length];
		for(int i=0;i<temp.length;i++) {
			temp[i]=chars[i];
		}
		return temp;
	}
}