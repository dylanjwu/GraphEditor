package application.view.visitor;

import application.view.GraphEdge;
import application.view.GraphNode;

/**
 * Visitor interface for the visitor pattern; visiting the graph
 * 
 * @author Dylan Wu
 */

public interface GraphVisitor {
	void visitNode(GraphNode node);
	void visitEdge(GraphEdge edge);
}
