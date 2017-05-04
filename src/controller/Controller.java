package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
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
	private static Controller instance=null;
	public static int state = 0;
	// 0 = initial
	// 1 = on drag une shape
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

	public static Controller getInstance(){
		if(instance==null)
			instance=new Controller();
		return instance;
	}
	
	public Controller() {
		m = Model.getInstance();
	}

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

		m.CreateRect(20, 20, new Point2D((shapePane.getPrefWidth() - 20) / 2, 1), Color.PINK);
		m.CreatePoly(6, 15, new Point2D((shapePane.getPrefWidth() - 20) / 2, 22), Color.PINK);

		refreshShapePane();

		// new CreateGroup(m, dropPane, shapePane, toolBar.getPrefHeight());
	}

	public void refreshShapePane() {
		for (Shape s : m.getGroup().getShapes()) {
			shapePane.getChildren().clear();
			if (s instanceof model.Rectangle) {
				System.out.println("first rect");
				model.Rectangle rModel = (model.Rectangle) s;
				Rectangle r = new Rectangle(rModel.getPosition().getX(), rModel.getPosition().getY(),
						rModel.getLargeur(), rModel.getHauteur());
				r.setFill(rModel.getCouleur());
				shapePane.getChildren().add(r);
				new DragAndDrop(r, dropPane, m);
			} else if (s instanceof model.Polygone) {
				model.Polygone pModel = (model.Polygone) s;
				/*
				 * for (double d : pModel.getPoints()) { System.out.println(d);
				 * }
				 */
				Polygon p = new Polygon(pModel.getPoints());
				p.setFill(pModel.getCouleur());
				shapePane.getChildren().add(p);
				new DragAndDrop(p, dropPane, m);
			} else {
				throw new TypeNotPresentException("erreur type de forme shapePane", null);
			}
		}
	}
	
	public void refreshDropPane() {
		for (Shape s : m.getGroup().getShapes()) {
			dropPane.getChildren().clear();
			if (s instanceof model.Rectangle) {
				model.Rectangle r = (model.Rectangle)s;
				Rectangle newShape = new Rectangle(r.getLargeur(), r.getHauteur(),r.getPosition().getX(),r.getPosition().getY());
				newShape.setOnMouseDragged(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						Controller.state = 5;
						newShape.setX(event.getX());
						newShape.setY(event.getY());
					}
				});
				dropPane.getChildren().add(newShape);
			} else if (s instanceof model.Polygone) {
				model.Polygone p = (model.Polygone)s;
				Polygon newShape = new Polygon(p.getPoints());
				newShape.relocate(p.getPosition().getX(), p.getPosition().getY());
				newShape.setOnMouseDragged(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						Controller.state = 5;
						newShape.relocate(event.getX(), event.getY());
					}
				});
				dropPane.getChildren().add(newShape);
			} else {
				throw new TypeNotPresentException("erreur type de forme dropPane", null);
			}
		}
	}

}