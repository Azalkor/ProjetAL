package controller;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Model;
import model.Shape;
import model.ShapeGroup;

public class CreateGroup {
	
	static Rectangle selectZone;
	ShapeGroup selectedShapes = new ShapeGroup();
	
	public CreateGroup(Model m, Pane p, double xOffset, double yOffset){
		
		p.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
				if(selectZone!=null){
					if (!selectZone.contains(new Point2D(event.getX(),event.getY()))){
						p.getChildren().remove(selectZone);
						selectedShapes.getShapes().clear();
					}
					else
						return;
				}
				if(!(Controller.state==5)){
					Controller.state=3;
					selectZone = new Rectangle(event.getX(),event.getY(),0,0);
		            selectZone.setFill(Color.TRANSPARENT);
		            selectZone.setStroke(Color.TRANSPARENT);
		            selectZone.setStrokeWidth(1);
		            selectZone.toFront();
		            p.getChildren().addAll(selectZone);
				}
			}
		});
		p.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if(!(Controller.state==5))
					if(Controller.state==3){
						System.out.println("getX : "+event.getX());
						selectZone.setWidth(event.getX()-selectZone.getX());
						selectZone.setHeight(event.getY()-selectZone.getY());
			            selectZone.setStroke(Color.BLACK);
					}
			}
			
		});
		p.setOnMouseReleased(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				Controller.state=4;
				for (Shape s : m.group.getShapes()) {
					if(selectZone.contains(s.getCentre())){
						selectedShapes.addShape(s);
					}
				}
				if(!selectedShapes.getShapes().isEmpty()){
					if(selectedShapes.getShapes().size()>1)
						m.group.addShape(selectedShapes);
					else
						m.group.addShape(selectedShapes.getShapes().get(0));
				}
				selectZone.setOnDragDetected(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						Dragboard db = selectZone.startDragAndDrop(TransferMode.ANY);
						ClipboardContent content = new ClipboardContent();
						content.putString("");
						db.setContent(content);
						WritableImage wi = new WritableImage((int)selectZone.getWidth(), (int)selectZone.getHeight());
						SnapshotParameters sp = new SnapshotParameters();
						sp.setViewport(new Rectangle2D(selectZone.getX()+xOffset,selectZone.getY()+yOffset,1000,1000));
						p.snapshot(sp, wi);
						db.setDragView(wi);
						event.consume();
					}
				});
				event.consume();
			}
		});
	}
}
