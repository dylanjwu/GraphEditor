package application.controller;

import java.util.Map;

import application.model.Graph;
import application.model.Vertex;
import application.view.GraphEditorView;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Pair;

public class DeleteController implements ModeController {

	private GraphEditorView view;
	private Graph model;
	private Map<Circle, Vertex> nodeMap;
	private Map<Line, Pair<Circle, Circle>> edgeMap;

	public DeleteController(GraphEditorView view, Map<Circle, Vertex> nodeMap, Map<Line, Pair<Circle, Circle>> edgeMap, Graph model) {
		this.model = model;
		this.view = view;
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
	public void onClickEdge() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClickGroup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClickCanvas() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDragCanvas() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDragGroup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNodeDragHandler(Circle node) {

		node.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				model.removeVertex(nodeMap.get(event.getSource()));
				//TODO delete all edges connected to node
				for (Line edge : edgeMap.keySet()) {
					Circle sourceNode = edgeMap.get(edge).getKey();
					Circle destNode = edgeMap.get(edge).getValue();
					if (sourceNode.equals(event.getSource()) || destNode.equals(event.getSource())){
						view.removeEdge(edge);
					}
				}

				nodeMap.remove(node);
				view.removeGroup(node);
			}
			
		});
	}

	@Override
	public void addCanvasPressHandler(Node node) {
		node.setOnMousePressed(null);
	}


	@Override
	public void addEdgeEventHandler(Line edge, Circle source, Circle dest) {

		edge.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("edge: " + edgeMap.get(event.getSource()));
				model.removeEdge(nodeMap.get(edgeMap.get(event.getSource()).getKey()), 
						nodeMap.get(edgeMap.get(event.getSource()).getValue()));
				edgeMap.remove((Line)event.getSource());
				view.removeEdge((Line) event.getSource());
			}
			
		});
	}

}
