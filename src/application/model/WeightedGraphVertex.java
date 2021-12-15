package application.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.util.Pair;

/**
 * Decorator class to wrap DefaultVertex into Weighted graph vertex; used by WeightedGraph
 * 
 * @author Dylan Wu
 * CS5010 v1 Fall 2021 - Final Project
 *  
 */


public class WeightedGraphVertex implements Vertex {
	
	private List<Pair<WeightedGraphVertex, Integer>> weightedEdges;
	private Vertex wrappedVertex;

	public WeightedGraphVertex(Vertex vertex) {
		weightedEdges = new ArrayList<>();
		this.wrappedVertex = vertex;
		/** set unweighted edges to 1 as default */
		for (Vertex u : vertex.edges()) {
			weightedEdges.add(new Pair<>(new WeightedGraphVertex(u), 1));
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
		weightedEdges.add(new Pair<>(new WeightedGraphVertex(v), 1));
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
		weightedEdges.add(new Pair<>(new WeightedGraphVertex(v), weight));
	}

	public List<Pair<WeightedGraphVertex, Integer>> weightedEdges() {
		return weightedEdges;
	}

	@Override
	public String toString() {
		return "WeightedGraphVertex [weightedEdges=" + weightedEdges + ", wrappedVertex=" + wrappedVertex + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(weightedEdges, wrappedVertex);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WeightedGraphVertex other = (WeightedGraphVertex) obj;
		return Objects.equals(weightedEdges, other.weightedEdges) && Objects.equals(wrappedVertex, other.wrappedVertex);
	}

}