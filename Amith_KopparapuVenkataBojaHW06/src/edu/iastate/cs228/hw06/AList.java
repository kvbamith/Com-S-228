package edu.iastate.cs228.hw06;/**   A class that implements the ADT list by using a resizable array.   Entries in a list have positions that begin with 1.   Duplicate entries are allowed.   @author Frank M. Carrano   @author Timothy M. Henry   @version 4.0*/import java.util.Arrays;public class AList<T> extends CustomClass<T>{	private T[] list; // Array of list entries; ignore list[0]	private int numberOfEntries;	private boolean initialized = false;	private static final int DEFAULT_CAPACITY = 25;	private static final int MAX_CAPACITY = 10000;	public AList()	{		this(DEFAULT_CAPACITY);	} // end default constructor	public AList(int initialCapacity)	{		// Is initialCapacity too small?		if (initialCapacity < DEFAULT_CAPACITY)			initialCapacity = DEFAULT_CAPACITY;		else // Is initialCapacity too big?			checkCapacity(initialCapacity);		// The cast is safe because the new array contains null entries		@SuppressWarnings("unchecked")		T[] tempList = (T[]) new Object[initialCapacity + 1];		list = tempList;		numberOfEntries = 0;		initialized = true;	} // end constructor	public int getLength()	{		return numberOfEntries;	}	public void add(T newEntry)	{		checkInitialization();		list[numberOfEntries + 1] = newEntry;		numberOfEntries++;		ensureCapacity();		// add(numberOfEntries + 1, newEntry); // ALTERNATE CODE	} // end add		public void clear()	{ /* < Implementation deferred > */		for (int index = 1; index <= numberOfEntries; index++)		{			list[index + 1] = null;		} // end for	} // end clear		public T[] toArray()	{		checkInitialization();		// The cast is safe because the new array contains null entries		@SuppressWarnings("unchecked")		T[] result = (T[]) new Object[numberOfEntries]; // Unchecked cast		for (int index = 0; index < numberOfEntries; index++)		{			result[index] = list[index + 1];		} // end for		return result;	} // end toArray		public int getSize()	{		return list.length;	} // end getLength	public boolean isEmpty()	{		return numberOfEntries == 0; // Or getLength() == 0	} // end isEmpty	// Doubles the capacity of the array list if it is full.	// Precondition: checkInitialization has been called.	private void ensureCapacity()	{		int capacity = list.length - 1;		if (numberOfEntries >= capacity)		{			int newCapacity = 2 * capacity;			checkCapacity(newCapacity); // Is capacity too big?			list = Arrays.copyOf(list, newCapacity + 1);		} // end if	} // end ensureCapacity	/*	 * < This class will define checkCapacity, checkInitialization, and two more	 * private methods that will be discussed later. >	 */	private void checkInitialization()	{		if (!initialized)			throw new SecurityException("AList object is not initialized properly!");	}	private void checkCapacity(int capacity)	{		if (capacity > MAX_CAPACITY)			throw new IllegalStateException(					"Attempt to create a list whose capacity " + "exceeds allowed maximum of " + MAX_CAPACITY);	}	// Precondition: The array list has room for another entry.	public void add(int newPosition, T newEntry)	{		checkInitialization();		if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1))		{			if (newPosition <= numberOfEntries)				makeRoom(newPosition);			list[newPosition] = newEntry;			numberOfEntries++;			ensureCapacity(); // Ensure enough room for next add		} else			throw new IndexOutOfBoundsException("Given position of add's new entry is out of bounds.");	} // end add	// Makes room for a new entry at newPosition.	// Precondition: 1 <= newPosition <= numberOfEntries + 1;	// numberOfEntries is list's length before addition;	// checkInitialization has been called.	private void makeRoom(int newPosition)	{		assert (newPosition >= 1) && (newPosition <= numberOfEntries + 1);		int newIndex = newPosition;		int lastIndex = numberOfEntries;		// Move each entry to next higher index, starting at end of		// list and continuing until the entry at newIndex is moved		for (int index = lastIndex; index >= newIndex; index--)			list[index + 1] = list[index];	} // end makeRoom	public T remove(int givenPosition)	{		checkInitialization();		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))		{			assert !isEmpty();			T result = list[givenPosition]; // Get entry to be removed			// Move subsequent entries towards entry to be removed,			// unless it is last in list			if (givenPosition < numberOfEntries)				removeGap(givenPosition);			numberOfEntries--;			return result;		} else			throw new IndexOutOfBoundsException("Illegal position given to remove operation.");	} // end remove	// Shifts entries that are beyong the entry to be removed to the	// next lower position.	// Precondition: 1 <= givenPosition < numberOfEntries;	// numberOfEntries is list's length before removal;	// checkInitialization has been called.	private void removeGap(int givenPosition)	{		assert (givenPosition >= 1) && (givenPosition < numberOfEntries);		int removedIndex = givenPosition;		int lastIndex = numberOfEntries;		for (int index = removedIndex; index < lastIndex; index++)			list[index] = list[index + 1];	}	public T replace(int givenPosition, T newEntry)	{		checkInitialization();		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))		{			assert !isEmpty();			T originalEntry = list[givenPosition];			list[givenPosition] = newEntry;			return originalEntry;		} else			throw new IndexOutOfBoundsException("Illegal position given to replace operation.");	} // end replace	public T getEntry(int givenPosition)	{		checkInitialization();		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))		{			assert !isEmpty();			return list[givenPosition];		} else			throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");	} // end getEntry	public boolean contains(T anEntry)	{		checkInitialization();		boolean found = false;		int index = 1;		while (!found && (index <= numberOfEntries))		{			if (anEntry.equals(list[index]))				found = true;			index++;		} // end while		return found;	} // end contains	/**	 * Adds a new entry to the beginning of this list.	 * 	 * 	 */	public void addFirst(T newEntry) {//		checkInitialization();//		ensureCapacity();////		for(int i=numberOfEntries+1;i>1;i--) {////			list[i]=list[i-1];////		}//		list[1]=newEntry;//		numberOfEntries++;		add(1,newEntry);	}				/**	 * Adds a new entry to the end of this list.	 * 	 * 	 */	public void addLast(T newEntry) {		add(numberOfEntries+1,newEntry);	}		/**	 * Removes and returns the first entry in this list.	 * If none, then throws java.util.NoSuchElementException.	 * 	 * 	 */	public T removeFirst() {		if(numberOfEntries==0) {			throw new java.util.NoSuchElementException();		}		return remove(1);	}			/**	 * Removes and returns the last entry in this list.	 * If none, then throws java.util.NoSuchElementException.	 * 	 * 	 */	public T removeLast() {		if(numberOfEntries==0) {			throw new java.util.NoSuchElementException();		}		return remove(numberOfEntries);	}			/**	 * Returns the first entry in this list.	 * If none, then throws java.util.NoSuchElementException.	 * 	 * 	 */	public T getFirst() {		if(numberOfEntries==0) {			throw new java.util.NoSuchElementException();		}		return list[1];	}		/**	 * Returns the last entry in this list.	 * If none, then throws java.util.NoSuchElementException.	 * 	 * 	 */	public T getLast() {		if(numberOfEntries==0) {			throw new java.util.NoSuchElementException();		}		return list[numberOfEntries];	}			/**	 * Moves the first entry in this list to the end 	 * of the list. If only single entry or	 * the list is empty then does nothing.	 * 	 */	public void moveToEnd(){		if(numberOfEntries !=0) {			T temp=list[1];			for(int i=2;i<=numberOfEntries;i++) {				list[i-1]=list[i];			}			list[numberOfEntries]=temp;		}	}		/**	 * Removes the first occurrence of anEntry from	 * the list. Returns true if succefully removes,	 * otherwise, returns false.	 * 	 */	public boolean remove(T anEntry) {		if(numberOfEntries !=0) {			for(int i=1;i<numberOfEntries;i++) {				if(list[i].equals(anEntry)) {					remove(i);					return true;				}			}					}		return false;	}			/**	 * Returns the position of the first occurrence of an 	 * anEntry, if it exists in the list. If not returns -1. 	 * 	 */	public int getPosition(T anEntry) {		if(numberOfEntries !=0) {			for(int i=1;i<=numberOfEntries;i++) {				if(list[i].equals(anEntry)) {					return i;				}			}					}		return -1;	}	} // end AList