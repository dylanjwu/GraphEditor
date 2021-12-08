package application.controller;

import java.util.Map;

import application.model.Graph;
import application.model.Vertex;
import application.view.GraphEditorView;
import javafx.event.EventHandler;
import javafx.scene.Node;
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

public abstract class AbstractModeController implements ModeController {

	protected GraphEditorView view;
	protected Graph model;
	protected Map<Circle, Vertex> nodeMap;
	protected Map<Line, Pair<Circle, Circle>> edgeMap;
	protected Pair<Double, Double> selectionStart;

	@Override
	public void unselectGraph() {
		/** un-highlight all incident nodes */
		for (Circle circle : nodeMap.keySet()) {
			System.out.println(circle.getFill());
			if (circle.getStroke().equals(Color.RED)){
				System.out.println("IS RED IN PARENT");
				view.unhighlightNode(circle);
			}
		}
		
		/** un-highlight all incident edges */
		for (Line line : edgeMap.keySet()) {
			if (line.getStroke().equals(Color.RED)){
				view.unhighlightNode(line);
			}
		}
	}
	
	@Override
	public void addCanvasPressHandler(Node node) {
		node.setOnMousePressed(e -> {
			unselectGraph();
		});
	}
	
	private void selectSubgraph(Double startX, Double startY, Double width, Double height) {
		
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
	}

	@Override
	public void addCanvasDragHandler(Node node) {

		/** SAVE STATE */
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
				

				selectSubgraph(startX, startY, width, height);
				view.moveSelection(startX, startY, width, height);
			}
			
		});

		/** SAVE STATE */
		node.setOnMouseReleased(e -> {
				view.quitSelection(); 
				selectionStart = null;
		});
		
	}
}