package edu.iastate.cs228.hw06;

/**
   A class that implements the ADT list by using a chain of nodes
   that has both a head reference and a tail reference.
   @author 
   Amith Kopparapu Venkata Boja
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 4.0
*/
public class LListWithTail<T> extends CustomClass<T>
{
	private Node firstNode; // Head reference to first node
	private Node lastNode;  // Tail reference to last node
	private int  numberOfEntries;

	public LListWithTail()
	{
		initializeDataFields();
	} // end default constructor

	public void clear()
	{
		initializeDataFields();
	} // end clear
  
	public void add(T newEntry)
	{
		Node newNode = new Node(newEntry);

		if (isEmpty())
			firstNode = newNode;
		else
			lastNode.setNextNode(newNode);
		
  		lastNode = newNode;
  		numberOfEntries++;
	}  // end add

   public void add(int newPosition, T newEntry)
	{
      if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1))
      {
         Node newNode = new Node(newEntry);

         if (isEmpty())
         {
            firstNode = newNode;
            lastNode = newNode;
         }
         else if (newPosition == 1)
         {
            newNode.setNextNode(firstNode);
            firstNode = newNode;
         }
         else if (newPosition == numberOfEntries + 1)
         {
            lastNode.setNextNode(newNode);
            lastNode = newNode;
         } 
         else
         {
            Node nodeBefore = getNodeAt(newPosition - 1);
            Node nodeAfter = nodeBefore.getNextNode();
            newNode.setNextNode(nodeAfter);
            nodeBefore.setNextNode(newNode);
         } // end if	    
         numberOfEntries++;
      }
      else
         throw new IndexOutOfBoundsException("Illegal position given to add operation.");
	} // end add

	public T remove(int givenPosition)
	{
      T result = null;                           // Return value

      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
      {
         assert !isEmpty();
         if (givenPosition == 1)                 // Case 1: Remove first entry
         {
            result = firstNode.getData();        // Save entry to be removed
            firstNode = firstNode.getNextNode();
            if (numberOfEntries == 1)
               lastNode = null;                  // Solitary entry was removed
            }
            else                                 // Case 2: Not first entry
            {
               Node nodeBefore = getNodeAt(givenPosition - 1);
               Node nodeToRemove = nodeBefore.getNextNode();
               Node nodeAfter = nodeToRemove.getNextNode();
               nodeBefore.setNextNode(nodeAfter);// Disconnect the node to be removed
               result = nodeToRemove.getData();  // Save entry to be removed

               if (givenPosition == numberOfEntries)
                  lastNode = nodeBefore;         // Last node was removed
         } // end if

         numberOfEntries--;
      }
      else
         throw new IndexOutOfBoundsException("Illegal position given to remove operation.");

      return result;                             // Return removed entry
	} // end remove

	public T replace(int givenPosition, T newEntry)
	{
      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
      {   
      	assert !isEmpty();

			Node desiredNode = getNodeAt(givenPosition);
         T originalEntry = desiredNode.getData();
			desiredNode.setData(newEntry);
         return originalEntry;
      }
		else
         throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
   } // end replace

   public T getEntry(int givenPosition)
   {
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
		{
			assert !isEmpty();
         return getNodeAt(givenPosition).getData();
     	}
      else
         throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
   } // end getEntry

   public T[] toArray()
   {
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] result = (T[])new Object[numberOfEntries];
      
      int index = 0;
      Node currentNode = firstNode;
      while ((index < numberOfEntries) && (currentNode != null))
      {
         result[index] = currentNode.getData();
         currentNode = currentNode.getNextNode();
         index++;
      } // end while
      
      return result;
   } // end toArray
   
	public boolean contains(T anEntry)
	{
		boolean found = false;
		Node currentNode = firstNode;
		
		while (!found && (currentNode != null))
		{
			if (anEntry.equals(currentNode.getData()))
				found = true;
			else
				currentNode = currentNode.getNextNode();
		} // end while
		
		return found;
	} // end contains

   public int getLength()
   {
      return numberOfEntries;
   } // end getLength

   public boolean isEmpty()
   {
   		boolean result;
   		
      	if (numberOfEntries == 0) // Or getLength() == 0
      	{
      		assert (firstNode == null) && (lastNode == null);
      		result = true;
      	}
      	else
      	{
      		assert (firstNode != null) && (lastNode != null);
      		result = false;
      	} // end if
      	
      	return result;
   } // end isEmpty
	
   // Initializes the class's data fields to indicate an empty list.
   private void initializeDataFields()
   {
		firstNode = null;
  		lastNode = null;
		numberOfEntries = 0;
   } // end initializeDataFields
	
	// Returns a reference to the node at a given position.
	// Precondition: List is not empty; 1 <= givenPosition <= numberOfEntries.
	private Node getNodeAt(int givenPosition)
	{
		assert (firstNode != null) && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
		Node currentNode = firstNode;
		
      if (givenPosition == numberOfEntries)
         currentNode = lastNode;
      else if (givenPosition >1)
		{
         assert givenPosition < numberOfEntries;
         // Traverse the chain to locate the desired node
         for (int counter = 1; counter < givenPosition; counter++)
            currentNode = currentNode.getNextNode();
		} // end if
      
		assert currentNode != null;
		return currentNode;
	} // end getNodeAt

	private class Node 
	{
		private T data;     // Data portion
		private Node next;  // Next to next node

		private Node(T dataPortion)//  PRIVATE or PUBLIC is OK
		{
			data = dataPortion;
			next = null;	
		} // end constructor

		private Node(T dataPortion, Node nextNode)//  PRIVATE or PUBLIC is OK
		{
			data = dataPortion;
			next = nextNode;	
		} // end constructor

		private T getData()
		{
			return data;
		} // end getData
		
		private void setData(T newData)
		{
			data = newData;
		} // end setData
		
		private Node getNextNode()
		{
			return next;
		} // end getNextNode
		
		private void setNextNode(Node nextNode)
		{
			next = nextNode;
		} // end setNextNode
	} // end Node
	/**
	 * Adds a new entry to the beginning of this list.
	 * 
	 * 
	 */
	public void addFirst(T newEntry) {
		add(1,newEntry);
	}
	
	
	
	/**
	 * Adds a new entry to the end of this list.
	 * 
	 * 
	 */
	public void addLast(T newEntry) {
		add(numberOfEntries+1,newEntry);
	}
	
	/**
	 * Removes and returns the first entry in this list.
	 * If none, then throws java.util.NoSuchElementException.
	 * 
	 * 
	 */
	public T removeFirst() {
		if(numberOfEntries==0) {
			throw new java.util.NoSuchElementException();
		}
		return remove(1);
	}
	
	/**
	 * Removes and returns the last entry in this list.
	 * If none, then throws java.util.NoSuchElementException.
	 * 
	 * 
	 */
	public T removeLast() {
		if(numberOfEntries==0) {
			throw new java.util.NoSuchElementException();
		}
		return remove(numberOfEntries);
	}
	
	
	/**
	 * Returns the first entry in this list.
	 * If none, then throws java.util.NoSuchElementException.
	 * 
	 * 
	 */
	public T getFirst() {
		if(numberOfEntries==0) {
			throw new java.util.NoSuchElementException();
		}
		return firstNode.data;
	}
	
	/**
	 * Returns the last entry in this list.
	 * If none, then throws java.util.NoSuchElementException.
	 * 
	 * 
	 */
	public T getLast() {
		if(numberOfEntries==0) {
			throw new java.util.NoSuchElementException();
		}
		return lastNode.data;
	}
	
	
	/**
	 * Moves the first entry in this list to the end 
	 * of the list. If only single entry or
	 * the list is empty then does nothing.
	 * 
	 */
	public void moveToEnd(){
		if(numberOfEntries !=0) {
			T temp=firstNode.data;
			Node n=firstNode;
			for(int i=1;i<numberOfEntries;i++) {
				n.setData(n.next.data);
				n=n.next;
			}
			n.setData(temp);
		}
	}
	
	/**
	 * Removes the first occurrence of anEntry from
	 * the list. Returns true if succefully removes,
	 * otherwise, returns false.
	 * 
	 */
	public boolean remove(T anEntry) {
		if(numberOfEntries !=0) {
			Node n=firstNode;
			for(int i=1;i<numberOfEntries;i++) {
				if(n.data.equals(anEntry)) {
					remove(i);
					return true;
				}
				n=n.next;
			}
			
		}
		return false;
	}
	
	
	/**
	 * Returns the position of the first occurrence of an 
	 * anEntry, if it exists in the list. If not returns -1. 
	 * 
	 */
	public int getPosition(T anEntry) {
		if(numberOfEntries !=0) {
			Node n=firstNode;
			for(int i=1;i<=numberOfEntries;i++) {
				if(n.data.equals(anEntry)) {
					return i;
				}
				n=n.next;
			}
			
		}
		return -1;
	}
	
} // end LListWithTail

