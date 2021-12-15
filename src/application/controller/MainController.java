package application.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import application.model.Graph;
import application.model.Vertex;
import application.view.GraphEdge;
import application.view.GraphEditorView;
import application.view.GraphNode;
import application.view.visitor.DFSVisitor;
import application.view.visitor.GraphVisitor;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Pair;

/**
 * Central controller that is in charge of changing between different mode controllers, 
 * running simulation, and holds view, model references
 * @author Dylan Wu
 * CS5010 v1 Fall 2021 - Final Project
 *  
 */

public class MainController implements Controller {
	
	private ModeController currentMode;
	private GraphEditorView view;
	private Graph model; 

	private Map<GraphNode, Vertex> nodeMap;
	private Map<GraphEdge, Pair<GraphNode, GraphNode>> edgeMap;
	
	public MainController(GraphEditorView view, Graph model) {
		this.nodeMap = new HashMap<>();
		this.edgeMap = new HashMap<>();

		/** opening mode is the add controller */
		this.currentMode = new AddController(view, nodeMap, edgeMap, model);

		this.view = view;
		this.model =  model;
		
	}

	@Override
	public void createNew() {
		// TODO Auto-generated method stub
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
	}

	/** given lowercase string, change the mode (changed in Main file) */
	@Override
	public void changeMode(String mode) {
		if (mode.equals("delete")) {
			this.currentMode = new DeleteController(view, nodeMap, edgeMap, model); 
		}
		else if (mode.equals("add")) {
			this.currentMode = new AddController(view, nodeMap, edgeMap, model); 
		}
		else if (mode.equals("move")) {
			this.currentMode = new MoveController(view, nodeMap, edgeMap, model); 
		}
		else {
			System.out.println("Invalid mode");
		}
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

	@Override
	public void importGraph() {
		// TODO Auto-generated method stub
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub
	}

	@Override
	public void copy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void paste() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public ModeController currentMode() {
		return currentMode;
	}

	/** Run the model's graph DFS iterator, should show all nodes highlighting (not yet fully functional)*/
	@Override
	public void simulateAlgorithm() throws InterruptedException {
		
		GraphVisitor visitor = new DFSVisitor();

		/** Using model's DFS iterator (iterator pattern) */

		for (Vertex vertex : model) {
			if (nodeMap.containsValue(vertex)) {
				for (Map.Entry<GraphNode, Vertex> entry : nodeMap.entrySet()) {
					if (entry.getValue().equals(vertex)) {
						
						GraphNode node = entry.getKey();

						node.accept(visitor);
					}
				}
			}
			/** must turn pause off for testing purposes */
//			Thread.sleep(100);
		}
	}
}
