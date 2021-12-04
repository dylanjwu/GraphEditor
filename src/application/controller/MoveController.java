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

public class MoveController extends AbstractModeController {

	private double orgSceneX, orgSceneY;
	private Pair<Double, Double> selectionStart;
	private boolean dragged;

	public MoveController(GraphEditorView view, Map<Circle, Vertex> nodeMap, Map<Line, Pair<Circle, Circle>> edgeMap, Graph model) {
		this.view = view;
		this.model = model;
		this.nodeMap = nodeMap;
		this.edgeMap = edgeMap;
		this.dragged = false;
		view.setModeController(this);

		for (Circle node : nodeMap.keySet()) {
			addNodeHandlers(node);
		}

		for (Line edge : edgeMap.keySet()) {
			addEdgeEventHandlers(edge, null, null);
		}
	
	}

	private void drag(Circle node, Double offsetX, Double offsetY) {
	     // view's responsibility!
	    node.setCenterX(node.getCenterX() + offsetX);
	    node.setCenterY(node.getCenterY() + offsetY);
	}

	@Override
	public void addNodeHandlers(Circle node) {

		node.setOnMouseDragged(null);
		node.setOnDragDetected(null);
		node.setOnDragDropped(null);
		
	    node.setOnMousePressed((t) -> {
		
	      orgSceneX = t.getSceneX();
	      orgSceneY = t.getSceneY();

	      Circle c = (Circle) (t.getSource());
	      c.toFront();
	      t.consume();
	    });

	    node.setOnMouseDragged((t) -> {
	      dragged = true;
	      double offsetX = t.getSceneX() - orgSceneX;
	      double offsetY = t.getSceneY() - orgSceneY;

	      Circle c = (Circle) (t.getSource());

	      // view's responsibility!
	      c.setCenterX(c.getCenterX() + offsetX);
	      c.setCenterY(c.getCenterY() + offsetY);


	      for (Circle otherNode : nodeMap.keySet()) {
	    	  if (otherNode.getStroke().equals(Color.RED) && !otherNode.equals(c)) {
	    		  drag(otherNode, offsetX, offsetY);
	    	  }
	      }

	      orgSceneX = t.getSceneX();
	      orgSceneY = t.getSceneY();
	      System.out.println(dragged);
	      t.consume();
	    });	
	    
	    node.setOnMouseReleased(t -> {
	    	System.out.println(dragged);
	    	if (dragged == false) {

			  unselectAllNodes();
			  view.highlightNode((Node)t.getSource());
				
			  orgSceneX = t.getSceneX();
			  orgSceneY = t.getSceneY();

			  Circle c = (Circle) (t.getSource());
			  c.toFront();
			  t.consume();
	    	}
	    	dragged = false;
	    });
	}

	@Override
	public void addEdgeEventHandlers(Line edge, Circle source, Circle dest) {
		edge.setOnMousePressed(e -> {
			unselectAllNodes();
			view.highlightNode((Node)e.getSource());
			e.consume();
		});
	}


}
