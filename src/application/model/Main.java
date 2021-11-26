package application.model;

public class Main {

	public static void main(String[] args) {
		Graph graph = new DefaultGraph();
		Vertex v1 = new Vertex();
		Vertex v2 = new Vertex();

		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addEdge(v1, v2);

		graph.addEdge(v2, v1);
		graph.removeEdge(v1, v2);
		System.out.println(graph);
	}

}
