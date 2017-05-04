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

public class DragAndDrop {

	public DragAndDrop(Shape s, Pane dropPane, Model m) {
		s.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				System.out.println("pd");
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
					System.out.println("forme créée");
					Point2D dropPos = new Point2D(event.getX(), event.getY());
					if (s instanceof Rectangle) {
						Rectangle r=(Rectangle)s;
						m.CreateRect((float) r.getWidth(), (float) r.getHeight(),
								new Point2D(dropPos.getX(), dropPos.getY()),
								new Point2D(dropPos.getX() + r.getWidth() / 2, dropPos.getY() + r.getHeight() / 2),
								Color.BLACK);
						Rectangle newShape = new Rectangle(r.getWidth(), r.getHeight());
						newShape.setX(dropPos.getX());
						newShape.setY(dropPos.getY());
						newShape.setOnMouseDragged(new EventHandler<MouseEvent>() {
							public void handle(MouseEvent event) {
								Controller.state = 5;
								newShape.setX(event.getX());
								newShape.setY(event.getY());
							}
						});
						dropPane.getChildren().add(newShape);
					}
					else if(s instanceof Polygon){
						Polygon newShape=(Polygon)s;
						dropPane.getChildren().add(newShape);
					}
					event.consume();
				}
			}
		});
	}
}
