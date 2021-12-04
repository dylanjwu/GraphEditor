package application.controller;

import java.util.Map;

import application.model.Graph;
import application.model.Vertex;
import application.view.GraphEditorView;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Pair;


public class AddController extends AbstractModeController {

	public AddController(GraphEditorView view, Map<Circle, Vertex> nodeMap, Map<Line, Pair<Circle, Circle>> edgeMap, Graph model) {
		this.model = model;
		this.view = view;
		this.nodeMap = nodeMap;
		this.edgeMap = edgeMap;
		view.setModeController(this);
		
		for (Circle node : nodeMap.keySet())
			addNodeHandlers(node);

	}
	
	@Override
	public void addCanvasPressHandler(Node node) {
		node.setOnMousePressed(e -> {
			unselectAllNodes();
			((GraphEditorView)node).addNode(e.getX(), e.getY()); /** overrided part: add node to canvas */
		});
	}

	
	@Override
	public void addNodeHandlers(Circle node) {  
		//add node to new vertex in nodeMap and model (model)
		
		if (nodeMap.get(node) == null) { /** add vertex to model if node is newly created (in view)*/
			Vertex newVertex = new Vertex();
			model.addVertex(newVertex);
			nodeMap.put(node, newVertex);

			System.out.println(model);
			System.out.println("nodeMap key set: " + nodeMap.entrySet().toString());
		}

		node.setOnMouseDragged(null); //necessary if coming from move controller
		node.setOnMousePressed(e -> {
			unselectAllNodes();
			view.highlightNode((Node)e.getSource());
			e.consume();
		});
		
		node.setOnDragDropped(
			new EventHandler<DragEvent>() {

				@Override
				public void handle(DragEvent event) {
					if (event.getGestureSource() != event.getSource()) {
						event.setDropCompleted(true);

						if (view.getCurrentSourceNode() != null) {
							Circle source = (Circle)view.getCurrentSourceNode();
							Circle dest = (Circle)event.getSource();

							if (!edgeExists(source, dest)) {
						
								view.highlightNode(dest);
								System.out.println(model);
								System.out.println("nodeMap key set: " + nodeMap.entrySet().toString());

								view.addEdge(source, dest);
							}
						}
						event.consume();
					}
				}
		});
	
		node.setOnDragOver(
			new EventHandler<DragEvent>() {
				public void handle(DragEvent event) {
					if (event.getGestureSource() != event.getSource()) {
						event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
					}
					event.consume();
				}
		});

       node.setOnDragDetected((MouseEvent event) -> {

    	   	view.setCurrentSourceNode((Circle)event.getSource()); /** set the node to be the source node */

            Dragboard db = ((Node) event.getSource()).startDragAndDrop(TransferMode.COPY_OR_MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString( "");
            db.setContent(content);
        });
	}
	
	private boolean edgeExists(Circle source, Circle dest) {

		for (Pair<Circle, Circle> pair : edgeMap.values()) {
			if (pair.getKey().equals(source) && pair.getValue().equals(dest))
				return true;
		}
		return false;
	}

	@Override
	public void addEdgeEventHandlers(Line edge, Circle source, Circle dest) {

		if (!edgeExists(source, dest)) {
			// Add edge to the source vertex in model (SHOULD CHANGE IN nodeMap too)
			model.addEdge(nodeMap.get(source), nodeMap.get(dest));
			edgeMap.put(edge, new Pair<>(source, dest));
		}

		edge.setOnMousePressed(e -> {
			unselectAllNodes();
			view.highlightNode((Node)e.getSource());
			e.consume();
		});	

		System.out.println("EDGE MAP in add edge handler: " + edgeMap.values());
	}

}
