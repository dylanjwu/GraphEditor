package application.view;

import javafx.beans.property.DoubleProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GraphNode extends StackPane {
	Circle circle;
	public GraphNode(Double x, Double y, int id) {
		System.out.println("NEW NODE: " + x + " " + y);
		Circle circle = new Circle(x, y, GraphEditorViewImpl.NODE_RADIUS);
		this.circle = circle;
		this.circle.setStroke(Color.RED);
		this.circle.setFill(Color.WHITE);
		this.getChildren().add(circle);
		Label labelId = new Label();
		this.getChildren().add(labelId);
	}
	public double getCenterX() {
		return circle.getCenterX();
	}
	
	public double getCenterY() {
		return circle.getCenterY();
	}
	
	public void movePosition(double x, double y) {
		this.setLayoutX(x);
		this.setLayoutY(y);
	}
	
	public void changeColor(Color color) {
		circle.setStroke(color);
	}
	public boolean isHighlighted() {
		return circle.getStroke().equals(Color.RED);
	}
	public DoubleProperty centerXProperty() {
		return circle.centerXProperty();
	}
	public DoubleProperty centerYProperty() {
		return circle.centerYProperty();
	}
}