package controller;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import model.Model;
import model.Polygone;

public class DragAndDrop {

	public DragAndDrop(Shape s, Pane dropPane, Model m) {
		s.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				Controller.state = 1;
				Dragboard db = s.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
				content.putString("");
				db.setContent(content);
				WritableImage wi = s.snapshot(null, null);
				db.setDragView(wi);
				event.consume();
			}
		});

		dropPane.setOnDragOver(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				if (event.getGestureSource() != dropPane && event.getDragboard().hasString()) {
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
				event.consume();
			}
		});
		dropPane.setOnDragDropped(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				if (Controller.state != 4) {
					Controller.state = 2;
					Point2D dropPos = new Point2D(event.getX(), event.getY());
					
					if (event.getGestureSource() instanceof Rectangle) {
						Rectangle r = (Rectangle) event.getGestureSource();
						m.DrawRect((float) r.getWidth(), (float) r.getHeight(), dropPos, (Color) r.getFill());
						Rectangle newShape = new Rectangle(dropPos.getX(), dropPos.getY(), r.getWidth(),r.getHeight());
						newShape.setFill(r.getFill());
							newShape.setOnMouseDragged(new EventHandler<MouseEvent>() {
								public void handle(MouseEvent event) {
									Controller.state = 5;
									newShape.setX(event.getX());
									newShape.setY(event.getY());
								}
							});
						dropPane.getChildren().add(newShape);
					} else if (event.getGestureSource() instanceof Polygon) {
						Polygon p = (Polygon) event.getGestureSource();
						model.Polygone tmp = m.DrawPoly(p.getPoints().size() / 2,
											(int) new Point2D(p.getPoints().get(0), p.getPoints().get(1))
											.distance(p.getPoints().get(2), p.getPoints().get(3)),
											dropPos, (Color) p.getFill());
						System.out.println(tmp.getPoints());
						Polygon newShape = new Polygon(tmp.getPoints());
						newShape.setFill(p.getFill());
							newShape.setOnMouseDragged(new EventHandler<MouseEvent>() {
								public void handle(MouseEvent event) {
									Controller.state = 5;
									Polygone tmp = new Polygone(newShape.getPoints().size() / 2,
											(int) new Point2D(newShape.getPoints().get(0), newShape.getPoints().get(1))
											.distance(newShape.getPoints().get(2), newShape.getPoints().get(3)),
											new Point2D(event.getX(),event.getY()), (Color) p.getFill());
									Double[] tab = new Double[tmp.getPoints().length];
									for(int i=0;i<tmp.getPoints().length;i++)
										tab[i]=tmp.getPoints()[i];
									newShape.getPoints().setAll(tab);
							}
						});
						dropPane.getChildren().add(newShape);
					}
					event.consume();
				}
			}
		});
	}
}
