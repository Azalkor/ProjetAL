package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
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
	public static int state = 0;
	//0 = initial
	//1 = on drag une forme
	//2 = shape posée
	//3 = sélection commencée
	//4 = sélection faite, un carré existe
	//5 = on déplace une forme
	
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
	@FXML
	private ScrollPane shapePane;
	@FXML
	private ToolBar toolBar;
	
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
			System.out.println("Liste Avant Undo\n");
			m.Liste();
			m.Undo();
			System.out.println("Liste Apr�s\n");
			m.Liste();
		});
		
		buttonRedo.setOnAction((event) -> {
			System.out.println("Button Action\n");
			m.Redo();
		});
		
		buttonDelete.setOnAction((event) -> {
			System.out.println("Button Action\n");
		});
		
		new DragAndDrop(rectangleFabrique,dropPane,m);
		new CreateGroup(m, dropPane, shapePane.getPrefWidth(),toolBar.getPrefHeight());
	}
	
}