package application.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Decorator class to wrap DefaultGraph into Weighted graph as a Graph; uses weighted graph vertices
 */

public class WeightedGraph implements Graph {

	private Graph wrapper;
	List<WeightedGraphVertex> adjList;

	public WeightedGraph(Graph graph) {
		wrapper = graph;
		adjList = new ArrayList<>();
		if (graph instanceof DefaultGraph) {
			for (Vertex v : graph.getVertices()) {
				adjList.add(new WeightedGraphVertex(v));
			}
		}
		else {
			adjList = ((WeightedGraph)graph).getWeightedVertices();
		}
	}

	@Override
	public Iterator<Vertex> iterator() {
		return wrapper.iterator();
	}

	@Override
	public void addVertex(Vertex v) {
		if (!adjList.contains(v)) {
			adjList.add(new WeightedGraphVertex(v));
		}
	}

	@Override
	public void removeVertex(Vertex v) {
		for (Vertex u : adjList) {
			u.removeEdge(v);
		}
		adjList.remove(v);
	}

	@Override
	public void clear() {
		adjList.clear();
	}

	@Override
	public void addEdge(Vertex u, Vertex v) {
		if (!u.hasEdge(v)) {
			wrapper.addEdge(u, v);
		}
	}

	@Override
	public void removeEdge(Vertex u, Vertex v) {
		u.removeEdge(v);
	}

	@Override
	public List<Vertex> getVertices() {
		return wrapper.getVertices();
	}
				
	
	public List<WeightedGraphVertex> getWeightedVertices() {
		return adjList;
	}
	
	public void addWeightedEdge(WeightedGraphVertex u, WeightedGraphVertex v, int weight) {
		if (!u.hasEdge(v)) {
			u.addEdge(v,weight);
		}
	}

	@Override
	public String toString() {
		return "WeightedGraph [wrapper=" + wrapper + ", adjList=" + adjList + "]";
	}

}
