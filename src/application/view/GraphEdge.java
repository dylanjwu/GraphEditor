package application.view;

import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class GraphEdge extends Line {

	public GraphEdge(GraphNode src, GraphNode dst) {
		super(src.getCenterX(), src.getCenterY(), dst.getCenterX(), dst.getCenterY());
		
		startXProperty().bind(src.layoutXProperty());
		startYProperty().bind(src.layoutYProperty());
		endXProperty().bind(dst.layoutXProperty());
		endYProperty().bind(dst.layoutYProperty());

//		startXProperty().bind(src.centerXProperty());
//		startYProperty().bind(src.centerYProperty());
//		endXProperty().bind(dst.centerXProperty());
//		endYProperty().bind(dst.centerYProperty());

	    setStrokeWidth(3);
	    setStroke(Color.BLACK);
	    setVisible(true);
	}

	public boolean isHighlighted() {
		return getStroke().equals(Color.RED);
	}
	public boolean isCovered(double startX, double startY, double width, double height) {
		return (getEndX() > startX && getEndX() < startX+width &&
						getEndY() > startY && getEndY() < startY+height &&
						getStartX() > startX && getStartX() < startX+width &&
						getStartY() > startY && getStartY() < startY+height);

	}
}
