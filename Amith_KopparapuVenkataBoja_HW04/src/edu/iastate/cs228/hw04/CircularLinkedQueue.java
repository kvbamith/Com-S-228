package edu.iastate.cs228.hw04;/** * Amith Kopparapu Venkata Boja * @author *  * NOTE:  * 0. Put your Firstname and Lastname after above author tag. * 	  Make sure that in both cases the first letter is uppercase *    and all others are lowercase (and space in between). * 1. You are allowed to create and use your own private helper methods.  * 2. No additional data fields can be introduced. * 3. No custom classes of your own can be introduced or used. * 4. Import statements are not allowed. * 5. Fully qualified class names usage is not allowed. * 6. You are allowed to reuse any part of the source codes provided *    or shown under lecture notes of week 5 (or before). * 7. You are not allowed to create arrays of objects and manipulate *    queue objects using arrays.    * * * * DESCRIPTION: * A class that implements the ADT queue by using a circular linked chain of nodes.  * For details of circular linked chain check slide number 28 of * "queueDequePriorityQueueImplementations_part2.pdf" file under lecture notes  * of Wednesday of Week 5 on Canvas. This slide shows a circular linked chain with  * an external reference to its last node that (a) shows a case when we have more than  * one node; (b) has one node; (c) is empty. *   *  */public class CircularLinkedQueue<T> implements QueueInterface<T>{	private Node lastNode; // References node for back of queue	public CircularLinkedQueue() 	{		lastNode = null;	}	public void enqueue(T newEntry) 	{		Node n=new Node(newEntry);		if(isEmpty()) {			lastNode=n;		}		n.setNextNode(lastNode.next);		lastNode.setNextNode(n);		lastNode=n;	}	public T dequeue() 	{		if(isEmpty()) {			throw new EmptyQueueException();		}		Node temp=lastNode.next;		lastNode.setNextNode(lastNode.next.next);		if(lastNode == temp) {			lastNode = null;		}		return temp.data;	}	public T getFront() 	{		return lastNode.next.data;	}	public boolean isEmpty() 	{		return (lastNode==null);	}	public void clear() 	{		lastNode=null;	}	private class Node	{		private T    data;  // Queue entry		private Node next;  // Link to next node		private Node(T dataPortion)		{			data = dataPortion;			next = null;			} // end constructor				private Node(T dataPortion, Node linkPortion)		{			data = dataPortion;			next = linkPortion;			} // end constructor		private T getData()		{			return data;		} // end getData		private void setData(T newData)		{			data = newData;		} // end setData		private Node getNextNode()		{			return next;		} // end getNextNode				private void setNextNode(Node nextNode)		{			next = nextNode;		} // end setNextNode	} // end Node} // end CircularLinkedQueue