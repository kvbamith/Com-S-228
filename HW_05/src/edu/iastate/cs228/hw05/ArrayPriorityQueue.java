package edu.iastate.cs228.hw05;

/**
 * A class of priority queues represented by an array.
 *
 * @author
 * Amith Kopparapu Venkata Boja
 * NOTE: 
 * 0. Put your Firstname and Lastname after above author tag.
 * 			Make sure that in both cases the first letter is uppercase
 *    and all others are lowercase.
 * 1. You are allowed to create and use your own private helper methods. 
 * 2. No additional data fields can be introduced.
 * 3. No custom classes of your own can be introduced or used.
 * 4. Import statements are not allowed.
 * 5. Fully qualified class names usage is not allowed.
 *    (Except for the methods that are provided already for you, which
 *    do not need to be implemented as part of this HW, i.e. needs to be
 *    used as it is.)
 * 6. You are allowed to reuse any part of the source codes of provided
 *    source codes or shown under lecture notes.
 * 
 * DESCRIPTION:
 * A class of priority queues represented by an array.
 * 
 * For details of priority queue implementation using arrays, check slide 
 * number 14 of "queueDequePriorityQueueImplementations_part3.pdf" file 
 * under lecture notes of Friday of Week 5 on Canvas.
 */


public class ArrayPriorityQueue<T extends Comparable<? super T>>
		implements PriorityQueueInterface<T>
{
	private T[] queue; // The contents of the priority queue
	private int frontIndex; // The index of the current front entry
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;

	public ArrayPriorityQueue()
	{
		this(DEFAULT_CAPACITY);
	} // end default constructor

	public ArrayPriorityQueue(int initialCapacity)
	{
		checkCapacity(initialCapacity);

		// The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] tempQueue = (T[]) new Comparable[initialCapacity]; // Unchecked cast
		queue = tempQueue;
		frontIndex = -1;
		initialized = true;
	}

	
	public void add(T newEntry)
	{
		checkInitialization();
		ensureCapacity();
		if(newEntry!=null) {
			if(isEmpty()) {
				queue[0]=newEntry;
			}else {
				for(int i=0;i<getSize();i++) {
					if(queue[i].compareTo(newEntry)>0) {
						for(int j=getSize();j>i;j--) {
							queue[j]=queue[j-1];
						}
						queue[i]=newEntry;
						break;
					}
				}
				if(queue[getSize()-1].compareTo(newEntry)<0) {
					queue[getSize()]=newEntry;
				}
			}
			frontIndex++;	
		}
	}

	public T remove()
	{
		checkInitialization();
		if(isEmpty()) {
			return null;
		}else {
			T temp= queue[frontIndex];
			frontIndex--;
			return temp;
		}
	}

	public T peek()
	{
		checkInitialization();
		if(getSize()==0) {
			return null;
		}
		return queue[frontIndex];
	}

	/**
  * If queue is empty returns [].
  * Else, returns as [1, 2, 3]
  * Important: note a comma and single space before every
  * item except the last, and after last there is no space.
  * In both cases before and after square brackets there
  * is no space.
  *    
  */
 @Override
 public String toString()
 {
 	if(isEmpty()) {
 		return "[]";
 	}else {
 		String temp="["+queue[0].toString();
 		for(int i=1;i<getSize();i++) {
 			temp= temp + ", " + queue[i].toString();
 		}
 		temp= temp + "]";
 		return temp;
 	}
 }

	
	public boolean isEmpty()
	{
		return (frontIndex < 0);
	}

	public int getSize()
	{
		return frontIndex + 1;
	}

	public void clear()
	{
  checkInitialization();
		for (int x = 0; x < queue.length; x++)
			queue[x] = null;
		frontIndex = -1;
	}

	

	
	// Throws an exception if this object is not initialized.
	private void checkInitialization()
	{
		if (!initialized)
			throw new SecurityException ("ArrayPriorityQueue object is not "
					+ "initialized properly.");
	}

	// Throws an exception if the client requests a capacity that is too large.
	private void checkCapacity(int capacity)
	{
		if (capacity > MAX_CAPACITY)
			throw new IllegalStateException("Attempt to create a priority queue " +
					"whose capacity exceeds allowed maximum.");
	} 

	// Doubles the size of the array queue if it is full.
	// Precondition: checkInitialization has been called.
	private void ensureCapacity()
	{
		if (frontIndex == queue.length - 1)
		{
			checkCapacity(queue.length * 2);
			queue = java.util.Arrays.copyOf(queue, queue.length * 2);
		}
	}
} // end ArrayPriorityQueue
