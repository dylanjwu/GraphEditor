package application.controller;

import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public interface ModeController {

	void addNodeDragHandler(Circle node);

	void addCanvasPressHandler(Node node);

	void addEdgeEventHandler(Line edge, Circle source, Circle dest);

}
