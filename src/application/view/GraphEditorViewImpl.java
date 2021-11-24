package application.view;

import java.util.ArrayList;
import java.util.List;

import application.controller.ModeController;
import application.model.Graph;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Pair;

public class GraphEditorViewImpl extends Pane implements GraphEditorView {
	static final int NODE_RADIUS = 15;
	private Graph model; // use this to iterate through the nodes and edges
	private Circle currentSourceNode;
	private ModeController modeController;

	public GraphEditorViewImpl(Graph model) {
		this.prefHeight(300);
		this.prefWidth(300);
		this.model = model;
		this.modeController = null;
	}
	
	@Override
	public void setModeController(ModeController newController) {
		modeController = newController;
		modeController.addCanvasPressHandler(this);
	}
	
	@Override
	public Circle getCurrentSourceNode() {
		return currentSourceNode;
	}

	@Override
	public void setCurrentSourceNode(Circle node) {
		currentSourceNode = node;
	}

	@Override
	public void removeGroup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEdge() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveGroup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectGroup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unselectGroup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectEdge() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void zoomIn() {
//			scene.setOnZoomStarted(evt -> System.out.println("ZOOM STARTED"));
////		scene.setOnZoom(evt -> {
////			System.out.println("ZOOMING");
////			double zoomFactor = 1.2;
////			root.setScaleX(root.getScaleX() * zoomFactor);
////            root.setScaleY(root.getScaleY() * zoomFactor);
////		});
////		
////		scene.setOnZoomFinished(evt -> System.out.println("ZOOM FINISHED"));
//
	}

	@Override
	public void zoomOut() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
	}
	
	
	@Override
	public void addNode(double x, double y) {
		if (modeController == null) return;
		Circle newNode = new Circle(x, y, NODE_RADIUS);
		// register this node with the controller, add handler
		modeController.addNodeDragHandler(newNode);
		newNode.setStroke(Color.BLACK);
		newNode.setFill(Color.TRANSPARENT);
		this.getChildren().add(newNode);
	}
	
	@Override
	public void addEdge(Circle src, Circle dst) {
		Line edge = new Line(src.getCenterX(), src.getCenterY(), dst.getCenterX(), dst.getCenterY());
		this.getChildren().add(edge);
	}
	
}


