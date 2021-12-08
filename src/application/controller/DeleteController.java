package application.controller;

import java.util.Iterator;

import java.util.Map;

import application.model.Graph;
import application.model.Vertex;
import application.view.GraphEditorView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
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

public class DeleteController extends AbstractModeController {
	
	private boolean nodeSelected(Circle node) {
		return node.getStroke().equals(Color.RED);
	}

//	private boolean edgeSelected(Line edge) {
//		return edge.getStroke().equals(Color.RED);
//	}

	public DeleteController(GraphEditorView view, Map<Circle, Vertex> nodeMap, Map<Line, Pair<Circle, Circle>> edgeMap, Graph model) {
		this.model = model;
		this.view = view;
		this.nodeMap = nodeMap;
		this.edgeMap = edgeMap;
		view.setModeController(this);


		for (Circle node : nodeMap.keySet()) {
			addNodeHandlers(node);
		}

		for (Line edge : edgeMap.keySet()) {
			addEdgeEventHandlers(edge, null, null);
		}
	}
	
	/** only to be used if node is removed from nodeMap as well */
	private void deleteNode(Circle node) {
		model.removeVertex(nodeMap.get(node));
		for (Line edge : edgeMap.keySet()) {
			Circle sourceNode = edgeMap.get(edge).getKey();
			Circle destNode = edgeMap.get(edge).getValue();
			if (sourceNode.equals(node) || destNode.equals(node)){
				view.removeEdge(edge);
			}
		}
		view.removeGroup(node);
	}


	@Override
	public void addNodeHandlers(Circle node) {

		node.setOnMouseDragged(null);
		node.setOnDragOver(null);
		node.setOnDragDropped(null);
		node.setOnDragDetected(null);

		node.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

				Circle circle = (Circle)event.getSource();

				if (nodeSelected(circle)) {

					Iterator<Circle> iterator = nodeMap.keySet().iterator();
					
					while (iterator.hasNext()) {

						Circle otherCircle = iterator.next();

						if (nodeSelected(otherCircle)) {
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

	@Override
	public void addEdgeEventHandlers(Line edge, Circle source, Circle dest) {

		edge.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// REMOVE EDGE
				model.removeEdge(nodeMap.get(edgeMap.get(event.getSource()).getKey()), 
						nodeMap.get(edgeMap.get(event.getSource()).getValue()));

				System.out.println("REMOVED EDGE: " + edgeMap);
				edgeMap.remove((Line)event.getSource());
				view.removeEdge((Line) event.getSource());
			}
			
		});
	}

}
