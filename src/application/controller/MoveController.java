package application.controller;

import java.util.Map;

import application.model.Graph;
import application.model.Vertex;
import application.view.GraphEditorView;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Pair;

public class MoveController implements ModeController {
	private GraphEditorView view;
	private Graph model;

	private double orgSceneX, orgSceneY;

	public MoveController(GraphEditorView view, Map<Circle, Vertex> nodeMap, Map<Line, Pair<Circle, Circle>> edgeMap, Graph model) {
		this.view = view;
		this.model = model;
		view.setModeController(this);

		for (Circle node : nodeMap.keySet()) {
			addNodeDragHandler(node);
		}
	
	}

	@Override
	public void addNodeDragHandler(Circle node) {
		// TODO select node when pressed on

		node.setOnMouseDragged(null);
//		node.setOnMousePressed(null);
		node.setOnDragDetected(null);
		node.setOnDragDropped(null);
//		node.setOnMousePressed(null);
		
	    node.setOnMousePressed((t) -> {
	      orgSceneX = t.getSceneX();
	      orgSceneY = t.getSceneY();

	      Circle c = (Circle) (t.getSource());
	      c.toFront();
	    });
	    node.setOnMouseDragged((t) -> {
	      double offsetX = t.getSceneX() - orgSceneX;
	      double offsetY = t.getSceneY() - orgSceneY;

	      Circle c = (Circle) (t.getSource());

	      c.setCenterX(c.getCenterX() + offsetX);
	      c.setCenterY(c.getCenterY() + offsetY);

	      orgSceneX = t.getSceneX();
	      orgSceneY = t.getSceneY();
	    });	
	}

	@Override
	public void addCanvasPressHandler(Node node) {
		// TODO create selection rectangle when dragged

		node.setOnMousePressed(null);
	}

	@Override
	public void addEdgeEventHandler(Line edge, Circle source, Circle dest) {
		// TODO select edge
		
	}

}
