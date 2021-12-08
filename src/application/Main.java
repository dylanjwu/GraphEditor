package application;

import application.controller.Controller;
import application.controller.MainController;
import application.model.DefaultGraph;
import application.model.Graph;
import application.view.GraphEditorView;
import application.view.GraphEditorViewImpl;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

/**
 * 
 * @author Dylan Wu
 * CS5010 v1 Fall 2021 - Final Project
 *  
 */


public class Main extends Application {
	

	private void showNewChooser(Stage primaryStage) {
		
		Dialog<String> dialog = new Dialog<>();
		DialogPane pane = new DialogPane();
		pane.getChildren().add(new ChoiceBox<String>());
		dialog.setDialogPane(pane);
		dialog.showAndWait();
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.getExtensionFilters().addAll(
//			 new FileChooser.ExtensionFilter("Text Files", "*.txt")
//        );
//        
//        fileChooser.showSaveDialog(primaryStage);


		// https://code.makery.ch/blog/javafx-dialogs-official/
//		 dialog = new TextInputDialog("walter");
//		dialog.setTitle("Create new graph");
//		dialog.setHeaderText("Create new graph project");
//		dialog.setContentText("enter filename");
//
//		// Traditional way to get the response value.
//		Optional<String> result = dialog.showAndWait();
//		if (result.isPresent()){
//			System.out.println("Your file: " + result.get());
//		}
//
//		// The Java 8 way to get the response value (with lambda expression).
//		result.ifPresent(name -> System.out.println("Your name: " + name));
		
	}
	
	private void showExistingChooser(Stage primaryStage) {
		FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
			 new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );
        
//        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        fileChooser.showOpenDialog(primaryStage);

	}
	
	@Override
	public void start(Stage primaryStage) {
		Graph model = new DefaultGraph();
		GraphEditorView view = new GraphEditorViewImpl(model);
		Controller controller = new MainController(view, model);

		Menu m = new Menu("File");
		  
		// create menuitems
		MenuItem m1 = new MenuItem("Create new");
//		m1.setOnAction(e -> showNewChooser(primaryStage));
//
		MenuItem m2 = new MenuItem("Choose existing");
//		m2.setOnAction(e -> showExistingChooser(primaryStage));

		MenuItem m3 = new MenuItem("Save");
		MenuItem m4 = new MenuItem("Save As");
		MenuItem m5 = new MenuItem("Undo");
		MenuItem m6 = new MenuItem("Redo");
		MenuItem m7 = new MenuItem("Copy");
		MenuItem m8 = new MenuItem("Paste");


		// add menu items to menu
		m.getItems().addAll(m1, m2, m3, m4, m5, m6, m7, m8);
  
		// create a menubar
		MenuBar mb = new MenuBar();
  
		// add menu to menubar
		mb.getMenus().add(m);


		VBox controls = new VBox(mb);
		controls.setPrefSize(129, 352);
		controls.setPadding(new Insets(10,10,10,10));
		controls.setSpacing(15);

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


		scene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.M) 
				controller.changeMode("move"); 

			else if (e.getCode() == KeyCode.D) 
				controller.changeMode("delete"); 

			else if (e.getCode() == KeyCode.A) 
				controller.changeMode("add"); 
		});


		primaryStage.setTitle("Graph Editor");
		primaryStage.setScene(scene);
		primaryStage.show();

	
	}

	public static void main(String[] args) {
		launch(args);
	}

}

