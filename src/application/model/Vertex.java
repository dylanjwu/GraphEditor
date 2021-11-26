package application.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class Vertex {
	private List<Vertex> edges;

	private static int idCount = 0;
	private int id;
	public Vertex() {
		this.edges = new ArrayList<>();
		this.id = idCount++;
	}

	public boolean hasEdge(Vertex v) {
		return edges.contains(v);
	}

	public void removeEdge(Vertex v) {
		edges.removeIf(e -> e.equals(v));
	}

	public void addEdge(Vertex v){ 
		edges.add(v);
	}
	
	public List<Vertex> edges() {
		return edges;
	}

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
		return Objects.equals(edges, other.edges) && id == other.id;
	}


}
