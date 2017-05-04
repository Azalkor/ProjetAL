package controller;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import model.Model;
import model.Shape;

/**
 * View-Controller for the person table.
 * 
 * @author Marco Jakob
 */
public class Controller {

	Model m;
	public static int state = 0;
	// 0 = initial
	// 1 = on drag une forme
	// 2 = shape posée
	// 3 = sélection commencée
	// 4 = sélection faite, un carré existe
	// 5 = on déplace une forme

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
	private Rectangle rectangleInitial;
	@FXML
	private Polygon polygoneInitial;
	@FXML
	private Pane dropPane;
	@FXML
	private Pane shapePane;
	@FXML
	private ToolBar toolBar;

	/**
	 * The constructor (is called before the initialize()-method).
	 */
	public Controller() {
		m = Model.getInstance();		
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
			System.out.println("Liste Après\n");
			m.Liste();
		});

		buttonRedo.setOnAction((event) -> {
			System.out.println("Button Action\n");
			m.Redo();
		});

		buttonDelete.setOnAction((event) -> {
			System.out.println("Button Action\n");
		});
		
		m.CreateRect(20, 20, new Point2D((shapePane.getPrefWidth()-20)/2,1), new Point2D(50,50), Color.BLACK);//gaffe

		for (Shape s : m.getGroup().getShapes()) {
			if (s instanceof model.Rectangle){
				model.Rectangle rModel = (model.Rectangle)s;
				Rectangle r = new Rectangle(rModel.getPosition().getX(),rModel.getPosition().getY(),
											rModel.getLargeur(),rModel.getHauteur());
				shapePane.getChildren().add(r);
				new DragAndDrop(r, dropPane, m);
			}
			else if(s instanceof model.Polygone){
				model.Polygone pModel = (model.Polygone)s;
				Polygon p = new Polygon(pModel.getPoints());
				new DragAndDrop(p, dropPane, m);
			}
			else{
				throw new TypeNotPresentException("erreur type de forme", null);
			}
		}

		new CreateGroup(m, dropPane, shapePane, toolBar.getPrefHeight());
	}

}