package application.controller;

import java.util.Map;

import application.model.Graph;
import application.model.Vertex;
import application.view.GraphEdge;
import application.view.GraphEditorView;
import application.view.GraphNode;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;

/**
 * 
 * @author Dylan Wu
 * CS5010 v1 Fall 2021 - Final Project
 *  
 */

public abstract class AbstractModeController implements ModeController {

	protected GraphEditorView view;
	protected Graph model;
	protected Map<GraphNode, Vertex> nodeMap;
	protected Map<GraphEdge, Pair<GraphNode, GraphNode>> edgeMap;
	protected Pair<Double, Double> selectionStart;

	/** helper function for unselecting the edges and nodes of the graph */
	@Override
	public void unselectGraph() {
		/** un-highlight all incident nodes */
		for (GraphNode circle : nodeMap.keySet()) {
			if (circle.isHighlighted()){
				view.unhighlightNode(circle);
			}
		}
		
		/** un-highlight all incident edges */
		for (GraphEdge line : edgeMap.keySet()) {
			if (line.isHighlighted()){
				view.unhighlightNode(line);
			}
		}
	}
	
	/**
	 * Unselect the selection of the graph by default
	 */
	@Override
	public void addCanvasPressHandler(Node node) {
		node.setOnMousePressed(e -> {
			unselectGraph();
		});
	}
	
	/**
	 * Select/highlight nodes and edges
	 * @param startX
	 * @param startY
	 * @param width
	 * @param height
	 */
	private void selectSubgraph(Double startX, Double startY, Double width, Double height) {
		
			/** highlight all incident nodes */
			for (GraphNode node : nodeMap.keySet()) {
				if (node.getCenterX() > startX && node.getCenterX() < startX+width &&
						node.getCenterY() > startY && node.getCenterY() < startY+height) {

					view.highlightNode(node);
				}
				else {

					view.unhighlightNode(node);
				}
			}
			
			/** highlight all incident edges */
			for (GraphEdge node : edgeMap.keySet()) {
				if (node.isCovered(startX, startY, width, height)) {
					view.highlightNode(node);
				}
				else {
					view.unhighlightNode(node);
				}
			}
	}

	/**
	 * Create rectangular selector for selecting graphs and nodes
	 */
	@Override
	public void addCanvasDragHandler(Node node) {

		/** initialize starting coordinates */
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

				/** inversion logic */
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
				

				selectSubgraph(startX, startY, width, height);
				view.moveSelection(startX, startY, width, height);
			}
			
		});

		/** selection goes when mouse is released */
		node.setOnMouseReleased(e -> {
				view.quitSelection(); 
				selectionStart = null;
		});
		
	}
}