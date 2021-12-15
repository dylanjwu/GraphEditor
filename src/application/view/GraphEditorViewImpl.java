package application.view;

import application.controller.ModeController;
import application.model.Graph;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 * View implementation class -- manages the visuals of nodes and edges; adds/deletes/moves them around
 * @author Dylan Wu
 * CS5010 v1 Fall 2021 - Final Project
 *  
 */

public class GraphEditorViewImpl extends Pane implements GraphEditorView {
	public static final int NODE_RADIUS = 15;

	/** if used, should segregate the interface, so that mutations are not possible here */
	private Graph model; // use this to iterate through the nodes and edges 
	
	private GraphNode currentSourceNode;
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
	public GraphNode getCurrentSourceNode() {
		return currentSourceNode;
	}

	@Override
	public void setCurrentSourceNode(GraphNode node) {
		currentSourceNode = node;
	}

	@Override
	public void removeGroup(Node node) {
		this.getChildren().remove(node);
	}

	@Override
	public void removeEdge(GraphEdge edge) {
		this.getChildren().remove(edge);
	}

	@Override
	public void zoomIn() {
		// TODO Auto-generated method stub
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
		GraphNode newNode = new GraphNode(x, y);
		modeController.addNodeHandlers(newNode);
		this.getChildren().add(newNode);
	}
	
	@Override
	public void addEdge(GraphNode src, GraphNode dst) {
		GraphEdge edge = new GraphEdge(src, dst);
		
		modeController.addEdgeEventHandlers(edge, src, dst);
		this.getChildren().add(edge);

	    src.toFront();
	    dst.toFront();
		clear();
	}

	@Override
	public void highlightNode(Node node) {
		if (node instanceof GraphNode) ((GraphNode)node).changeColor(Color.RED);
		if (node instanceof Line) ((Line)node).setStroke(Color.RED);
	}
	
	@Override
	public void unhighlightNode(Node node) {
		if (node instanceof GraphNode) ((GraphNode)node).changeColor(Color.BLACK);
		if (node instanceof Line) ((Line)node).setStroke(Color.BLACK);
	}

	@Override
	public void moveSelection(Double x, Double y, Double w, Double h) {
		if(x >= 0 && y >= 0 && x+w <= DIMENSION && y+h <= DIMENSION) {

			this.getChildren().removeIf(p -> p.equals(selectionRect));
			selectionRect = new Rectangle(x, y, w, h);

			selectionRect.setFill(Color.YELLOW);
			selectionRect.setOpacity(.2);
			selectionRect.setStroke(Color.BLACK);
			selectionRect.setVisible(true);
			this.getChildren().add(selectionRect);
		}
	}

	@Override
	public void quitSelection() {
		this.getChildren().removeIf(a -> a.equals(selectionRect));
	}

	@Override
	public void moveNode(GraphNode node, Double offsetX, Double offsetY) {
		double nextX = node.getLayoutX() + offsetX;
		double nextY = node.getLayoutY() + offsetY;

		if (node.isHighlighted() && nextX-NODE_RADIUS > 0 && nextX+NODE_RADIUS < DIMENSION 
				&& nextY-NODE_RADIUS > 0 && nextY+NODE_RADIUS < DIMENSION) {
			node.movePosition(nextX, nextY);
		}
	}
	
}
