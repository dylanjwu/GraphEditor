package application.view;

import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class GraphEdge extends StackPane {
	private Line edge;
	public GraphEdge(GraphNode src, GraphNode dst) {
		this.edge = new Line(src.getCenterX(), src.getCenterY(), dst.getCenterX(), dst.getCenterY());

		this.setLayoutX(src.getCenterX());
		this.setLayoutY(src.getCenterY());
		this.setWidth(dst.getCenterX()-src.getCenterX());
		this.setHeight(dst.getCenterY()-src.getCenterY());

		edge.startXProperty().bind(src.centerXProperty());
		edge.startYProperty().bind(src.centerYProperty());
		edge.endXProperty().bind(dst.centerXProperty());
		edge.endYProperty().bind(dst.centerYProperty());

	    edge.setStrokeWidth(3);
	    edge.setStroke(Color.BLACK);
	    edge.setVisible(true);


	    this.getChildren().add(edge);
//	    TextField field = new TextField("Edge");
//	    this.getChildren().add(field);
	    
	}
	public boolean isHighlighted() {
		return edge.getStroke().equals(Color.RED);
	}
	public boolean isCovered(double startX, double startY, double width, double height) {
		return (edge.getEndX() > startX && edge.getEndX() < startX+width &&
						edge.getEndY() > startY && edge.getEndY() < startY+height &&
						edge.getStartX() > startX && edge.getStartX() < startX+width &&
						edge.getStartY() > startY && edge.getStartY() < startY+height);

	}
}
