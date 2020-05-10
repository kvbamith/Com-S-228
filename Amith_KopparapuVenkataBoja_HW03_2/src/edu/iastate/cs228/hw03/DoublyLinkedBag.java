package edu.iastate.cs228.hw03;

import edu.iastate.cs228.hw03.BagInterface;

/**
   A class of bags whose entries are stored in a chain of linked nodes.
	The bag is never full.
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 4.1
    @author Amith Kopparapu Venkata Boja
    @version 4.3
*/
public final class DoublyLinkedBag<T> implements BagInterface<T>
{
	private DoublyLinkedNode firstNode;       // Reference to first node
	private int numberOfEntries;

	public DoublyLinkedBag()
	{
		firstNode = null;
      numberOfEntries = 0;
	} // end default constructor

	/** Sees whether this bag is empty.
	    @return  True if this bag is empty, or false if not. */
	public boolean isEmpty() 
	{
		return numberOfEntries == 0;
	} // end isEmpty

	/** Gets the number of entries currently in this bag.
	    @return  The integer number of entries currently in this bag. */
	public int getCurrentSize() 
	{
		return numberOfEntries;
	} // end getCurrentSize

	/** Adds a new entry to this bag.
	    @param newEntry  The object to be added as a new entry
	    @return  True if the addition is successful, or false if not. */
	public boolean add(T newEntry)  	      // OutOfMemoryError possible
	{
      // Add to beginning of chain:
		DoublyLinkedNode newNode = new DoublyLinkedNode(newEntry);
		newNode.next = firstNode; // Make new node reference rest of chain
                                // (firstNode is null if chain is empty)        
      firstNode = newNode;      // New node is at beginning of chain
		numberOfEntries++;
      
		return true;
	} // end add

	/** Retrieves all entries that are in this bag.
	    @return  A newly allocated array of all the entries in this bag. */
	public Object[] toArray()
	{
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] result = (T[])new Object[numberOfEntries]; // Unchecked cast

      int index = 0;
      DoublyLinkedNode currentNode = firstNode;
      while ((index < numberOfEntries) && (currentNode != null))
      {
         result[index] = currentNode.data;
         index++;
         currentNode = currentNode.next;
      } // end while
      	
		return result;
	} // end toArray

	/** Counts the number of times a given entry appears in this bag.
		 @param anEntry  The entry to be counted.
		 @return  The number of times anEntry appears in this bag. */
	public int getFrequencyOf(T anEntry) 
	{
	  int frequency = 0;

      int counter = 0;
      DoublyLinkedNode currentNode = firstNode;
      while ((counter < numberOfEntries) && (currentNode != null))
      {
         if (anEntry.equals(currentNode.data))
         {
            frequency++;
         } // end if
         
         counter++;
         currentNode = currentNode.next;
      } // end while

		return frequency;
	} // end getFrequencyOf

	/** Tests whether this bag contains a given entry.
		 @param anEntry  The entry to locate.
		 @return  True if the bag contains anEntry, or false otherwise. */
	public boolean contains(T anEntry)
	{
      boolean found = false;
      DoublyLinkedNode currentNode = firstNode;
      
      while (!found && (currentNode != null))
      {
         if (anEntry.equals(currentNode.data))
            found = true;
         else
            currentNode = currentNode.next;
      } // end while	
      
      return found;
   } // end contains

 	// Locates a given entry within this bag.
	// Returns a reference to the node containing the entry, if located,
	// or null otherwise.
	private DoublyLinkedNode getReferenceTo(T anEntry)
	{
		boolean found = false;
		DoublyLinkedNode currentNode = firstNode;
		
		while (!found && (currentNode != null))
		{
			if (anEntry.equals(currentNode.data))
				found = true;
			else
				currentNode = currentNode.next;
		} // end while
     
		return currentNode;
	} // end getReferenceTo

   /** Removes all entries from this bag. */
	public void clear() 
	{
		while (!isEmpty()) 
         remove();
	} // end clear
	
	/** Removes one unspecified entry from this bag, if possible.
       @return  Either the removed entry, if the removal
                was successful, or null. */
	public T remove()
	{
		T result = null;
      if (firstNode != null)
      {
         result = firstNode.data; 
         firstNode = firstNode.next; // Remove first node from chain
         numberOfEntries--;
      } // end if

		return result;
	} // end remove
	
	/** Removes one occurrence of a given entry from this bag, if possible.
       @param anEntry  The entry to be removed.
       @return  True if the removal was successful, or false otherwise. */
   public boolean remove(T anEntry) 
	{
		boolean result = false;
		DoublyLinkedNode nodeN = getReferenceTo(anEntry);
      
      if (nodeN != null)
      {
         nodeN.data = firstNode.data; // Replace located entry with entry in first node
         
         firstNode = firstNode.next;  // Remove first node
         numberOfEntries--;
         
         result = true;
      } // end if
         
		return result;
	} // end remove

	private class DoublyLinkedNode 
	{
	  private T    data; // Entry in bag
	  private DoublyLinkedNode next; // Link to next node
	  private DoublyLinkedNode previous;

		private DoublyLinkedNode(T dataPortion)
		{
			this(dataPortion, null, null);	
		} // end constructor
		
		private DoublyLinkedNode(T dataPortion, DoublyLinkedNode nextNode, DoublyLinkedNode previousNode)
		{
			data = dataPortion;
			next = nextNode;	
			previous=previousNode;
		} // end constructor
	} // end Node
	public BagInterface<T> union(BagInterface<T> anotherBag){
		BagInterface<T> unionBag= new DoublyLinkedBag<T>();
		DoublyLinkedBag<T> otherBag=(DoublyLinkedBag<T>) anotherBag;
		int i=0;
		DoublyLinkedNode f=this.firstNode;
		while(i<numberOfEntries) {
			unionBag.add(f.data);
			f=f.next;
			i++;
		}
		int j=0;
		DoublyLinkedNode f2=otherBag.firstNode;
		while(j<otherBag.numberOfEntries) {
			unionBag.add(f2.data);
			f2=f2.next;
			j++;
		}
		
		return unionBag;
	}
	public BagInterface<T> intersection(BagInterface<T> anotherBag){
		DoublyLinkedBag<T> otherBag=(DoublyLinkedBag<T>) anotherBag;
		BagInterface<T> intersectionBag= new DoublyLinkedBag<T>();
		int i=0;
		DoublyLinkedNode f=this.firstNode;
		int numDifference;
		while(i<numberOfEntries) {
			numDifference=Math.min(otherBag.getFrequencyOf(f.data),this.getFrequencyOf(f.data));
			if(!(intersectionBag.contains(f.data))) {
				int j=0;
				while(j<numDifference) {
					intersectionBag.add(f.data);
					j++;
				}
			}
			i++;
			f=f.next;
		}
		return intersectionBag;
	}
	public BagInterface<T> difference(BagInterface<T> anotherBag){
		BagInterface<T> differenceBag= new DoublyLinkedBag<T>();
		DoublyLinkedBag<T> otherBag=(DoublyLinkedBag<T>) anotherBag;
		int i=0;
		int j=0;
		DoublyLinkedNode f=this.firstNode;
		DoublyLinkedNode f2=otherBag.firstNode;
		while(i<numberOfEntries) {
			differenceBag.add(f.data);
			f=f.next;
			i++;
		}
		while(j<otherBag.getCurrentSize()) {
			if(differenceBag.contains(f2.data)) {
				differenceBag.remove(f2.data);
			}
			j++;
			f2=f2.next;
		}
		return differenceBag;
	}
	public T replace(T replacement) {
		T result=null;
		if(firstNode!=null) {
			result=firstNode.data;
			firstNode.data=replacement;
		}
		return result;
	}
	public void removeEvery(T anEntry) {
		int i=0;
		DoublyLinkedNode f=this.firstNode;
		while(i<numberOfEntries) {
			if(f.data.equals(anEntry)) {
				this.remove(anEntry);
				i--;
			}
			f=f.next;
			i++;
		}
	}
	@Override
	public boolean equals(Object obj) {
		if(this==null || obj==null) {
			return false;
		}
		if(obj.getClass()==this.getClass()) {
			DoublyLinkedBag<T> otherBag=(DoublyLinkedBag<T>) obj;
			int i=0;
			boolean isEqual=true;
			if(this.getCurrentSize() != otherBag.getCurrentSize()) {
				return false;
			}
			DoublyLinkedNode f=this.firstNode;
			DoublyLinkedNode f2=otherBag.firstNode;
			while(i<numberOfEntries) {
				
				if(!(otherBag.contains(f.data) && this.getFrequencyOf(f.data)==otherBag.getFrequencyOf(f2.data) )) {
					isEqual=false;
				}
				f=f.next;
				f2=f2.next;
				i++;
			}
			return isEqual;
		}else {
			return false;
		}
	}
	public String toString() {
		if(this==null || this.numberOfEntries<=0) {
			return "[]";
		}
		int i=0;
		DoublyLinkedNode f=this.firstNode;
		String s="[";
		
		while(i<numberOfEntries-1) {
			s = s + f.data+ ", ";
			f=f.next;
			i++;
		}
		s =s+ f.data +"]";
		return s;
	}
} // end DoublyLinkedBag



