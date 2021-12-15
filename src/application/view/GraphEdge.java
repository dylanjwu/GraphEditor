package application.view;

import application.view.visitor.GraphVisitor;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 * View class for the edge, as a Line, is visited by graph visitor
 * @author Dylan Wu
 *
 */

public class GraphEdge extends Line implements GraphObject {

	public GraphEdge(GraphNode src, GraphNode dst) {
		super(src.getCenterX(), src.getCenterY(), dst.getCenterX(), dst.getCenterY());
		
		/** make the line stick to its two src and dst nodes */
		startXProperty().bind(src.layoutXProperty());
		startYProperty().bind(src.layoutYProperty());
		endXProperty().bind(dst.layoutXProperty());
		endYProperty().bind(dst.layoutYProperty());

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

	@Override
	public void accept(GraphVisitor visitor) {
		visitor.visitEdge(this);
	}
}
