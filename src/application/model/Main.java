package application.model;

/**
 * 
 * @author Dylan Wu
 * CS5010 v1 Fall 2021 - Final Project
 *  
 */

public class Main {

	public static void main(String[] args) {
		Graph graph = new DefaultGraph();
		Vertex v1 = new DefaultVertex();
		Vertex v2 = new DefaultVertex();

		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addEdge(v1, v2);

		graph.addEdge(v2, v1);
		graph.removeEdge(v1, v2);
		System.out.println(graph);
	}

}
