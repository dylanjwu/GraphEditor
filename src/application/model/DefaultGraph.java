package application.model;

import java.util.ArrayList;
import java.util.List;

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
			u.getInEdges().removeIf(edge -> edge.getDst().equals(v));
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
		u.addOutEdge(new DefaultEdge(u, v));
	}

	@Override
	public List<Vertex> getVertices() {
		// TODO Auto-generated method stub
		return null;
	}
	
}