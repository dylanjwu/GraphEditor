package application.controller;

import java.util.Map;

import application.model.Graph;
import application.model.Vertex;
import application.view.GraphEditorView;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Pair;

public class MoveController implements ModeController {
	private GraphEditorView view;
	private Graph model;
	private Map<Circle, Vertex> nodeMap;
	private Map<Line, Pair<Circle, Circle>> edgeMap;

	private double orgSceneX, orgSceneY;
	private Pair<Double, Double> selectionStart;

	public MoveController(GraphEditorView view, Map<Circle, Vertex> nodeMap, Map<Line, Pair<Circle, Circle>> edgeMap, Graph model) {
		this.view = view;
		this.model = model;
		this.nodeMap = nodeMap;
		this.edgeMap = edgeMap;
		view.setModeController(this);

		for (Circle node : nodeMap.keySet()) {
			addNodeDragHandler(node);
		}

		for (Line edge : edgeMap.keySet()) {
			addEdgeEventHandler(edge, null, null);
		}
	
	}

	@Override
	public void addNodeDragHandler(Circle node) {
		// TODO select node when pressed on

		node.setOnMouseDragged(null);
		node.setOnDragDetected(null);
		node.setOnDragDropped(null);
		
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
	      t.consume();
	    });	
	}

	@Override
	public void addCanvasPressHandler(Node node) {

		node.setOnMousePressed(e -> {
			System.out.println("MOUSE PRESSED");
			/** un-highlight all incident nodes */
			for (Circle circle : nodeMap.keySet()) {
				System.out.println(circle.getFill());
				if (circle.getStroke().equals(Color.RED)){
					System.out.println("IS RED");
					view.unhighlightNode(circle);
				}
			}
			
			/** un-highlight all incident edges */
			for (Line line : edgeMap.keySet()) {
				if (line.getStroke().equals(Color.RED)){
					view.unhighlightNode(line);
				}
			}
		});

		node.setOnDragDetected(e -> selectionStart = new Pair<>(e.getX(), e.getY()));
		
		node.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {

				if (selectionStart == null) return;

				Double startX; 
				Double startY;
				Double x = e.getX();
				Double y = e.getY();
				Double width = Math.abs(x-selectionStart.getKey());
				Double height = Math.abs(y-selectionStart.getValue());

				if (x < selectionStart.getKey() && y < selectionStart.getValue()) {
					startX = x;
					startY = y;
				}

				else if (x < selectionStart.getKey()) {
					startX = x;
					startY = selectionStart.getValue();
				}

				else if (y < selectionStart.getValue()) {
					startX = selectionStart.getKey();
					startY = y;
				}
				
				else {
					startX = selectionStart.getKey();
					startY = selectionStart.getValue();
				}
				
				/** highlight all incident nodes */
				for (Circle node : nodeMap.keySet()) {
					if (node.getCenterX() > startX && node.getCenterX() < startX+width &&
							node.getCenterY() > startY && node.getCenterY() < startY+height) {

						view.highlightNode(node);
					}
					else {
						view.unhighlightNode(node);
					}
				}
				
				/** highlight all incident edges */
				for (Line node : edgeMap.keySet()) {
					if (node.getEndX() > startX && node.getEndX() < startX+width &&
							node.getEndY() > startY && node.getEndY() < startY+height &&
							node.getStartX() > startX && node.getStartX() < startX+width &&
							node.getStartY() > startY && node.getStartY() < startY+height) {

						view.highlightNode(node);
					}
					else {
						view.unhighlightNode(node);
					}
				}

				view.moveSelection(startX, startY, width, height);
			}
			
		});
		
		node.setOnMouseReleased(e -> { 
				view.quitSelection(); 
				selectionStart = null;
		});
		
	}

	@Override
	public void addEdgeEventHandler(Line edge, Circle source, Circle dest) {
		edge.setOnMousePressed(null);
	}

}
