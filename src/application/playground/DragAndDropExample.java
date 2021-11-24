package application.playground;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Pair;

class SpecialCircle extends Circle {
	
	private static Pair<Double, Double> start;
	private static Pane root = null;
	
	public static void setRoot(Pane pane) {
		if (root == null) {
			root = pane;
		}
	}
	
	public SpecialCircle() {

		this.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                if (event.getGestureSource() != this) {
                    event.acceptTransferModes(TransferMode.LINK);
                }
                event.consume();
            }
        });

        this.setOnDragDetected((MouseEvent event) -> {
            System.out.println("Circle 1 drag detected");

            start = new Pair<>(event.getX(), event.getY());
            Dragboard db = this.startDragAndDrop(TransferMode.LINK);
            ClipboardContent content = new ClipboardContent();
            content.putString("");
            db.setContent(content);

        });

        
        this.setOnDragDropped((DragEvent event) -> {
			event.setDropCompleted(true);
			Line line = new Line(start.getKey(), start.getValue(), event.getX(), event.getY());
			root.getChildren().add(line);
            event.consume();
        });

	}
}

public class DragAndDropExample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
    	
        Pane pane = new Pane();
        SpecialCircle.setRoot(pane);

        Circle circle = createCircle("#ff00ff", "#ff88ff",100);

        Circle circle2 = createCircle("#00ffff", "#88ffff",300);

      
        pane.getChildren().add(circle);
        pane.getChildren().add(circle2);

        Scene scene = new Scene(pane, 1024, 800, true);
        primaryStage.setScene(scene);
        primaryStage.setTitle("2D Example");

        primaryStage.show();
    }

    private Circle createCircle(String strokeColor, String fillColor, double x) {
        Circle circle = new SpecialCircle();
        circle.setCenterX(x);
        circle.setCenterY(200);
        circle.setRadius(50);
        circle.setStroke(Color.valueOf(strokeColor));
        circle.setStrokeWidth(5);
        circle.setFill(Color.valueOf(fillColor));
        return circle;
    }
}
