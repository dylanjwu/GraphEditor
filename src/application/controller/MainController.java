package application.controller;

import java.util.HashMap;
import java.util.Map;

import application.model.Graph;
import application.model.Vertex;
import application.view.GraphEditorView;
import javafx.scene.shape.Circle;

public class MainController implements Controller {
	
	private ModeController currentMode;
	private GraphEditorView view;
	private Graph model;

	private Map<Circle, Vertex> nodeMap;

	public MainController(GraphEditorView view, Graph model) {
		this.nodeMap = new HashMap<>();
		this.currentMode = new AddController(view, nodeMap, model); 
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
	public void changeMode() {
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
