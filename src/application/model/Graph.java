package application.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.shape.Line;


class Vertex {
	private List<Edge> inEdges;
	private List<Edge> outEdges;

	private static int idCount = 0;
	private int id;
	public Vertex() {
		this.inEdges = new ArrayList<>();
		this.outEdges = new ArrayList<>();
		this.id = idCount++;
	}
	public List<Edge> getInEdges() {
		return inEdges;
	}
	public List<Edge> getOutEdges() {
		return outEdges;
	}
	public int getId() {
		return id;
	}
}

public interface Graph {
	void addVertex(Vertex v);
	void removeVertex(Vertex v);
	void clear();
	void addEdge(Vertex u, Vertex v);
	List<Vertex> getVertices();
}

abstract class AbstractGraph implements Graph {
	protected Map<Vertex, List<Edge>> adjList;
	
	public void addVertex(Vertex v) {
		if (adjList.get(v) == null) {
			adjList.put(v, new ArrayList<>());
		}
	}
	
	public void removeVertex(Vertex v) {
		
			adjList.remove(v);
			System.out.println("Vertex " + v + " deleted from graph");
	}
}
//
//
//class UndirectedGraph implements Graph {
//
//
//}




