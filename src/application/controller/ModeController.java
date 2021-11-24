package application.controller;

import javafx.scene.Node;
import javafx.scene.shape.Circle;

public interface ModeController {

	void onClickEdge();

	void onClickGroup();

	void onClickCanvas();

	void onDragCanvas();

	void onDragGroup();

	void addNodeDragHandler(Circle node);

	void addCanvasPressHandler(Node node);

}
