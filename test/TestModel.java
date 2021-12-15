import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import application.model.DefaultGraph;
import application.model.DefaultVertex;
import application.model.Graph;
import application.model.Vertex;
import application.model.WeightedGraph;
import application.model.WeightedGraphVertex;
import application.view.GraphNode;
import application.view.visitor.DFSVisitor;
import application.view.visitor.GraphVisitor;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class TestModel {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAddingNode() {
		Graph g = new DefaultGraph();
		Vertex vert1 = new DefaultVertex();
		Vertex vert2 = new DefaultVertex();

		g.addVertex(vert1);
		g.addVertex(vert2);
		assertEquals(g.getVertices().get(0), vert1);
		assertEquals(g.getVertices().get(1), vert2);
	}
	
	
	@Test
	public void testRemovingNode() {
		Graph g = new DefaultGraph();
		Vertex vert1 = new DefaultVertex();
		Vertex vert2 = new DefaultVertex();

		g.addVertex(vert1);
		g.addVertex(vert2);
		
		g.removeVertex(vert2);
		assertEquals(g.getVertices().get(0), vert1);
		assertEquals(g.getVertices().size(), 1);
	}

	@Test
	public void testAddingEdge() {
		Graph g = new DefaultGraph();
		Vertex vert1 = new DefaultVertex();
		Vertex vert2 = new DefaultVertex();

		g.addVertex(vert1);
		g.addVertex(vert2);

		g.addEdge(vert1, vert2);
		g.addEdge(vert2, vert1);
		
		assertEquals(true, g.getVertices().get(0).hasEdge(vert2));
		assertEquals(true, g.getVertices().get(1).hasEdge(vert1));
	}
	
	@Test
	public void testRemoveEdge() {
		Graph g = new DefaultGraph();
		Vertex vert1 = new DefaultVertex();
		Vertex vert2 = new DefaultVertex();

		g.addVertex(vert1);
		g.addVertex(vert2);

		g.addEdge(vert1, vert2);
		g.addEdge(vert2, vert1);
		
		assertEquals(true, g.getVertices().get(0).hasEdge(vert2));
		assertEquals(true, g.getVertices().get(1).hasEdge(vert1));
		
		g.removeVertex(vert2);
		assertEquals(g.getVertices().get(0), vert1);
		assertEquals(false, g.getVertices().get(0).hasEdge(vert2));
	}

	@Test
	public void testClear() {
		Graph g = new DefaultGraph();
		Vertex vert1 = new DefaultVertex();
		Vertex vert2 = new DefaultVertex();
		Vertex vert3 = new DefaultVertex();

		g.addVertex(vert1);
		g.addVertex(vert2);
		g.addVertex(vert3);

		g.addEdge(vert1, vert2);
		g.addEdge(vert2, vert1);
		g.addEdge(vert1, vert3);
		
		assertEquals(g.getVertices().size(), 3);
		g.clear();
		assertEquals(g.getVertices().size(), 0);
	}

	@Test
	public void testWeightedGraph() {
		Graph g = new DefaultGraph();
		Vertex vert1 = new DefaultVertex();
		Vertex vert2 = new DefaultVertex();
		Vertex vert3 = new DefaultVertex();
		g.addVertex(vert1);
		g.addVertex(vert2);
		g.addVertex(vert3);

		WeightedGraph wg = new WeightedGraph(g); /** Test decorator */
		
		wg.addWeightedEdge(new WeightedGraphVertex(vert1), new WeightedGraphVertex(vert2), 24);

		System.out.println(wg.getWeightedVertices().size());
		wg.addEdge(vert2, vert1);
		wg.addWeightedEdge(new WeightedGraphVertex(vert1), new WeightedGraphVertex(vert3), 3);

		System.out.println(wg.getWeightedVertices().size());
		
		assertEquals(wg.getWeightedVertices().size(), 3);
		wg.clear();
		assertEquals(wg.getWeightedVertices().size(), 0);
	}

}
