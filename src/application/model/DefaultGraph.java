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