package application.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 
 * @author Dylan Wu
 * CS5010 v1 Fall 2021 - Final Project
 *  
 */

public class DefaultVertex implements Vertex{
	private List<Vertex> edges;

	private static int idCount = 0;
	private int id;
	public DefaultVertex() {
		this.edges = new ArrayList<>();
		this.id = idCount++;
	}

	@Override
	public boolean hasEdge(Vertex v) {
		return edges.contains(v);
	}

	@Override
	public void removeEdge(Vertex v) {
		edges.removeIf(e -> e.equals(v));
	}

	@Override
	public void addEdge(Vertex v){ 
		if (!edges.contains(v))
			edges.add(v);
	}
	
	@Override
	public List<Vertex> edges() {
		return edges;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		String edgesId = edges.stream().map(edge -> String.valueOf(edge.getId())).collect(Collectors.joining(", "));
		return "Vertex [edges=" + edgesId + ", id=" + id + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(edges, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		/** PROBLEM HERE: here to fix, error is occuring when try to add an edge to a node that already has at least 2 edges*/
		//STACK overflow error, probably due to infinite recursion.
		return Objects.equals(edges, other.edges()) && id == other.getId();
	}

}