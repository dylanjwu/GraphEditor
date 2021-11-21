package application;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Pair;

public class GraphNode extends Circle {
	private static Group root = null;

	private static Pair<Double, Double> startPos;
	private static Pair<Double, Double> endPos;

	private static GraphNode sourceNode = null;

	private List<Edge> inEdges = new ArrayList<>();
	private List<Edge> outEdges = new ArrayList<>();
	private boolean isSource = false;
	
//	private Edge currLine = null;
	public GraphNode(double x, double y, double radius) {
		super(x, y, radius);
		
		GraphNode thisNode = this;
		setStroke(Color.BLACK);
		setFill(Color.TRANSPARENT);

	    
		this.setOnMousePressed(e -> e.consume());
		
		
		this.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                if (event.getGestureSource() != this) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
              
                event.consume();
            }
        });

        this.setOnDragDetected((MouseEvent event) -> {

            startPos = new Pair<>(event.getX(), event.getY());
			sourceNode = thisNode;

            Dragboard db = this.startDragAndDrop(TransferMode.COPY_OR_MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString("");
            db.setContent(content);

        });

        
        this.setOnDragDropped((DragEvent event) -> {

        	if (event.getGestureSource() != this) {

				event.setDropCompleted(true);
				Edge edge = new Edge(startPos.getKey(), startPos.getValue(), event.getX(), event.getY());
				root.getChildren().add(edge);
				sourceNode.addOutEdge(edge);
				inEdges.add(edge);
				System.out.println(sourceNode);
				System.out.println(this);
				event.consume();
        	}
        });
		
	}

	public void addOutEdge(Edge edge) {
		outEdges.add(edge);
	}

	static void setRoot(Group rt) {
		if (root == null)
			root = rt;
	}

	@Override
	public String toString() {
		return "GraphNode [inEdges=" + inEdges.toString() + ", "
				+ "outEdges=" + outEdges.toString() + "]";
	}
	
}
