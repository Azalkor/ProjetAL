package controller;

import javafx.event.EventHandler;
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
	
	public static boolean isTranslating=false;
	
	Rectangle selectZone;
	boolean selectStarted=false;
	ShapeGroup selectedShapes = new ShapeGroup();
	
	public CreateGroup(Model m, Pane p){
		
		p.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
				p.getChildren().remove(selectZone);
				selectedShapes.getShapes().clear();
				if(!isTranslating){
					selectZone = new Rectangle(event.getX(),event.getY(),0,0);
		            selectZone.setFill(Color.TRANSPARENT);
		            selectZone.setStroke(Color.TRANSPARENT);
		            selectZone.setStrokeWidth(1);
		            p.getChildren().addAll(selectZone);
		            selectStarted=true;
		            System.out.println("coucouentrer");
				}
				event.consume();
			}
		});
		p.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				System.out.println("coucoufuck");
				if(!isTranslating)
					if(selectStarted){
						System.out.println("getX : "+event.getX());
						selectZone.setWidth(event.getX()-selectZone.getX());
						selectZone.setHeight(event.getY()-selectZone.getY());
			            selectZone.setStroke(Color.BLACK);
			            System.out.println(selectZone.getWidth());
					}
				event.consume();
			}
			
		});
		p.setOnMouseReleased(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				System.out.println("coucou");
				isTranslating=false;
				selectStarted=false;
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
				//selectZone.toBack();
				selectZone.setOnDragDetected(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						Dragboard db = selectZone.startDragAndDrop(TransferMode.ANY);
						ClipboardContent content = new ClipboardContent();
						content.putString("");
						db.setContent(content);
						WritableImage wi = p.snapshot(null, null);
						db.setDragView(wi);
						event.consume();
					}
				});
				//selectZone.toBack();
				event.consume();
			}
			
		});
	}
}
