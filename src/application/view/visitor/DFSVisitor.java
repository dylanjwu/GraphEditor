package application.view.visitor;

import application.view.GraphEdge;
import application.view.GraphNode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * 
 * @author Dylan Wu
 * 
 * DFS visitor for the graph; visits nodes and edges
 * Also prints to console, as nothing much happens on the view as of now
 *
 */

public class DFSVisitor implements GraphVisitor {
	
	private int count;

	public DFSVisitor() {
		count = 0;
	}

	@Override
	public void visitNode(GraphNode node) {
		node.changeColor(Color.RED);
		count++;
		System.out.println("Nodes visited: " + count);
	}

	@Override
	public void visitEdge(GraphEdge edge) {
		((Line)edge).setStroke(Color.RED);
	}

}
