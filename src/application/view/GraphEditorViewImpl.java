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
import javafx.scene.shape.StrokeLineCap;
import javafx.util.Pair;

public class GraphEditorViewImpl extends Pane implements GraphEditorView {
	static final int NODE_RADIUS = 15;
	private Graph model; // use this to iterate through the nodes and edges
	private Circle currentSourceNode;
	private ModeController modeController;

	public GraphEditorViewImpl(Graph model) {
		this.setPrefSize(450, 352);
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
	public void removeGroup(Node node) {
		this.getChildren().remove(node);
	}

	@Override
	public void removeEdge(Line edge) {
		this.getChildren().remove(edge);
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
		for (Node child : getChildren()) {
			System.out.println(child);
		}
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
		System.out.println(edge);
		modeController.addEdgeEventHandler(edge, src, dst);
		//connect the edge to the src and dst nodes so that when one of them moves, the edge moves accordingly
		System.out.println("src x: " + src.centerXProperty());

		System.out.println("dst x: " + dst.centerXProperty());

		edge.startXProperty().bind(src.centerXProperty());
		edge.startYProperty().bind(src.centerYProperty());
		edge.endXProperty().bind(dst.centerXProperty());
		edge.endYProperty().bind(dst.centerYProperty());

	    edge.setStrokeWidth(1);
//	    edge.setStrokeLineCap(StrokeLineCap.BUTT);
//	    edge.getStrokeDashArray().setAll(1.0, 4.0);
	    edge.setStroke(Color.BLACK);
	    edge.setVisible(true);
	    
		this.getChildren().add(edge);
		clear();
	}
	
}


