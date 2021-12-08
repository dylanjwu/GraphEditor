package application.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Dylan Wu
 * CS5010 v1 Fall 2021 - Final Project
 *  
 */

public class DefaultGraph implements Graph {
	private List<Vertex> adjList;

	public DefaultGraph() {
		adjList = new ArrayList<>();
	}

	@Override
	public void addVertex(Vertex v) {
		if (!adjList.contains(v)) {
			adjList.add(v);
		}
	}

	@Override
	public void removeVertex(Vertex v) {
		for (Vertex u : adjList) {
			u.removeEdge(v);
		}
		adjList.remove(v);
		System.out.println("Vertex " + v + " deleted from graph");
	}

	@Override
	public void clear() {
		adjList = new ArrayList<>();
	}

	@Override
	public void addEdge(Vertex u, Vertex v) {
		if (!u.hasEdge(v)) {
			u.addEdge(v);
		}
	}

	@Override
	public List<Vertex> getVertices() {
		return null;
	}

	@Override
	public void removeEdge(Vertex u, Vertex v) {
		u.removeEdge(v);
	}

	@Override
	public String toString() {
		return "DefaultGraph [adjList=" + adjList + "]";
	}

}

class WeightedGraph implements Graph {
	
	private Graph graph;
	public WeightedGraph(Graph graph) {
		this.graph = graph;
	}

	@Override
	public void addVertex(Vertex v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeVertex(Vertex v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addEdge(Vertex u, Vertex v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEdge(Vertex u, Vertex v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Vertex> getVertices() {
		// TODO Auto-generated method stub
		return null;
	}
	
}