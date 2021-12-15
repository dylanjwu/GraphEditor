package application.controller;

import java.util.Iterator;

import java.util.Map;

import application.model.Graph;
import application.model.Vertex;
import application.view.GraphEdge;
import application.view.GraphEditorView;
import application.view.GraphNode;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Pair;

/**
 * Mode controller implementation for deleting nodes and edges
 * @author Dylan Wu
 * CS5010 v1 Fall 2021 - Final Project
 *  
 */

public class DeleteController extends AbstractModeController {
	
	/**
	 * 
	 * @param view
	 * @param nodeMap
	 * @param edgeMap
	 * @param model
	 */
	public DeleteController(GraphEditorView view, Map<GraphNode, Vertex> nodeMap, Map<GraphEdge, Pair<GraphNode, GraphNode>> edgeMap, Graph model) {
		this.model = model;
		this.view = view;
		this.nodeMap = nodeMap;
		this.edgeMap = edgeMap;
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
	
	/** Deletes node from the view and the model; only to be used if node is removed from nodeMap as well */
	private void deleteNode(GraphNode node) {
		model.removeVertex(nodeMap.get(node));
		for (GraphEdge edge : edgeMap.keySet()) {
			GraphNode sourceNode = edgeMap.get(edge).getKey();
			GraphNode destNode = edgeMap.get(edge).getValue();
			if (sourceNode.equals(node) || destNode.equals(node)){
				view.removeEdge(edge);
			}
		}
		view.removeGroup(node);
	}


	/** Adds handler to node such that if pressed, will delete itself and other highlighted nodes/edges if applicable (from controller maps, and view and model) */
	@Override
	public void addNodeHandlers(GraphNode node) {

		node.setOnMouseDragged(null);
		node.setOnDragOver(null);
		node.setOnDragDropped(null);
		node.setOnDragDetected(null);

		node.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

				GraphNode circle = (GraphNode)event.getSource();

				if (circle.isHighlighted()) {

					/** run through iterator to find highlighted nodes */
					Iterator<GraphNode> iterator = nodeMap.keySet().iterator();
					
					while (iterator.hasNext()) {

						GraphNode otherCircle = iterator.next();

						if (otherCircle.isHighlighted()) {
							deleteNode(otherCircle);
							iterator.remove();
						}
					}
				}
				else {
					deleteNode(circle);
				}

			}
			
		});
	}


	/** Add handler to edge such that it is removed from view and model and edgeMap*/
	@Override
	public void addEdgeEventHandlers(GraphEdge edge, GraphNode source, GraphNode dest) {

		edge.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				/** SAVE STATE */
				model.removeEdge(nodeMap.get(edgeMap.get(event.getSource()).getKey()), 
						nodeMap.get(edgeMap.get(event.getSource()).getValue()));

				System.out.println("REMOVED EDGE: " + edgeMap);
				edgeMap.remove((GraphEdge)event.getSource());
				view.removeEdge((GraphEdge) event.getSource());
			}
			
		});
	}

}
