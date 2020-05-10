
package edu.iastate.cs228.hw08;



import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
/**
 * 
 * 
 * A class that implements the ADT dictionary by using a chain of nodes.
 * The dictionary is unsorted and has distinct search keys, i.e., can have 
 * duplicate values, however, those are differentiated based on their keys.
 * 
 * @author
 * Amith Kopparapu Venkata Boja
 * NOTEs and REQUIREMENTs:
 * 
 * Exactly same as the ones listed for SortedVectorDictionary class.
 * 
 * In addition to above ANSWER the following 6 QUESTIONS, inside these 
 * comments right below each question. Figures needed to answer questions
 * 3, 4, and 5 are shown on Canvas under description of HW08.
 * 
 * =========================================================================
 * Q1. (a) What is the height of the shortest binary tree that contains 22
 *     nodes? (b) Is this tree full? (c) Is it balanced?
 *     
 * A1. (a)5
 *     (b)No
 *     (c)Yes
 * =========================================================================
 * Q2. Consider a binary tree that has four levels.
 *     (a) What is the maximum number of nodes in this tree?
 *     (b) What is the maximum number of leaves in this tree?
 *     
 * A2. (a)15
 *     (b)8    
 * =========================================================================
 * Q3. Consider a traversal of a binary tree, which contains Integer data. 
 *     Suppose that visiting a node means to simply display the data in the 
 *     node. What are the results of each of the following traversals of the 
 *     binary tree shown in Figure 1.
 *     (a) Preorder
 *     (b) Postorder
 *     (c) Inorder
 *     (d) Level order
 *     
 * A3. (a)6 4 2 1 3 5 8 7 9 10 11
 *     (b)1 3 2 5 4 9 7 11 10 8 6
 *     (c)1 2 3 4 5 6 7 9 8 10 11
 *     (d)6 4 8 2 5 7 10 1 3 9 11
 *     
 * =========================================================================
 * Q4. Repeat Q3 but for the binary tree shwn in Figure 2. 
 * A4. (a)11 8 3 2 1 5 4 6 10 9 7
 *     (b)2 1 3 4 6 5 8 9 7 10 11
 *     (c)2 3 1 8 4 5 6 11 9 10 7
 *     (d)11 8 10 3 5 9 7 2 1 4 6
 *  
 * =========================================================================
 * Q5. The two binary trees shown in Figures 1 and 2 contain Integer data.
 *     (a) Is the tree in Figure 1 a binary search tree? Why or why not?
 *     (b) Is the tree in Figure 2 a maxheap? Why or why not?
 *  
 * A5. (a)Yes, because all the the nodes in the subtree to the left of a node is 
 * 		  less than the value of the current node and all the nodes in the right
 * 		  subtree are greater than the value of the current node.
 *     (b)No, because 11>10 where 11 is the value of the parent and 10 is its
 *     	  its child in the right subtree.
 *     
 * =========================================================================
 * Q6. Can a binary search tree ever be a maxheap? Explain.
 * A6. No, because the nodes of the right subtree for a node is always greater than 
 * 	   the value of the current node, but a maxheap requires all its children to be less
 * 	   than itself.                           
 *     
 *     
 *     
 *     
 *     
 *     
 */
public class LinkedDictionary<K, V> implements DictionaryInterface<K, V>
{
 private Node firstNode;   // Reference to first node of chain
 private int  numberOfEntries; 
	
 public LinkedDictionary()
 {
  firstNode = null;
  numberOfEntries = 0;
 }
 /**
  * A private helper method used to check whether the given key k exists in the linked dictionary.
  * @param k
  * 		the key to look for
  * @return 
  * 		an integer value of the location of the specified key
  */
 private int locateIndex(K k) {
	 int i=0;
	 Node n=firstNode;
	 while(i<numberOfEntries && !k.equals(n.getKey())) {
		 i++;
		 n=n.next;
	 }
	 return i;
 }
 /**
  * gives the last node of the linked dictionary
  * @return
  * 		a node the refers to the last node of the linked dictionary
  */
 private Node getLastNode() {
	 Node temp=firstNode;
	 int i=0;
	  while(i<numberOfEntries-1) {
		  temp=temp.next;
		  i++;
	  }
	  return temp;
 }
 public V add(K key, V value)
 {
  if(Objects.isNull(key) || Objects.isNull(value)) {
	throw new IllegalArgumentException();
 }else{
	  V result=null;
	  Node n=firstNode;
	  int index=locateIndex(key);
	  if(isEmpty()) {
		  firstNode=new Node(key,value);
		  numberOfEntries++;
	  }
	  else if(index<numberOfEntries) {
		  for(int i=0;i<index;i++) {
			  n=n.next;
		  }
		  result=n.getValue();
		  n.setValue(value);
	  }else {
		  Node temp=getLastNode();
		  Node newNode=new Node(key,value);
		  temp.next=newNode;
		  numberOfEntries++;
	  }
	  return result;
  }
 }

 public V remove(K key)
 {
  if(Objects.isNull(key)) {
	throw new IllegalArgumentException();
  }else {
	  V result = null;
	  Node n=firstNode;
	  int index=locateIndex(key);
	  if( index< numberOfEntries) {
		  for(int i=0;i<index;i++) {
			  n=n.next;
		  }
		  result=n.getValue();
		  Node temp=getLastNode();
		  n.setValue(temp.getValue());
		  n.key=temp.getKey();
		  temp.setValue(null);
		  temp.key=null;
		  numberOfEntries--;
	  }
	  return result;  
  }
 }

 public V getValue(K key)
 {
  if(Objects.isNull(key)) {
	throw new IllegalArgumentException();
  }else {
	  Node n=firstNode;
	  int index=locateIndex(key);
	  if(index==0 && !firstNode.getKey().equals(key)) {
		  return null;
	  }else {
		  for(int i=0;i<index;i++) {
			  n=n.next;
		  }
		  return n.getValue();
	  }
  }
 }

 public boolean contains(K key)
 {
  if(Objects.isNull(key)) {
	throw new IllegalArgumentException();
  }else {
	  int index=locateIndex(key);
	  return !((index==0 && !firstNode.getKey().equals(key))||index==numberOfEntries);

  }
 }

 public boolean isEmpty()
 {
  return (numberOfEntries==0);
 }
	
 public int getSize()
 {
  return numberOfEntries;
 }

 public void clear()
 {
	 firstNode=null;
	 numberOfEntries=0;
 }

 // Needs to output String representation in exact same
 // format as the one done by SortedVectorDictionary.
 public String toString()
 {
  String str="[";
  Node n=firstNode;
  int i=0;
  while(i<numberOfEntries-1) {
	  str=str+n.toString()+", ";
	  n=n.next;
	  i++;
  }
  str = str+ n.toString();
  return str+"]";
 }

 public Iterator<K> getKeyIterator()
 {
  return new KeyIterator();
 }
	
 public Iterator<V> getValueIterator()
 {
  return new ValueIterator();
 }

 private class KeyIterator implements Iterator<K>
 {
  private Node nextNode;
		
  private KeyIterator()
  {
   nextNode=firstNode;
  }
		
  public boolean hasNext() 
  {
   return (nextNode !=null);
  }
		
  public K next()
  {
   K result=null;
   if(hasNext()) {
	   result=nextNode.getKey();
	   nextNode=nextNode.next;
	   return result;
   }else {
	   throw new NoSuchElementException();
   }

  }
 } 
	
 private class ValueIterator implements Iterator<V>
 {
  private Node nextNode;
  		
  private ValueIterator()
  {
   nextNode=firstNode;
  }
		
  public boolean hasNext() 
  {
	 return (nextNode !=null);
  }
		
  public V next()
  {
	  V result=null;
	   if(hasNext()) {
		   result=nextNode.getValue();
		   nextNode=nextNode.next;
		   return result;
	   }else {
		   throw new NoSuchElementException();
	   }
  }
 }

 private class Node
 {
  private K key;
  private V value;
  private Node next;

  private Node(K searchKey, V dataValue)
  {
   key = searchKey;
   value = dataValue;
   next = null;	
  }
		
  private Node(K searchKey, V dataValue, Node nextNode)
  {
   key = searchKey;
   value = dataValue;
   next = nextNode;	
  }
		
  private K getKey()
  {
   return key;
  }
		
  private V getValue()
  {
   return value;
  }

  private void setValue(V newValue)
  {
   value = newValue;
  }

  private Node getNextNode()
  {
   return next;
  }
		
  private void setNextNode(Node nextNode)
  {
   next = nextNode;
  }
  
  public String toString()
  {
   return "("+key+":"+value+")";	
  }
 }
}

		
