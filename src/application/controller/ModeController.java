package application.controller;

import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 * 
 * @author Dylan Wu
 * CS5010 v1 Fall 2021 - Final Project
 *  
 */

public interface ModeController {

	void addNodeHandlers(Circle node);

	void addEdgeEventHandlers(Line edge, Circle source, Circle dest);
	
	void unselectGraph();

	void addCanvasPressHandler(Node node);

	void addCanvasDragHandler(Node node);

}
