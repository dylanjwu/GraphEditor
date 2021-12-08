package application.model;

import java.util.List;

/**
 * 
 * @author Dylan Wu
 * CS5010 v1 Fall 2021 - Final Project
 *  
 */

public interface Graph {
	void addVertex(Vertex v);
	void removeVertex(Vertex v);
	void clear();
	void addEdge(Vertex u, Vertex v);
	void removeEdge(Vertex u, Vertex v);
	List<Vertex> getVertices();
}


//abstract class AbstractGraph implements Graph {
//	protected Set<Vertex> adjList;
//
//	public void addVertex(Vertex v) {
//		if (!adjList.contains(v)) {
//			adjList.add(v);
//		}
//	}
//	
//	public void removeVertex(Vertex v) {
//		for (Vertex u : adjList) {
//			adjList.get(u).removeIf(el -> el.equals(v));
//		}
//		adjList.remove(v);
//		System.out.println("Vertex " + v + " deleted from graph");
//	}
//}
//
//
//class UndirectedGraph implements Graph {
//
//
//}




