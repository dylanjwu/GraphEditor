package application.model;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
	private List<Edge> inEdges;
	private List<Edge> outEdges;

	private static int idCount = 0;
	private int id;
	public Vertex() {
		this.inEdges = new ArrayList<>();
		this.outEdges = new ArrayList<>();
		this.id = idCount++;
	}

	public void removeEdge(Edge edge) {
		inEdges.removeIf(e -> e.equals(edge));
	}

	public void addInEdge(Edge edge) { 
		inEdges.add(edge);
	}
	
	public void addOutEdge(Edge edge) {
		outEdges.add(edge);
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
