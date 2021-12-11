package application.controller;

import java.util.HashMap;

import java.util.Map;

import application.model.Graph;
import application.model.Vertex;
import application.view.GraphEdge;
import application.view.GraphEditorView;
import application.view.GraphNode;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Pair;

/**
 * 
 * @author Dylan Wu
 * CS5010 v1 Fall 2021 - Final Project
 *  
 */

public class MainController implements Controller {
	
	private ModeController currentMode;

	private GraphEditorView view;

	//UNDO/REDO: save state with commands (if added, then remove command is added, vice versa)
	private Graph model; 

	//UNDO/REDO: save state (copies) of nodeMap and edgeMap
	private Map<GraphNode, Vertex> nodeMap;
	private Map<GraphEdge, Pair<GraphNode, GraphNode>> edgeMap;

	public MainController(GraphEditorView view, Graph model) {
		this.nodeMap = new HashMap<>();
		this.edgeMap = new HashMap<>();

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

}
