package application.view;

import application.controller.ModeController;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public interface GraphEditorView {

	void removeGroup(Node node);

	void removeEdge(Line edge);

	void moveGroup();

	void selectEdge();

	void zoomIn();

	void zoomOut();

	void clear();

	void addNode(double x, double y);

	Circle getCurrentSourceNode();

	void addEdge(Circle src, Circle dst);

	void setCurrentSourceNode(Circle node);

	void setModeController(ModeController newController);

	void moveSelection(Double x, Double y, Double w, Double h);

	void quitSelection();

	void highlightNode(Node node);

	void unhighlightNode(Node node);

}
