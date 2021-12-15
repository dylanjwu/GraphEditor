package application.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * 
 * Basic, default graph implementation; wrapped by WeightedGraph
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
		return adjList;
	}

	@Override
	public void removeEdge(Vertex u, Vertex v) {
		u.removeEdge(v);
	}

	@Override
	public String toString() {
		return "DefaultGraph [adjList=" + adjList + "]";
	}

	@Override
	public Iterator<Vertex> iterator() {
		return new DFSIterator(adjList);
	}

	private class DFSIterator implements Iterator<Vertex> {
		private Set<Vertex> visited;
		private List<Vertex> adjList;
		
		private Stack<Vertex> stack;
		
		public DFSIterator(List<Vertex> adjList) {
			this.visited = new HashSet<>();
			this.adjList = adjList;
			this.stack = new Stack<>();
			
			/** perform dfs immediately, and by order to the stack */
			for (Vertex w : adjList) {
				if (!visited.contains(w)) {
					this.stack.push(w);
					boolean nothingAdded = false;
					while (!nothingAdded) {
						Vertex u = stack.peek();
						nothingAdded = true;
						for (Vertex v : u.edges()) {
							if (!visited.contains(v)) {
								stack.push(v);
								nothingAdded = false;
							}
						}
					}
				}
			}
		}

		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public Vertex next() {
			return stack.pop();
		}
		
	}
	
	
}
