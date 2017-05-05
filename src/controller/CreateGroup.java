package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Model;
import model.Shape;
import model.ShapeGroup;

public class CreateGroup {

	static Rectangle selectZone;
	ShapeGroup selectedShapes = new ShapeGroup();

	public CreateGroup(Model m, Pane p, Pane sPane, double yOffset) {

		p.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.PRIMARY) {
					if (selectZone == null || !selectZone.contains(new Point2D(event.getX(), event.getY()))) {
						if (!(Controller.state == 5)) {
							Controller.state = 3;
							selectZone = new Rectangle(event.getX(), event.getY(), 0, 0);
							selectZone.setFill(Color.TRANSPARENT);
							selectZone.setStroke(Color.TRANSPARENT);
							selectZone.setStrokeWidth(1);
							selectZone.toFront();
							p.getChildren().addAll(selectZone);
						}

					}
				}
			}
		});
		p.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if (!(Controller.state == 5))
					if (Controller.state == 3) {
						selectZone.setWidth(event.getX() - selectZone.getX());
						selectZone.setHeight(event.getY() - selectZone.getY());
						selectZone.setStroke(Color.BLACK);
					}
			}

		});
		p.setOnMouseReleased(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.PRIMARY && Controller.state == 3) {
					Controller.state = 4;
					for (Shape s : m.getGroup().getShapes()) {
						if (selectZone.contains(s.getCentre())) {
							selectedShapes.addShape(s);
							// m.getGroup().getShapes().remove(s);
						}
					}
					if (!selectedShapes.getShapes().isEmpty()) {
						selectedShapes = new ShapeGroup((float) selectZone.getWidth(), (float) selectZone.getHeight(),
								new Point2D(selectZone.getX(), selectZone.getY()));
						selectZone.setUserData(Controller.id);
						selectedShapes.putId();
						Controller.id++;
						m.getGroup().addShape(selectedShapes);
						m.getGroup().liste();
					} else {
						p.getChildren().remove(selectZone);
					}
					selectZone.setOnDragDetected(new EventHandler<MouseEvent>() {
						public void handle(MouseEvent event) {
							Dragboard db = selectZone.startDragAndDrop(TransferMode.ANY);
							ClipboardContent content = new ClipboardContent();
							content.putString("");
							db.setContent(content);
							WritableImage wi = new WritableImage((int) selectZone.getWidth(),
									(int) selectZone.getHeight());
							WritableImage wi2 = new WritableImage((int) selectZone.getWidth(),
									(int) selectZone.getHeight());
							SnapshotParameters sp = new SnapshotParameters();
							sp.setViewport(new Rectangle2D(selectZone.getX() + sPane.getPrefWidth() + 20,
									selectZone.getY() + yOffset, 1000, 1000));
							p.snapshot(sp, wi2);
							db.setDragView(wi2);
							sp.setFill(Color.TRANSPARENT);
							p.snapshot(sp, wi);
							Rectangle rTest = new Rectangle(wi.getWidth(), wi.getHeight());
							ImagePattern ip = new ImagePattern(wi);
							rTest.setFill(ip);
							// sPane.getChildren().add(rTest);
							event.consume();
						}
					});
					selectZone.setOnMousePressed(new EventHandler<MouseEvent>() {
						public void handle(MouseEvent event) {
							if (event.getButton() == MouseButton.SECONDARY) {
								System.out.println("yolo");
								TextField textField = new TextField("Type Something");
								final ContextMenu contextMenu = new ContextMenu();
								MenuItem couleur = new MenuItem("Couleur");
								MenuItem arrondi = new MenuItem("Arrondir bords");
								MenuItem rotation = new MenuItem("Rotation");
								MenuItem largeur = new MenuItem("Largeur");
								MenuItem hauteur = new MenuItem("Hauteur");
								MenuItem suppr = new MenuItem("Supprimer");
								contextMenu.getItems().addAll(couleur, arrondi, rotation, largeur, hauteur,suppr);
								couleur.setOnAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent event) {
										System.out.println("kek");
										final ColorPicker colorPicker1 = new ColorPicker();
										p.getChildren().add(colorPicker1);
										System.out.println("TOPKEK");
										colorPicker1.setOnAction(e -> {
											Color color = colorPicker1.getValue();
											System.out.println(color);
											selectedShapes.setCouleur(color);
											for (Shape s : selectedShapes.getShapes()) {
												for (Node n : p.getChildren()) {
													if (n instanceof javafx.scene.shape.Shape) {
														javafx.scene.shape.Shape shape = (javafx.scene.shape.Shape) n;
														if (s.getId() == (int) shape.getUserData())
															shape.setFill(s.getCouleur());
													}
												}
											}
											p.getChildren().remove(colorPicker1);
										});
									}
								});
								suppr.setOnAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent event) {
										m.getInstance().getGroup().getShapes().remove(selectedShapes);
									}
								});
								// ...
								textField.setContextMenu(contextMenu);
								contextMenu.show(selectZone, event.getScreenX(), event.getScreenY());
							}
							event.consume();
						}
					});
				}
				event.consume();
			}
		});
	}
}
