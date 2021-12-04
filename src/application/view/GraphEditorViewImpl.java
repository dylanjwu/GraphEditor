package application.view;

import java.util.ArrayList;
import java.util.List;

import application.controller.ModeController;
import application.model.Graph;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.util.Pair;

public class GraphEditorViewImpl extends Pane implements GraphEditorView {
	static final int NODE_RADIUS = 15;
	private Graph model; // use this to iterate through the nodes and edges
	private Circle currentSourceNode;
	private ModeController modeController;
	private static final int DIMENSION = 550;
	
	private Rectangle selectionRect;

	public GraphEditorViewImpl(Graph model) {
		this.setMaxHeight(DIMENSION);
		this.setMaxWidth(DIMENSION);

		BackgroundFill background_fill = new BackgroundFill(Color.WHITE, 
        CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(background_fill);
		this.setBackground(background);


		this.setLayoutX(50);
		this.setLayoutY(50);
		this.setBorder(Border.EMPTY);
		this.model = model;
		this.modeController = null;
	}
	
	@Override
	public void setModeController(ModeController newController) {
		modeController = newController;
		modeController.addCanvasPressHandler(this);
		modeController.addCanvasDragHandler(this);
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
		modeController.addNodeHandlers(newNode);
		newNode.setStroke(Color.RED);
		newNode.setFill(Color.TRANSPARENT);

		// register this node with the controller, add handler
//		StackPane stackPane = new StackPane();
//		Label label = new Label("Hi");
//		newNode.radiusProperty().bind(label.widthProperty());
//		stackPane.getChildren().addAll(newNode, label);
		
		this.getChildren().add(newNode);
	}
	
	@Override
	public void addEdge(Circle src, Circle dst) {
		Line edge = new Line(src.getCenterX(), src.getCenterY(), dst.getCenterX(), dst.getCenterY());
		System.out.println(edge);
		modeController.addEdgeEventHandlers(edge, src, dst);
		//connect the edge to the src and dst nodes so that when one of them moves, the edge moves accordingly
		System.out.println("src x: " + src.centerXProperty());

		System.out.println("dst x: " + dst.centerXProperty());

		edge.startXProperty().bind(src.centerXProperty());
		edge.startYProperty().bind(src.centerYProperty());
		edge.endXProperty().bind(dst.centerXProperty());
		edge.endYProperty().bind(dst.centerYProperty());

	    edge.setStrokeWidth(3);
//	    edge.setStrokeLineCap(StrokeLineCap.BUTT);
//	    edge.getStrokeDashArray().setAll(1.0, 4.0);
	    edge.setStroke(Color.BLACK);
	    edge.setVisible(true);
	    
		this.getChildren().add(edge);
		clear();
	}

	@Override
	public void highlightNode(Node node) {
		if (node instanceof Circle) ((Circle)node).setStroke(Color.RED);
		if (node instanceof Line) ((Line)node).setStroke(Color.RED);
	}
	
	@Override
	public void unhighlightNode(Node node) {
		if (node instanceof Circle) ((Circle)node).setStroke(Color.BLACK);
		if (node instanceof Line) ((Line)node).setStroke(Color.BLACK);
	}

	@Override
	public void moveSelection(Double x, Double y, Double w, Double h) {

		this.getChildren().removeIf(p -> p.equals(selectionRect));
		selectionRect = new Rectangle(x, y, w, h);

		selectionRect.setFill(Color.YELLOW);
		selectionRect.setOpacity(.2);
		selectionRect.setStroke(Color.BLACK);
		selectionRect.setVisible(true);
		this.getChildren().add(selectionRect);
	}

	@Override
	public void quitSelection() {
		this.getChildren().removeIf(a -> a.equals(selectionRect));
	}
	
}


