package application.controller;

import application.model.Graph;
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
	public AddController(GraphEditorView view, Graph model) {
		this.model = model;
		this.view = view;
		view.setModeController(this);
	}
	
	@Override
	public void addCanvasPressHandler(Node node) {
		node.setOnMousePressed(event -> ((GraphEditorView)node).addNode(event.getX(), event.getY()));
	}

	
	@Override
	public void addNodeDragHandler(Circle node) {  
		node.setOnMousePressed(e -> e.consume());
		node.setOnDragDropped(
			new EventHandler<DragEvent>() {

				@Override
				public void handle(DragEvent event) {
					if (event.getGestureSource() != event.getSource()) {
						event.setDropCompleted(true);

						if (view.getCurrentSourceNode() != null) {
							view.addEdge((Circle)view.getCurrentSourceNode(), (Circle)event.getSource());
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
