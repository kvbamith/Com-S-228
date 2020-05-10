package edu.iastate.cs228.proj3;

/**
 *  @author
 *Amith Kopparapu Venkata Boja
 *
 *
 *  An implementation of List<E> based on a doubly-linked list 
 *  with an array for indexed reads/writes
 *
 */

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class AdaptiveList<E> implements List<E>
{
  public class ListNode 
  {                     
    public E data;        
    public ListNode next; 
    public ListNode prev; 
    
    public ListNode(E item)
    {
      data = item;
      next = prev = null;
    }
  }
  
  public ListNode head;  // dummy node made public for testing.
  public ListNode tail;  // dummy node made public for testing.
  private int numItems;  // number of data items
  private boolean linkedUTD; // true if the linked list is up-to-date.

  public E[] theArray;  // the array for storing elements
  private boolean arrayUTD; // true if the array is up-to-date.

  public AdaptiveList()
  {
    clear();
  }

  @Override
  public void clear()
  {
    head = new ListNode(null);
    tail = new ListNode(null);
    head.next = tail;
    tail.prev = head;
    numItems = 0;
    linkedUTD = true;
    arrayUTD = false;
    theArray = null;
  }

  public boolean getlinkedUTD()
  {
    return linkedUTD;
  }

  public boolean getarrayUTD()
  {
    return arrayUTD;
  }

  public AdaptiveList(Collection<? extends E> c)
  {
	this();
    theArray=(E[])c.toArray();
    numItems=theArray.length;
    int i=0;
    for(E val:c) {
    		add(val);
    		theArray[i]=val;
    		i++;
    }
    
  }

  // Removes the node from the linked list.
  // This method should be used to remove a node 
  // from the linked list.
  private void unlink(ListNode toRemove)
  {
    if ( toRemove == head || toRemove == tail )
      throw new RuntimeException("An attempt to remove head or tail");
    toRemove.prev.next = toRemove.next;
    toRemove.next.prev = toRemove.prev;
  }

  // Inserts new node toAdd right after old node current.
  // This method should be used to add a node to the linked list.
  private void link(ListNode current, ListNode toAdd)
  {
    if ( current == tail )
      throw new RuntimeException("An attempt to chain after tail");
    if ( toAdd == head || toAdd == tail )
      throw new RuntimeException("An attempt to add head/tail as a new node");
    toAdd.next = current.next;
    toAdd.next.prev = toAdd;
    toAdd.prev = current;
    current.next = toAdd;
  }

  private void updateArray() // makes theArray up-to-date.
  {
    if ( numItems < 0 )
      throw new RuntimeException("numItems is negative: " + numItems);
    if ( ! linkedUTD )
      throw new RuntimeException("linkedUTD is false");
    ListNode n=head.next;
    for(int i=0;i<numItems-1;i++) {
    		theArray[i]=n.data;
    		n=n.next;
    }
    arrayUTD=true;
  }

  private void updateLinked() // makes the linked list up-to-date.
  {
    if ( numItems < 0 )
      throw new RuntimeException("numItems is negative: " + numItems);
    if ( ! arrayUTD )
      throw new RuntimeException("arrayUTD is false");

    if ( theArray == null || theArray.length < numItems )
      throw new RuntimeException("theArray is null or shorter");
    
    ListNode n=head.next;
    for(int i=0;i<numItems-1;i++) {
    		n.data=theArray[i];
    		n=n.next;
    }
    linkedUTD=true;
  }

  @Override
  public int size()
  {
    if(arrayUTD) {
    		return theArray.length;
    }else {
    		return numItems;
    }
  }

  @Override
  public boolean isEmpty()
  {
	if(linkedUTD) {
    		return (head.next==tail && tail.prev==head);
    }else {
    		return theArray.length==0;
    }
  }

  @Override
  public boolean add(E obj)
  {
	if(obj==null) {
		throw new NullPointerException();
	}
	else if(!linkedUTD && !arrayUTD) {
		return false;
	}
	else {
	    if(!linkedUTD) {
			updateLinked();
	    }
	    ListNode n=new ListNode(obj);
	    n.next=tail;
	    n.prev=tail.prev;
	    tail.prev.next=n;
	    tail.prev=n;
	    arrayUTD=false;
	    return true; 
	}
  }

  @Override
  public boolean addAll(Collection< ? extends E> c)
  {
    E[] temp=(E[]) c.toArray();
    if(!linkedUTD) {
		updateLinked();
    }
    for(int i=0;i<c.size();i++) {
    		boolean checker=add(temp[i]);
    		arrayUTD=false;
    		if(!checker) {
    			return false;
    		}
    }
    return true; 
  }

  @Override
  public boolean remove(Object obj)
  {
	if(obj==null) {
		return false;
	}else {
	    if(!linkedUTD) {
			updateLinked();
	    }
		ListNode n=head.next;
		for(int i=0;i<numItems-1;i++) {
			if(n.data.equals(obj)) {
				n.next.prev = n.prev;
				n.prev.next = n.next;
				
				break;
			}
			n=n.next;
			arrayUTD=false;
		}
		return true;
	}
  }

  private void checkIndex(int pos) // a helper method
  {
    if ( pos >= numItems || pos < 0 )
     throw new IndexOutOfBoundsException(
       "Index: " + pos + ", Size: " + numItems);
  }

  private void checkIndex2(int pos) // a helper method
  {
    if ( pos > numItems || pos < 0 )
     throw new IndexOutOfBoundsException(
       "Index: " + pos + ", Size: " + numItems);
  }

  private void checkNode(ListNode cur) // a helper method
  {
    if ( cur == null || cur == tail )
     throw new RuntimeException(
      "numItems: " + numItems + " is too large");
  }

  private ListNode findNode(int pos)   // a helper method
  {
    ListNode cur = head;
    for ( int i = 0; i < pos; i++ )
    {
      checkNode(cur);
      cur = cur.next;
    }
    checkNode(cur);
    return cur;
  }

  @Override
  public void add(int pos, E obj)
  {
	if(obj!=null && pos>=0 && pos<numItems) {
		if(!linkedUTD) {
			updateLinked();
		}
		ListNode n=head.next;
		for(int i=0;i<numItems-1;i++) {
			if(i==pos-1) {
				ListNode temp= new ListNode(obj);
				n.next.prev=temp;
				temp.next=n.next;
				temp.prev=n;
				n.next=temp;
				numItems++;
				arrayUTD=false;
				break;
			}
			n=n.next;
		}
	}
  }

  @Override
  public boolean addAll(int pos, Collection< ? extends E> c)
  {
	  E[] temp=(E[]) c.toArray();
	    if(!linkedUTD) {
			updateLinked();
	    }
	    int index=pos;
	    for(int i=0;i<c.size();i++) {
	    		add(index++,temp[i]);
	    }
	    arrayUTD=false;
	    return true; 
  }

  @Override
  public E remove(int pos)
  {
	if(pos>=0 && pos<numItems) {  
		if(!linkedUTD) {
			updateLinked();
		}
		ListNode n=head.next;
		E temp=null;
		for(int i=0;i<numItems-1;i++) {
			if(i==pos) {
				temp= n.data;
				remove(n.data);
			}
			n=n.next;
		}
		arrayUTD=false;
		return temp;
	}else {
		return null;
	}
  }

  @Override
  public E get(int pos)
  {        
	if(!arrayUTD) {
		updateArray();
	}
    if(pos>=0 && pos<numItems) {

        linkedUTD=false;
    		return theArray[pos];
    }
    return null; 
  }

  @Override
  public E set(int pos, E obj)
  {
	if(!arrayUTD) {
    		updateArray();
    }
	if(obj!=null && pos>=0 && pos<numItems) {
		E temp=theArray[pos];
		theArray[pos]=obj;
		linkedUTD=false;
		return temp;
	}
    return obj; 
  } 

  /**
   *  If the number of elements is at most 1, 
   *  the method returns false. Otherwise, it 
   *  reverses the order of the elements in the 
   *  array without using any additional array, 
   *  and returns true. Note that if the array 
   *  is modified, then linkedUTD needs to be set 
   *  to false.
   */
  public boolean reverse()
  {
	if(!arrayUTD) {
    		updateArray();
    } 
	if(theArray.length>1) {
		for(int i=0;i<numItems/2;i++) {
			E temp=theArray[i];
			theArray[i]=theArray[theArray.length-i-1];
			theArray[theArray.length-i-1]=temp;
		}
		linkedUTD=false;
	    	return true;
	}
	else {
		return false;
	}
  }

  
  /** 
   *  If the number of elements is at most 1, 
   *  the method returns false. Otherwise, it 
   *  swaps the items positioned at even index 
   *  with the subsequent one in odd index without 
   *  using any additional array, and returns true.
   *  Note that if the array is modified, then 
   *  linkedUTD needs to be set to false. 
   */
  public boolean reorderOddEven()
  {
	if(!arrayUTD) {
  		updateArray();
	} 
	if(theArray.length>1) {
		for(int i=0;i<theArray.length-1;i+=2) {
			E temp=theArray[i];
			theArray[i]=theArray[i+1];
			theArray[i+1]=temp;
		}
		linkedUTD=false;
	    	return true;
	}
	else {
		return false;
	}
  }
  
  @Override
  public boolean contains(Object obj)
  {
    if(!linkedUTD) {
    		updateLinked();
    }
    ListNode n=head;
    for(int i=0;i<numItems;i++) {
    		if(n.data==obj) {
    			return true;
    		}
    		n=n.next;
    }
   return false;
  }

  @Override
  public boolean containsAll(Collection< ? > c)
  {
   E[] temp=(E[])c.toArray();
   for(int i=0;i<c.size();i++) {
	   boolean checker=contains(temp[i]);
	   if(!checker) {
		   return false;
	   }
   }
   return true; 
  }


  @Override
  public int indexOf(Object obj)
  {
    ListNode n=head.next;
    for(int i=0;i<numItems;i++) {
    		if(n.data.equals(obj)) {
    			return i;
    		}
    		n=n.next;
    }
    return -1; 
  }

  @Override
  public int lastIndexOf(Object obj)
  {
	  if(!linkedUTD) {
  		updateLinked();
	  }
	  ListNode n=tail.prev;
	  for(int i=numItems-1;i>=0;i--) {
	  		if(n.data.equals(obj)) {
	  			return i;
	  		}
	  		n=n.prev;
	  }
	  return -1; 
  }

  @Override
  public boolean removeAll(Collection<?> c)
  {
    E[] temp=(E[])c.toArray();
    for(int i=0;i<c.size();i++) {
    		remove(temp[i]);
    }
    return true; 
  }

  @Override
  public boolean retainAll(Collection<?> c)
  {
	E[] temp=(E[]) c.toArray();
	for(int j=0;j<c.size();j++) {
		if(!this.contains(temp[j])) {
			this.remove(temp[j]);
		}
	}
	return true;
  }

  @Override
  public Object[] toArray()
  {
	if(!arrayUTD) {
		updateArray();
	}
    return theArray; 
  }
  
  
  /**
   * In here you are allowed to use only 
   * java.util.Arrays.copyOf method.
   */
  @Override
  public <T> T[] toArray(T[] arr)
  {
//	  return java.util.Arrays.copyOf((T[])theArray, this.size());
	    if(!arrayUTD) {
	    		updateArray();
	    }
	    if(arr.length < numItems)
	    {
		    T[] temp = (T[]) new Object[numItems];
		    temp = (T[])java.util.Arrays.copyOf(theArray, numItems);
		    return temp;
	    }else {
		    	arr = (T[]) java.util.Arrays.copyOf(arr, numItems);
		    	return arr;
	    }

  }

  @Override
  public List<E> subList(int fromPos, int toPos)
  {
    throw new UnsupportedOperationException();
  }

  private class AdaptiveListIterator implements ListIterator<E>
  {
    private int    index;  // index of next node;
    private ListNode cur;  // node at index - 1
    private ListNode last; // node last visited by next() or previous()

    public AdaptiveListIterator()
    {
      if ( ! linkedUTD ) updateLinked();
      cur=head;
      index=0;
    }
    public AdaptiveListIterator(int pos)
    {
    	 	if(pos>=0 && pos<=numItems+1) {
		      if ( ! linkedUTD ) updateLinked();
		      for(int i=0;i<pos;i++) {
		    	  	cur=cur.next;
		      }
		      index=pos+1;
    	 	}
    	 	else {
    	 		throw new IndexOutOfBoundsException();
    	 	}
    }

    @Override
    public boolean hasNext()
    {
      return	 (cur.next !=tail); 
    }

    @Override
    public E next()
    {
    	 if(hasNext()) {
    	     last=cur;
    		 cur=cur.next;
	     index++;
	     return cur.data; 
    	  }else {
    		return null;
    	  }
    } 

    @Override
    public boolean hasPrevious()
    {
      return (cur.prev !=null); 
    }

    @Override
    public E previous()
    {
   	 if(hasPrevious()) {
   		 E temp=cur.data;
	     last=cur;
		 cur=cur.prev;
		 index--;
		 return temp; 
	  }else {
		return null;
	  } 
    }
    
    @Override
    public int nextIndex()
    {
      return index; 
    }

    @Override
    public int previousIndex()
    {
      // TODO
      return index-1; 
    }

    @Override
    public void remove()
    {
	    	if(!linkedUTD) {
	    		updateLinked();
	    	}
	    last.data=head.data;
	    head=head.next;
	    arrayUTD=false;
    }

    @Override
    public void add(E obj)
    { 
    		if(obj!=null) {
    			ListNode temp=new ListNode(obj);
    			temp.prev=cur;
    			temp.next=cur.next;
    			cur.next.prev=temp;
    			cur.next=temp;
    		}
    		
    } 

    @Override
    public void set(E obj)
    {
    		if(obj!=null) {
    			last.data=obj;
    		}else {
    			throw new NullPointerException();
    		}
    }
  } // AdaptiveListIterator
  
  @Override
  public boolean equals(Object obj)
  {
    if ( ! linkedUTD ) updateLinked();
    if ( (obj == null) || ! ( obj instanceof List<?> ) )
      return false;
    List<?> list = (List<?>) obj;
    if ( list.size() != numItems ) return false;
    Iterator<?> iter = list.iterator();
    for ( ListNode tmp = head.next; tmp != tail; tmp = tmp.next )
    {
      if ( ! iter.hasNext() ) return false;
      Object t = iter.next();
      if ( ! (t == tmp.data || t != null && t.equals(tmp.data) ) )
         return false;
    }
    if ( iter.hasNext() ) return false;
    return true;
  }

  @Override
  public Iterator<E> iterator()
  {
    return new AdaptiveListIterator();
  }

  @Override
  public ListIterator<E> listIterator()
  {
    return new AdaptiveListIterator();
  }

  @Override
  public ListIterator<E> listIterator(int pos)
  {
    checkIndex2(pos);
    return new AdaptiveListIterator(pos);
  }

  // Adopted from the List<E> interface.
  @Override
  public int hashCode()
  {
    if ( ! linkedUTD ) updateLinked();
    int hashCode = 1;
    for ( E e : this )
       hashCode = 31 * hashCode + ( e == null ? 0 : e.hashCode() );
    return hashCode;
  }

  // You should use the toString*() methods to see if your code works as expected.
  @Override
  public String toString()
  {
   // Other options System.lineSeparator or
   // String.format with %n token...
   // Not making data field.
   String eol = System.getProperty("line.separator");
   return toStringArray() + eol + toStringLinked();
  }

  public String toStringArray()
  {
    String eol = System.getProperty("line.separator");
    StringBuilder strb = new StringBuilder();
    strb.append("A sequence of items from the most recent array:" + eol );
    strb.append('[');
    if ( theArray != null )
      for ( int j = 0; j < theArray.length; )
      {
        if ( theArray[j] != null )
           strb.append( theArray[j].toString() );
        else
           strb.append("-");
        j++;
        if ( j < theArray.length )
           strb.append(", ");
      }
    strb.append(']');
    return strb.toString();
  }

  public String toStringLinked()
  {
    return toStringLinked(null);
  }

  // iter can be null.
  public String toStringLinked(ListIterator<E> iter)
  {
    int cnt = 0;
    int loc = iter == null? -1 : iter.nextIndex();

    String eol = System.getProperty("line.separator");
    StringBuilder strb = new StringBuilder();
    strb.append("A sequence of items from the most recent linked list:" + eol );
    strb.append('(');
    for ( ListNode cur = head.next; cur != tail; )
    {
      if ( cur.data != null )
      {
        if ( loc == cnt )
        {
          strb.append("| ");
          loc = -1;
        }
        strb.append(cur.data.toString());
        cnt++;

        if ( loc == numItems && cnt == numItems )
        {
          strb.append(" |");
          loc = -1;
        }
      }
      else
         strb.append("-");
      
      cur = cur.next;
      if ( cur != tail )
         strb.append(", ");
    }
    strb.append(')');
    return strb.toString();
  }
}
