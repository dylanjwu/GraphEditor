package application;

import java.awt.TextField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import application.controller.Controller;
import application.controller.MainController;
import application.model.DefaultGraph;
import application.model.Graph;
import application.view.GraphEditorView;
import application.view.GraphEditorViewImpl;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

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
	
	
	@Override
	public void start(Stage primaryStage) {
		Graph model = new DefaultGraph();
		GraphEditorView view = new GraphEditorViewImpl(model);

		Controller controller = new MainController(view, model);
		
		MenuBar menu = new MenuBar();
		menu.setPrefSize(600, 29);
		menu.setLayoutX(-2);
		menu.setLayoutY(1);

		VBox controls = new VBox();
		controls.setPrefSize(129, 352);
		controls.setPadding(new Insets(10,10,10,10));
		controls.setSpacing(15);

		controls.getChildren().add(new ToggleButton("Undirected"));
		controls.getChildren().add(new ToggleButton("Weighted"));

		ToggleButton addBtn = new ToggleButton("Add");
		addBtn.setOnMousePressed(e -> controller.changeMode("add"));
		controls.getChildren().add(addBtn);

		ToggleButton delBtn = new ToggleButton("Delete");
		delBtn.setOnMousePressed(e -> controller.changeMode("delete"));
		controls.getChildren().add(delBtn);

		ToggleButton moveBtn = new ToggleButton("Move");
		moveBtn.setOnMousePressed(e -> controller.changeMode("move"));
		controls.getChildren().add(moveBtn);
		
		
		StackPane stackpane = new StackPane();
		Scene scene = new Scene(stackpane, 800, 800);
		stackpane.getChildren().addAll(controls, (Pane)view);
		scene.setOnKeyPressed(e -> { if (e.getCode() == KeyCode.D) controller.changeMode("move"); });
		primaryStage.setTitle("Graph Editor");
		primaryStage.setScene(scene);
		primaryStage.show();

	
	}

	public static void main(String[] args) {
		launch(args);
	}

}

