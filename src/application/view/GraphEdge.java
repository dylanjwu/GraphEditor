package application.view;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class GraphEdge extends StackPane {
	private Line edge;
	public GraphEdge(GraphNode src, GraphNode dst) {
		this.edge = new Line(src.getCenterX(), src.getCenterY(), dst.getCenterX(), dst.getCenterY());

		edge.startXProperty().bind(src.centerXProperty());
		edge.startYProperty().bind(src.centerYProperty());
		edge.endXProperty().bind(dst.centerXProperty());
		edge.endYProperty().bind(dst.centerYProperty());

	    edge.setStrokeWidth(3);
	    edge.setStroke(Color.BLACK);
	    edge.setVisible(true);
	    
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
