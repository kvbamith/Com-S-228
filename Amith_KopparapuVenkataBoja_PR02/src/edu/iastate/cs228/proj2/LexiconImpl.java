package edu.iastate.cs228.proj2;

import java.util.Comparator;
/**
 * 
 * @author 
 *Amith Kopparapu Venkata Boja
 */
public class LexiconImpl implements Lexicon, Comparator<String> {

    /***
     * Lookup table mapping characters in lexicographical order to
     * to their input order. This is public to support automated grading. 
     */
	public CharacterValue[] characterOrdering; 

    /***
     * Creates an array of CharacterValue from characterOrdering.  Sorts
     * it using java.util.Arrays.sort().
     * @param characterOrdering character order from configuration file
     */	
	public LexiconImpl(char[] characterOrdering) {
		char[] temp=new char[characterOrdering.length];
		for(int i=0;i<characterOrdering.length;i++) {
			temp[i]=characterOrdering[i];
		}
		java.util.Arrays.sort(characterOrdering);
		this.characterOrdering=new CharacterValue[characterOrdering.length];
		for(int i=0;i<this.characterOrdering.length;i++) {
			int j=0;
			for(j=0;j<temp.length;j++) {
				if(temp[j]==characterOrdering[i]) {
					break;
				}
			}
			this.characterOrdering[i]=new CharacterValue(j,characterOrdering[i]);
		}
	}


    /***
     * Compares two words based on the configuration
     * @param a first word
     * @param b second word
     * @return negative if a<b, 0 if equal, postive if a>b
     */
	@Override
	public int compare(String a, String b) {
		if(a!=null || b!=null || isValid(a) || isValid(b)) {
			int length=Math.min(a.length(),b.length());
			int aValue=-1;
			int bValue=-1;
			for(int j=0;j<length;j++) {
				for(int i=0;i<characterOrdering.length;i++) {
					if(a.charAt(j)==characterOrdering[i].character) {
						aValue=characterOrdering[i].value;
					}
					if(b.charAt(j)==characterOrdering[i].character) {
						bValue=characterOrdering[i].value;
					}
				}
				if(aValue<bValue) {
					return 1;
				}
				else if(aValue>bValue) {
					return -1;
				}
			}
		}
		if(b.length()>a.length()) {
			return -1;
		}
		else if(a.length()>b.length()) {
			return 1;
		}else {
			return 0; 
		}
	}
	
	/**
	 * Uses binary search to find the order of key.
	 * @param key
	 * @return ordering value for key or -1 if key is an invalid character.
	 */
	public int getCharacterOrdering(char key) {
		int begin=0;
		int end=this.characterOrdering.length;
		int mid=(begin+(end-1))/2;
		while(end>=begin) {
			mid=((begin+(end-1))/2);
			if (characterOrdering[mid].character < key) {
	            begin = mid + 1;
	        } else if (characterOrdering[mid].character > key) {
	            end = mid - 1;
	        } else if (characterOrdering[mid].character == key) {
	            return characterOrdering[mid].value;
	        }
		}
		return -1; 

	}

	public static class CharacterValue {
		public int value;
		public char character;
		
		public CharacterValue(int value, char character) {
			this.value = value;
			this.character = character;
		}
		
		public boolean equals(Object o) {
			if (o == null || o.getClass() != this.getClass()) {
				return false;
			}
			CharacterValue other = (CharacterValue) o;
			return value == other.value && character == other.character;
		}
	}
	
	/**
	 * Returns whether or not word is valid according to the alphabet
	 * known to this lexicon. 
	 * 
	 * @param word word to be checked.
	 *
	 * @return true if valid. false otherwise
	 */
	public boolean isValid(String word) {
		boolean contains=true;
		for(int i=0;i<word.length();i++) {
			if(contains) {
				for(int j=0;j<characterOrdering.length;j++) {
					if(word.charAt(i)==characterOrdering[j].character) {
						contains=true;
						break;
					}else {
						contains=false;
					}
				}
			}else {
				contains=false;
				break;
			}
		}
		return contains; 

	}
	
}
