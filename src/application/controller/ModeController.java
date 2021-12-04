package application.controller;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Pair;

public interface ModeController {

	void addNodeHandlers(Circle node);

	void addEdgeEventHandlers(Line edge, Circle source, Circle dest);
	
	void unselectAllNodes();

	void addCanvasPressHandler(Node node);

	void addCanvasDragHandler(Node node);

}
