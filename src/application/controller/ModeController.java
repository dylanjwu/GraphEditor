package application.controller;

import application.view.GraphEdge;
import application.view.GraphNode;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 * Mode controller interface for Delete/Add/Move controllers
 * @author Dylan Wu
 * CS5010 v1 Fall 2021 - Final Project
 *  
 */

public interface ModeController {

	void addNodeHandlers(GraphNode node);

	void addEdgeEventHandlers(GraphEdge edge, GraphNode source, GraphNode dest);
	
	void unselectGraph();

	void addCanvasPressHandler(Node node);

	void addCanvasDragHandler(Node node);

}
