package controller;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import model.Model;
import model.Shape;

public class Controller {

	Model m;
	private static Controller instance = null;
	public static int state = 0;
	public static int id = 0;
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

	public static Controller getInstance() {
		if (instance == null)
			instance = new Controller();
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
			m.Undo();
		});

		buttonRedo.setOnAction((event) -> {
			m.Redo();
		});

		buttonDelete.setOnAction((event) -> {
			System.out.println("Button Action\n");
		});

		m.AddRectToolBar(20, 20, new Point2D((shapePane.getPrefWidth() - 20) / 2, 5), Color.RED);
		Controller.id++;
		m.AddPolyToolbar(6, 15, new Point2D((shapePane.getPrefWidth()) / 2, 50), Color.BLUE);
		Controller.id++;

		refreshShapePane();

		new CreateGroup(m, dropPane, shapePane, toolBar.getPrefHeight());
	}

	public void refreshShapePane() {
		shapePane.getChildren().clear();
		for (Shape s : m.getToolbar().getShapes()) {
			if (s instanceof model.Rectangle) {
				// System.out.println("first rect");
				model.Rectangle rModel = (model.Rectangle) s;
				Rectangle r = new Rectangle(rModel.getPosition().getX(), rModel.getPosition().getY(),
						rModel.getLargeur(), rModel.getHauteur());
				r.setFill(rModel.getCouleur());
				r.setUserData(rModel.getId());
				shapePane.getChildren().add(r);
				new DragAndDrop(r, dropPane, m);
			} else if (s instanceof model.Polygone) {
				model.Polygone pModel = (model.Polygone) s;
				Polygon p = new Polygon(pModel.getPoints());
				p.setFill(pModel.getCouleur());
				p.setUserData(pModel.getId());
				shapePane.getChildren().add(p);
				new DragAndDrop(p, dropPane, m);
			} else {
				throw new TypeNotPresentException("erreur type de forme shapePane", null);
			}
		}
	}

	public void refreshObserver() {

		refreshDropPane(dropPane);
	}

	public void refreshDropPane(Pane dropPane) {
		for (model.Shape s : m.getGroup().getShapes()) {

			recur(s, dropPane);
		}
	}

	public void recur(Shape s, Pane dropPane) {
		for (Node sfx : dropPane.getChildren()) {
			if (sfx instanceof javafx.scene.shape.Shape) {
				javafx.scene.shape.Shape tmp = (javafx.scene.shape.Shape) sfx;
				if (s.getId() == (int) tmp.getUserData()) {
					if (tmp instanceof Rectangle) {
						model.Rectangle sCast = (model.Rectangle) s;
						Rectangle tmpCast = (Rectangle) tmp;
						tmpCast.setX(sCast.getPosition().getX());
						tmpCast.setY(sCast.getPosition().getY());
						tmpCast.setWidth(sCast.getLargeur());
						tmpCast.setHeight(sCast.getHauteur());
						tmpCast.setFill(sCast.getCouleur());
						if (sCast.isBordRond())
							tmpCast.setArcWidth(20);
						else
							tmpCast.setArcWidth(0);
					}
				}
			}
		}
	}

}