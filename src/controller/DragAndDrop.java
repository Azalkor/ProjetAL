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
import javafx.scene.shape.Rectangle;
import model.Model;

public class DragAndDrop {
	
	public DragAndDrop(Rectangle rectangleFabrique, Pane dropPane, Model m) {
		rectangleFabrique.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				Controller.state=1;
				Dragboard db = rectangleFabrique.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
				content.putString("");
				db.setContent(content);
				WritableImage wi = rectangleFabrique.snapshot(null, null);
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
				if (Controller.state!=4){
					Controller.state=2;
					System.out.println("carré créé");
					Point2D dropPos = new Point2D(event.getX(), event.getY());
					m.CreateRect((float) rectangleFabrique.getWidth(), (float) rectangleFabrique.getHeight(),
							new Point2D(dropPos.getX(), dropPos.getY()),
							new Point2D(dropPos.getX() + rectangleFabrique.getWidth() / 2,
									dropPos.getY() + rectangleFabrique.getHeight() / 2),
							Color.BLACK);
					Rectangle newRect = new Rectangle(rectangleFabrique.getWidth(), rectangleFabrique.getHeight());
					newRect.setX(dropPos.getX());
					newRect.setY(dropPos.getY());
					newRect.setOnMouseDragged(new EventHandler<MouseEvent>() {
						public void handle(MouseEvent event){
							Controller.state = 5;
							newRect.setX(event.getX());
							newRect.setY(event.getY());
						}
					});
					dropPane.getChildren().add(newRect);
					event.consume();
				}
			}
		});
	}
}
