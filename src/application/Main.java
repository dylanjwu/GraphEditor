package application;
	
import java.awt.TextField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.event.EventHandler;

class Edge extends Line {
	public Edge(double x1, double y1, double x2, double y2) {
		super(x1, y1, x2, y2);
	}
	
}

enum Modes {
	ADD, DELETE, MOVE;
}

/**
 * 
 * If ADD, 
 * 	(selections can exist but cannot be created)
 * 
 * 	CANVAS:
 * 		Clicking on canvas creates a a node
 * 	GRAPHNODE:
 * 		Dragging from a node and dropping on a different node creates an edge
 * 		Clicking on an existing node does nothing	
 *  EDGE:
 * 		Clicking on an existing edge does nothing
 * 	SELECTION:
 * 		Clicking on a selection undoes the selection
 * 
 * If DELETE
 * 	(selections can exist but cannot be created)
 * 
 * 	CANVAS:
 * 		Clicking on canvas does nothing
 *  GRAPHNODE:
 * 		Clicking on a node deletes the node and any connected edges (from inEdges, and outEdges lists)
 *  EDGE:
 *  	Clicking on an edge deletes that edge
 * 	SELECTION:
 *  	Clicking on selection deletes all of the nodes and edges in the selection
 *  
 *  
 * If MOVE
 * 
 *  CANVAS:
 * 		Clicking on canvas undoes selection if it exists
 * 	GRAPHNODE:
 * 		Dragging a node moves that node and re-orients any connected edges (talks to EDGE)
 * 		Selection over a node highlights the node (becomes part of the selection)
 * 	SELECTION: 
 * 		Dragging on canvas creates a selection
 * 		Releasing a drag event preserves the new selection
 * 		Clicking on a selection does nothing (preserves selection)	
 * 		Dragging on any point in a selection moves the entire selection as if it were a single node
 */

/**
 * GRAPHNODE:
 *  	inEdges, outEdges, label, xPosition, yPosition
 *			 
 * (all edges in a graph must be the same kind)
 * 
 * DECORATOR PATTERN:
 * 
 * EDGE (UNDIRECTED and UNWEIGHTED by default):
 * 		source node, destination node, startXY, endXY
 * 
 * 		WEIGHTED_EDGE
 * 
 * 		DIRECTED_EDGE
 * 
 * (4 possibilities)
 * EDGE (unweighted, undirected)
 * EDGE, WEIGHTED_EDGE	(weighted, undirected)
 * EDGE, DIRECTED_EDGE (unweighted, directed)
 * EDGE, WEIGHTED_EDGE, DIRECTED_EDGE (weighted, directed)
 * 
 * 
 * COMPOSITE PATTERN: SELECTION and GRAPHNODE both implement a Draggable interface:
 * 		
 */



public class Main extends Application {
	
	private Modes currentMode = Modes.ADD;
	
	Map<GraphNode,  Vertex> nodeMap; 
	

	private double nodeRadius = 15;
	
	@Override
	public void start(Stage primaryStage) {

		nodeMap = new HashMap<>();
		
		Group root = new Group();

		Scene scene = new Scene(root, 400, 400);

		GraphNode.setRoot(root);

//		scene.setOnZoomStarted(evt -> System.out.println("ZOOM STARTED"));
//		scene.setOnZoom(evt -> {
//			System.out.println("ZOOMING");
//			double zoomFactor = 1.2;
//			root.setScaleX(root.getScaleX() * zoomFactor);
//            root.setScaleY(root.getScaleY() * zoomFactor);
//		});
//		
//		scene.setOnZoomFinished(evt -> System.out.println("ZOOM FINISHED"));

		scene.addEventHandler(MouseEvent.MOUSE_PRESSED, 
				new EventHandler<MouseEvent>(){
			
					@Override
					public void handle(MouseEvent event) {
						GraphNode graphNode = new GraphNode(event.getX(), event.getY(), nodeRadius);
						nodeMap.put(graphNode, new Vertex());
						root.getChildren().add(graphNode);
					}
		});
		

		primaryStage.setTitle("Graph Editor");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}

