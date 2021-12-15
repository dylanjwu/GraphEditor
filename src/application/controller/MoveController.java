package application.controller;

import java.util.Map;


import application.model.Graph;
import application.model.Vertex;
import application.view.GraphEdge;
import application.view.GraphEditorView;
import application.view.GraphNode;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Pair;

/**
 * Controller for moving nodes and vertices aroung the screen
 * @author Dylan Wu
 * CS5010 v1 Fall 2021 - Final Project
 *  
 */


public class MoveController extends AbstractModeController {

	private double orgSceneX, orgSceneY;
	private boolean dragged;

	/**
	 * 
	 * @param view
	 * @param nodeMap
	 * @param edgeMap
	 * @param model
	 */
	public MoveController(GraphEditorView view, Map<GraphNode, Vertex> nodeMap, Map<GraphEdge, Pair<GraphNode, GraphNode>> edgeMap, Graph model) {
		this.view = view;
		this.model = model;
		this.nodeMap = nodeMap;
		this.edgeMap = edgeMap;
		this.dragged = false;

		view.setModeController(this);

		/** add its node handlers */
		for (GraphNode node : nodeMap.keySet()) {
			addNodeHandlers(node);
		}

		/** add its edge handlers */
		for (GraphEdge edge : edgeMap.keySet()) {
			addEdgeEventHandlers(edge, null, null);
		}
	
	}


	/**
	 * Set handlers on nodes for when they are dragged, drag/dropped, released
	 * Move them when dragged
	 */
	@Override
	public void addNodeHandlers(GraphNode node) {

		node.setOnMouseDragged(null);
		node.setOnDragDetected(null);
		node.setOnDragDropped(null);

	    node.setOnMousePressed((t) -> {
		
	      orgSceneX = t.getSceneX();
	      orgSceneY = t.getSceneY();

	      GraphNode c = (GraphNode) (t.getSource());
	      view.highlightNode(c); 
	      c.toFront();
	      t.consume();
	    });


	    node.setOnMouseDragged((t) -> {

	      dragged = true;

	      double offsetX = t.getSceneX() - orgSceneX;
	      double offsetY = t.getSceneY() - orgSceneY;


	      for (GraphNode otherNode : nodeMap.keySet()) {
	    	  view.moveNode(otherNode, offsetX, offsetY);
	      }

	      orgSceneX = t.getSceneX();
	      orgSceneY = t.getSceneY();

	      t.consume();
	    });	

	    
	    node.setOnMouseReleased(t -> {
	    	
	    	if (dragged == false) {

			  unselectGraph();
			  view.highlightNode((GraphNode)t.getSource());
			  orgSceneX = t.getSceneX();
			  orgSceneY = t.getSceneY();
;
			  t.consume();
	    	}
	    	dragged = false;
	    });
	}

	/**
	 * Add handler that highlights edge when it is pressed; unselect the rest of the graph if applicable
	 */
	@Override
	public void addEdgeEventHandlers(GraphEdge edge, GraphNode source, GraphNode dest) {

		
		edge.setOnMousePressed(e -> {
			unselectGraph();
			view.highlightNode((Node)e.getSource());
			e.consume();
		});
	}


}
