package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import model.Model;

/**
 * View-Controller for the person table.
 * 
 * @author Marco Jakob
 */
public class Controller {
	
	Model m;
	
	@FXML
	private Button buttonSaveAs;
	@FXML
	private Button buttonLoad;
	@FXML
	private Button buttonUndo;
	@FXML
	private Button buttonRedo;
	@FXML
	private Button buttonDelete;
	@FXML
	private Rectangle rectangleFabrique;
	@FXML
	private Pane dropPane;
	
	/**
	 * The constructor (is called before the initialize()-method).
	 */
	public Controller() {
		m = new Model();
	}
	
	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Handle Button event.
		buttonSaveAs.setOnAction((event) -> {
			System.out.println("Button Action\n");
		});
		
		buttonLoad.setOnAction((event) -> {
			System.out.println("Button Action\n");
		});
		
		buttonUndo.setOnAction((event) -> {
			System.out.println("Button Action\n");
		});
		
		buttonRedo.setOnAction((event) -> {
			System.out.println("Button Action\n");
		});
		
		buttonDelete.setOnAction((event) -> {
			System.out.println("Button Action\n");
		});
		
		new DragAndDrop(rectangleFabrique,dropPane,m);
	}
	
}