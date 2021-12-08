package application.controller;

import java.util.Map;


import application.model.Graph;
import application.model.Vertex;
import application.view.GraphEditorView;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Pair;

/**
 * 
 * @author Dylan Wu
 * CS5010 v1 Fall 2021 - Final Project
 *  
 */

public class MoveController extends AbstractModeController {

	private double orgSceneX, orgSceneY;
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


	@Override
	public void addNodeHandlers(Circle node) {

		node.setOnMouseDragged(null);
		node.setOnDragDetected(null);
		node.setOnDragDropped(null);
		
	    node.setOnMousePressed((t) -> {
		
	    	/** SAVE STATE */
	      orgSceneX = t.getSceneX();
	      orgSceneY = t.getSceneY();

	      Circle c = (Circle) (t.getSource());
	      view.highlightNode(c);
	      c.toFront();
	      t.consume();
	    });


	    /** SAVE STATE */
	    node.setOnMouseDragged((t) -> {

	      dragged = true;

	      double offsetX = t.getSceneX() - orgSceneX;
	      double offsetY = t.getSceneY() - orgSceneY;


	      for (Circle otherNode : nodeMap.keySet()) {
	    	  view.moveNode(otherNode, offsetX, offsetY);
	      }

	      orgSceneX = t.getSceneX();
	      orgSceneY = t.getSceneY();

	      t.consume();
	    });	
	    
	    node.setOnMouseReleased(t -> {
	    	
	    	if (dragged == false) {

			  unselectGraph();
			  view.highlightNode((Circle)t.getSource());
			  orgSceneX = t.getSceneX();
			  orgSceneY = t.getSceneY();
;
			  t.consume();
	    	}
	    	dragged = false;
	    });
	}

	@Override
	public void addEdgeEventHandlers(Line edge, Circle source, Circle dest) {
		edge.setOnMousePressed(e -> {
			/** SAVE STATE */
			unselectGraph();
			view.highlightNode((Node)e.getSource());
			e.consume();
		});
	}


}
