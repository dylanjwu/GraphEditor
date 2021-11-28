package application.controller;

import java.util.HashMap;
import java.util.Map;

import application.model.Graph;
import application.model.Vertex;
import application.view.GraphEditorView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Pair;

public class MainController implements Controller {
	
	private ModeController currentMode;
	private GraphEditorView view;
	private Graph model;

	private Map<Circle, Vertex> nodeMap;
	
	private Map<Line, Pair<Circle, Circle>> edgeMap;

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
			this.currentMode = new MoveController(); 
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
