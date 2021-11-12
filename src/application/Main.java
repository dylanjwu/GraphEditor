package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.event.EventHandler;


public class Main extends Application {

	private double nodeRadius = 15;
	
	private Pair<Double, Double> startPos;
	private Pair<Double, Double> endPos;

	@Override
	public void start(Stage primaryStage) {

		Group root = new Group();

		Scene scene = new Scene(root, 400, 400);
		
		scene.addEventHandler(MouseEvent.MOUSE_PRESSED, 
				new EventHandler<MouseEvent>(){
			
					@Override
					public void handle(MouseEvent event) {
						System.out.println("Mouse pressed");
						Circle circle = new Circle(event.getX(), event.getY(), nodeRadius);
						circle.setStroke(Color.BLACK);
						circle.setFill(Color.TRANSPARENT);
						circle.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {

							@Override
							public void handle(MouseEvent arg0) {
								System.out.println("Hovering over node");
								event.consume();
							}
							
						});
						root.getChildren().add(circle);
						startPos = new Pair<>(event.getX(), event.getY());
					}
		});

		scene.addEventHandler(MouseEvent.MOUSE_DRAGGED, 
				new EventHandler<MouseEvent>(){

					@Override
					public void handle(MouseEvent event) {
					}
		});

		scene.addEventHandler(MouseEvent.MOUSE_RELEASED, 
				new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				System.out.println("Mouse releasted");
				endPos = new Pair<>(event.getX(), event.getY());
				Line line = new Line(startPos.getKey(), startPos.getValue(), endPos.getKey(), endPos.getValue());
				System.out.println(startPos.getKey() + ", " + endPos.getKey());
				line.setStroke(Color.BLACK);
				root.getChildren().add(line);
				
			}
		});

		primaryStage.setTitle("draw line");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}


}

