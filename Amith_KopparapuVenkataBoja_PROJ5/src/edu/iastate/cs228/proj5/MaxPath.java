package edu.iastate.cs228.proj5;
/*
 *  @author
	Amith Kopparapu Venkata Boja
 *  
 *  
 *  
 */

import java.util.HashMap;
import java.util.Stack;

public class MaxPath
{
  /**
   *
   * If G is null or maxPath is null, then it throws IllegalArgumentException
   * with the message "null arguments". If maxPath is not empty, then it 
   * throws IllegalArgumentException with the message "maxPath is not empty".
   *
   * This method calls depthFirstSearch(...) in the class DFS on G, and saves 
   * the reference to a stack of vertices from depthFirstSearch(...) into the 
   * variable, say topoOrder, of type Stack<V>. If topoOrder is null, then it 
   * throws IllegalArgumentException with the message "The graph has a cycle". 
   * If topoOrder is empty, then it throws IllegalStateException with the 
   * message "topoOrder is empty".
   *
   * Then it creates a dist map and a pred map (see lecture code on Dijkstra's
   * algorithm under week 13 of Lecture Notes). And it sets the value to 0 
   * for each vertex in the dist map, 
   * and sets the value to null for each vertex in the pred map.
   *
   * For each vertex u in the stack topoOrder, removes u from the stack, and 
   * for each edge from u to v, if the cost of the path to v via vertex u is 
   * larger than the current cost of v (given by dist.get(v)), then updates 
   * the current cost of v with the larger cost and sets the value of v to u 
   * in the pred map (see lecture code on Dijkstra's algorithm, i.e., 
   * DiGraph.java).
   *
   * Let variable, say score, of type Integer be the maximum distance of any 
   * path seen so far and let variable, say end, of type V be the ending vertex 
   * of a path with the distance score. Initially, score is set to 0 and end to 
   * null. Whenever the distance of a new path ending at vertex v is larger than
   * score, this method, sets score to the larger distance and sets end to v.
   *
   * At the end of this method, score is the maximum distance of all paths in
   * the graph and a path with this maximum distance ends at the vertex end.
   * Uses the pred map to generate each vertex in this path in reverse order,
   * starting at the vertex end, and places the vertices in the stack maxPath
   * with the stack top being the first vertex in this path. Note that the pred
   * value for the first vertex is null.
   * 
   * 
   */
  public static <V> Integer findMaxPath(DiGraph<V> G, Stack<V> maxPath)
  {
	if(G!=null && maxPath!=null) {
		if(!maxPath.isEmpty()) {
			throw new IllegalArgumentException("maxPath is not empty");
		}else {
			Stack<V> topoOrder = DFS.depthFirstSearch(G);
			if(topoOrder==null)
				throw new IllegalArgumentException("The graph has a cycle");
			if(topoOrder.isEmpty())
				throw new IllegalStateException("topoOrder is empty");
			HashMap<V, Integer> dist = new HashMap<>();
			HashMap<V, V> pred = new HashMap<>();
			for(V temp:topoOrder) {
				dist.put(temp, 0);
				pred.put(temp, null);
			}
			Integer score=Integer.MIN_VALUE;
			V end=null;
			while(!topoOrder.isEmpty()) {
				V u=topoOrder.pop();
				for(Edge<V,Integer> v:G.adjacentTo(u)) {
					int uDistance=dist.get(u);
					int vDistance=dist.get(v.getVertex());
					int path=uDistance+v.getCost();
					if(vDistance<path) {
						dist.put(v.getVertex(), path);
						pred.put(v.getVertex(), u);
						vDistance+=v.getCost();
					}
					if(vDistance>score) {
						score=vDistance;
						end=v.getVertex();					
					}
				}
			}
			while(pred.get(end) !=null) {
				maxPath.push(end);
				end=pred.get(end);
			}
			maxPath.push(end);
			return score+1;
		}
	}else {
		throw new IllegalArgumentException("null arguments");
	}
  }
}
