package edu.iastate.cs228.proj4;

import java.util.LinkedList;

/**
 * 
 * @author 
 *Amith Kopparapu Venkata Boja
 *
 * An entry tree class.
 *
 *
 */
public class EntryTree<K, V> 
{
 // Dummy root node. 
 // Made public for grading.
 public Node root;

 /**
  * 
  * You are allowed to add at most TWO more data fields to 
  * EntryTree class of int type ONLY if you need to.
  *  
  */
 
 // All made public for grading.
 public class Node implements EntryNode<K, V> 
 {
  public Node child; // reference to the first child node
  public Node parent; // reference to the parent node
  public Node prev; // reference to the previous sibling
  public Node next; // reference to the next sibling
  public K key; // the key for this node
  public V value; // the value at this node

  public Node(K aKey, V aValue) 
  {
   key = aKey;
   value = aValue;
   child = null;
   parent = null;
   prev = null;
   next = null;
  }

  @Override
  public EntryNode<K, V> parent() 
  {
   // TODO
   return parent;
  }

  @Override
  public EntryNode<K, V> child() 
  {
   // TODO
   return child;
  }

  @Override
  public EntryNode<K, V> next() 
  {
   // TODO
   return next;
  }

  @Override
  public EntryNode<K, V> prev() 
  {
   // TODO
   return prev;
  }

  @Override
  public K key() 
  {
   // TODO
   return key;
  }

  @Override
  public V value() 
  {
   // TODO
   return value;
  }
 }

 public EntryTree() 
 {
  root = new Node(null, null);
  
 }

 /**
  * Returns the value of the entry with a specified key sequence, 
  * or {@code null} if this tree contains no entry with this key 
  * sequence.
  * 
  * This method returns {@code null} if {@code keyarr} is null or 
  * if its length is {@code 0}. If any element of {@code keyarr} 
  * is {@code null}, then the method throws a 
  * {@code NullPointerException}. The method returns the value of 
  * the entry with the key sequence in {@code keyarr} or {@code null} 
  * if this tree contains no entry with this key sequence. An example 
  * is given in provided sample input and output files to illustrate 
  * this method.  
  *
  * @param keyarr Read description.
  * @return Read description.
  * @throws NullPointerException Read description.
  */
 public V search(K[] keyarr) 
 {
  if(keyarr==null || keyarr.length==0) {
	  return null;
  }else {
	  Node n=root;
	  for(int i=0;i<keyarr.length;i++) {
			n=findNode(n,keyarr[i]);
			if(n==null) {
				return null;
			}
	  }
	  return n.value();
  }
 }

 /**
  * 
  * This method returns an array of type {@code K[]} with the longest 
  * prefix of the key sequence specified in {@code keyarr} such that 
  * the keys in the prefix label the nodes on the path from the root 
  * to a node. The length of the returned array is the length of the 
  * longest prefix.
  * 
  * This method returns {@code null} if {@code keyarr} is {@code null}, 
  * or if its length is {@code 0}. If any element of {@code keyarr} is
  * {@code null}, then the method throws a {@code NullPointerException}. 
  * A prefix of the array {@code keyarr} is a key sequence in the subarray 
  * of {@code keyarr} from index {@code 0} to any index {@code m>=0}, 
  * i.e., greater than or equal to; the corresponding suffix is a key
  * sequence in the subarray of {@code keyarr} from index {@code m+1} to
  * index {@code keyarr.length-1}. The method returns an array of type
  * {@code K[]} with the longest prefix of the key sequence specified in
  * {@code keyarr} such that the keys in the prefix are, respectively,
  * with the nodes on the path from the root to a node. The lngth of the
  * returned array is the length of the longest prefix. Note that if the
  * length of the longest prefix is {@code 0}, then the method returns 
  * {@code null}. This method can be used to select a shorted key sequence
  * for an {@code add} command to create a shorter path of nodes in the
  * tree. An example is given in the attachment to illustrate how this
  * method is used with the {@code #add(K[] keyarr, V aValue)} method.  
  * 
  * NOTE: In this method you are allowed to use 
  * {@link java.util.Arrays}'s {@code copyOf} method only.
  * 
  * @param keyarr Read description.
  * @return Read description.
  * @throws NullPointerException Read description.
  */
 public K[] prefix(K[] keyarr) 
 {
	 if(keyarr==null || keyarr.length==0) {
		  return null;
	  }else {
		 K[] arr=(K[])new Object[keyarr.length];
		 int j=0;
		 Node n=root;
		 for(int i=0;i<keyarr.length;i++) {
			  if (n != null && keyarr[i] != null) {
					n = (Node)n.child();
					while(n != null) {
						if(n.key().equals(keyarr[i])) {
							arr[j]=keyarr[i];
							j++;
							break;	
						}
						n = (Node)n.next();	
					}
					if(n==null) {
						break;
					}
				}else {
					throw new NullPointerException();
				}
		  }
		  K[] temp=(K[])new Object[j];
		  for(int k=0;k<temp.length;k++) {
			  temp[k]=arr[k];
		  }
		  return temp;
	  }
	  
 }

 /**
  * 
  * This method returns {@code false} if {@code keyarr} is {@code null}, 
  * its length is {@code 0}, or {@code aValue} is {@code null}. If any 
  * element of {@code keyarr} is {@code null}, then the method throws a
  * {@code NullPointerException}.
  * 
  * This method locates the node {@code P} corresponding to the longest 
  * prefix of the key sequence specified in {@code keyarr} such that the 
  * keys in the prefix label the nodes on the path from the root to the node. 
  * If the length of the prefix is equal to the length of {@code keyarr}, 
  * then the method places {@code aValue} at the node {@code P} (in place of 
  * its old value) and returns {@code true}. Otherwise, the method creates a 
  * new path of nodes (starting at a node {@code S}) labelled by the 
  * corresponding suffix for the prefix, connects the prefix path and suffix 
  * path together by making the node {@code S} a child of the node {@code P}, 
  * and returns {@code true}. An example input and output files illustrate 
  * this method's operation.
  *
  * NOTE: In this method you are allowed to use 
  * {@link java.util.Arrays}'s {@code copyOf} method only.
  * 
  * @param keyarr Read description.
  * @param Read description.
  * @return Read description.
  * @throws NullPointerException Read description.
  */
 public boolean add(K[] keyarr, V aValue){
		if (keyarr != null && keyarr.length != 0 && aValue != null) {
			Node n = root;
			Node c;		
			for (int i = 0; i < keyarr.length; i++) {
				c=findNode(n,keyarr[i]);
				if (c == null) {						
					c = new Node(keyarr[i], null);		
					c.parent = n;					
					if(n.child() != null) {	
						Node temp = n.child;	
						while (temp.next != null) {		
							temp = temp.next;
						}
						temp.next = c;				
						c.prev = temp;			
					}else {									
						n.child = c;	
					}
				}
				n = c;							
			}
			n.value = aValue;							
			return true;	
		}else {
			return false;
		}
	}
 /**
  * Removes the entry for a key sequence from this tree and returns its value
  * if it is present. Otherwise, it makes no change to the tree and returns
  * {@code null}.
  * 
  * This method returns {@code null} if {@code keyarr} is {@code null} or its
  * length is {@code 0}. If any element of {@code keyarr} is {@code null}, then
  * the method throws a {@code NullPointerException}. The method returns 
  * {@code null} if the tree contains no entry with the key sequence specified
  * in {@code keyarr}. Otherwise, the method finds the path with the key sequence,
  * saves the value field of the node at the end of the path, sets the value field
  * to {@code null}.
  * 
  * The following rules are used to decide whether the current node and higher
  * nodes on the path need to be removed. The root cannot be removed. Any node 
  * whose value is not {@code null} cannot be removed. Consider a non-root node 
  * whose value is {@code null}. If the node is a leaf node (has no children),
  * then the node is removed. Otherwise, if the node is the parent of a single
  * child and the child node is removed, then the node is removed. Finally, the
  * method returns the saved old value.
  * 
  * 
  * @param keyarr Read description.
  * @return Read description.
  * @throws NullPointerException Read description.
  *   
  */
 public V remove(K[] keyarr) {
		Node n = root;
		V val = null;
		if (keyarr != null && keyarr.length != 0) {
			for (int i = 0; i < keyarr.length; i++) {		
				n = findNode(n, keyarr[i]);	
				if (n == null) {						
					return null;							
				}
			}
			val = n.value();						
			n.value = null;							
			removeVals(n);							
			return val;	
		}else {
			return null;
		}
}
	
	/**
	 * An private helper method used to remove all the appropriate nodes when trying to remove the given node
	 * 
	 * @param n	
			The node that needs to be removed without a trace
	 */
	private void removeVals(Node n) {
		if(n != null && n.value==null && n.child==null) {
				if(n.parent != null && n.parent.child.next == null) {
					n.parent.child = null;			
					removeVals(n.parent);			
				}else {		
					if(n.next != null) {
						n.next.prev = n.prev;
					}
					if(n.prev != null) {				
						n.prev.next = n.next;
					}
				}
		}
	}

 /**
  * 
  * This method prints the tree on the console in the output format 
  * shown in provided sample output file. If the tree has no entry, 
  * then the method just prints out the line for the dummy root node.
  * 
  */
 public void showTree() {
	 	System.out.println(treeToString(root,0));
 }

	/**
	 *  A private helper method used to print the values in the tree
	 * @param n	
	 * 		The node used to print itself and its siblings
	 * @param height		
	 * 		current depth in the tree
	 * @return temp		
	 * 		the final string value that needs to be printed
	 */
	private String treeToString(Node n, int height) {
		if(n != null) {
			String temp = "";
			while(n != null) {
				
				for (int i = 0; i < height; i++) {
					if(i==0) {
						temp+="....";
					}
					temp += "..";
				}
				temp += (n.key() + "::" + n.value() + "\n");
				temp += treeToString(n.child, height+1);
				n = n.next;
			}
			if(root!=null) {
				temp=temp.substring(0, temp.length());
			}
			return temp;
		}else {
			return null;
		}
	}

 
 
 /**
  * 
  * Returns all values in this entry tree together with their keys.
  * The order of outputs would be similar to level order traversal,
  * i.e., first you would get all values together with their keys in
  * first level from left to right, then second level, and so on.
  * If tree has no values then it would return {@code null}.
  *
  * For the example image given in description, the 
  * returned String[][] would look as follows:
  * 
  *  {{"IA","Grow"}, {"ISU","CS228"}}   
  * 
  * NOTE: In this method you are allowed to use 
  * {@link java.util.LinkedList}.
  * 
  *  
  */	
 public String[][] getAll()
 {
	 if(root.child==null) {
		 return null;
	 }else {
		LinkedList<String>vals=helper(root.child);
		String[][] returnVals=new String[vals.size()][vals.size()];
		for(int i=0;i<vals.size();i++) {
			String temp=vals.get(i);
			String[] contents=temp.split("\\s+");
			returnVals[0][0]=temp;
		}
		
	 }
	 return null;
 }
 /**
  * A private helper method used to build a linked list for the tree
  * @param n
  * 		The node that needs to converted to a linked list
  * @return
  * 		a linked list format of the tree
  */
 private  LinkedList<String> helper(Node n) {
	 LinkedList<String> keys=new LinkedList<String>();
	 String str="";
	 while(n!=null) {
		 String temp=str+n.key;
		 if(n.value!=null) {
			 keys.add(temp+" "+n.value);
		 }
		 if(n.child!=null) {
			 helper(n.child);
		 }
		 n=n.next;
	 }
	 return keys;
 }
 /**
	 * Helper method to find the child of the specified node, using the specified key.
	 * 
	 * @param current Node we need to find the child of
	 * @param key	  Look for a child with this key
	 * @return node   If the child exists, return that, else return null
	 */
	private Node findNode(Node n, K k) {
		if (n!= null && k != null) {
			n = n.child;
			while(n != null) {			
				if(n.key().equals(k)) {	
					break;		
				}
				n = n.next;			
			}
			return n;
		}else {
			throw new NullPointerException();
		}
	}
}
