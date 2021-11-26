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
import javafx.scene.shape.Circle;


public class AddController implements ModeController {

	private GraphEditorView view;
	private Graph model;
	private Map<Circle, Vertex> nodeMap;
	public AddController(GraphEditorView view, Map<Circle, Vertex> nodeMap, Graph model) {
		this.model = model;
		this.view = view;
		this.nodeMap = nodeMap;
		view.setModeController(this);
	}
	
	@Override
	public void addCanvasPressHandler(Node node) {
		node.setOnMousePressed(event -> ((GraphEditorView)node).addNode(event.getX(), event.getY()));
	}

	
	@Override
	public void addNodeDragHandler(Circle node) {  
		//add node to new vertex in nodeMap and model (model)
		Vertex newVertex = new Vertex();
		model.addVertex(newVertex);
		nodeMap.put(node, newVertex);

		System.out.println(model);
		System.out.println("nodeMap key set: " + nodeMap.entrySet().toString());

		node.setOnMousePressed(e -> e.consume());
		node.setOnDragDropped(
			new EventHandler<DragEvent>() {

				@Override
				public void handle(DragEvent event) {
					if (event.getGestureSource() != event.getSource()) {
						event.setDropCompleted(true);

						if (view.getCurrentSourceNode() != null) {
							Circle source = (Circle)view.getCurrentSourceNode();
							Circle dest = (Circle)event.getSource();
							
							// Add edge to the source vertex in model (SHOULD CHANGE IN nodeMap too)
							model.addEdge(nodeMap.get(source), nodeMap.get(dest));
							System.out.println(model);
							System.out.println("nodeMap key set: " + nodeMap.entrySet().toString());
//							nodeMap.get(source).addEdge(nodeMap.get(dest));

							view.addEdge(source, dest);
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
	

	@Override
	public void onClickEdge() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onClickGroup() {
		// TODO Auto-generated method stub
		
	}

	// TODO make an event handler, so that we can add a new node to the view, and to the model as well
	@Override
	public void onClickCanvas() {
		view.addNode(0, 0);
	}

	@Override
	public void onDragCanvas() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDragGroup() {
		// TODO Auto-generated method stub
		
	}

}
