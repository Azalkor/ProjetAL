package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Model;
import model.Shape;
import model.ShapeGroup;

public class CreateGroup {
	
	Rectangle selectZone;
	boolean selectStarted=false;
	ShapeGroup selectedShapes = new ShapeGroup();
	
	public CreateGroup(Model m, Pane p){
		
		p.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
				selectZone = new Rectangle(event.getX(),event.getY(),0,0);
	            selectZone.setFill(Color.TRANSPARENT);
	            selectZone.setStroke(Color.TRANSPARENT);
	            selectZone.setStrokeWidth(1);
	            p.getChildren().addAll(selectZone);
	            selectStarted=true;
	            System.out.println("coucouentrer");
			}
		});
		p.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				System.out.println("coucoufuck");
				if(selectStarted){
					System.out.println("getX : "+event.getX());
					selectZone.setWidth(event.getX()-selectZone.getX());
					selectZone.setHeight(event.getY()-selectZone.getY());
		            selectZone.setStroke(Color.BLACK);
		            System.out.println(selectZone.getWidth());
				}
			}
			
		});
		p.setOnMouseReleased(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				System.out.println("coucou");
				selectStarted=false;
				for (Shape s : m.group.getShapes()) {
					
				}
			}
			
		});
	}
}
