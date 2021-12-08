package application.model;

import java.util.List;
import javafx.util.Pair;

/**
 * 
 * @author Dylan Wu
 * CS5010 v1 Fall 2021 - Final Project
 *  
 */


public class WeightedGraphVertex implements Vertex {
	
	List<Pair<Vertex, Integer>> weightedEdges;
	private Vertex wrappedVertex;

	public WeightedGraphVertex(Vertex vertex) {
		this.wrappedVertex = vertex;
		/** set unweighted edges to 1 as default */
		for (Vertex u : vertex.edges()) {
			weightedEdges.add(new Pair<Vertex, Integer>(u, 1));
		}
	}

	@Override
	public boolean hasEdge(Vertex v) {
		return wrappedVertex.hasEdge(v);
	}

	@Override
	public void removeEdge(Vertex v) {
		wrappedVertex.removeEdge(v);
	}

	@Override
	public void addEdge(Vertex v) {
		weightedEdges.add(new Pair<Vertex, Integer>(v, 1));
	}

	@Override
	public List<Vertex> edges() {
		return wrappedVertex.edges();
	}

	@Override
	public int getId() {
		return wrappedVertex.getId();
	}
	
	public void addEdge(Vertex v, Integer weight) {
		weightedEdges.add(new Pair<Vertex, Integer>(v, weight));
	}
}