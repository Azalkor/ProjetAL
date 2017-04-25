package controller;

import java.awt.MouseInfo;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Model;

/**
 * View-Controller for the person table.
 * 
 * @author Marco Jakob
 */
public class FenetreProjetController {
	
	private Model model;
	
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
	private Rectangle rectangleFabrique;
	@FXML
	private Pane dropPane;
	
	
	
	/**
	 * The constructor (is called before the initialize()-method).
	 */
	public FenetreProjetController() {
		model = new Model();
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
			System.out.println("Button Action\n");
		});
		
		buttonRedo.setOnAction((event) -> {
			System.out.println("Button Action\n");
		});
		
		buttonDelete.setOnAction((event) -> {
			System.out.println("Button Action\n");
		});
		
		rectangleFabrique.setOnDragDetected(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent event) {
		        /* drag was detected, start a drag-and-drop gesture*/
		        /* allow any transfer mode */
		        Dragboard db = rectangleFabrique.startDragAndDrop(TransferMode.ANY);
		        
		        /* Put a string on a dragboard */
		        ClipboardContent content = new ClipboardContent();
		        content.putString("TEST");
		        db.setContent(content);
		        
		        event.consume();
		    }
		});
		
		dropPane.setOnDragEntered(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* the drag-and-drop gesture entered the target */
                System.out.println("onDragEntered");
                /* show to the user that it is an actual gesture target */
                if (event.getGestureSource() != dropPane &&
                        event.getDragboard().hasString()) {
                	//dropPane.setFill(Color.GREEN);
                }
                
                event.consume();
            }
        });

		dropPane.setOnDragOver(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");
                
                /* accept it only if it is  not dragged from the same node 
                 * and if it has a string data */
                if (event.getGestureSource() != dropPane &&
                        event.getDragboard().hasString()) {
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                
                event.consume();
            }
        });
		
		dropPane.setOnDragExited(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* mouse moved away, remove the graphical cues */
            	//dropPane.setFill(Color.BLACK);
                
                event.consume();
            }
        });

		dropPane.setOnDragDropped(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* data dropped */
                System.out.println("onDragDropped");
                Point2D dropPos = new Point2D(event.getX()-rectangleFabrique.getWidth()/2,
                							  event.getY()-rectangleFabrique.getHeight()/2);
                model.CreateRect((float)rectangleFabrique.getWidth(),
                				 (float)rectangleFabrique.getHeight(),
                				 new Point2D(dropPos.getX(), dropPos.getY()),
                				 new Point2D(dropPos.getX()+rectangleFabrique.getWidth()/2,
                						 dropPos.getY()+rectangleFabrique.getHeight()/2)
                				 ,Color.BLACK);
                Rectangle newRect = new Rectangle(rectangleFabrique.getWidth(),rectangleFabrique.getHeight());
                newRect.setX(dropPos.getX());
                newRect.setY(dropPos.getY());
                dropPane.getChildren().add(newRect);
                /* if there is a string data on dragboard, read it and use it */
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                	//dropPane.setText(db.getString());
                    success = true;
                }
                /* let the source know whether the string was successfully 
                 * transferred and used */
                event.setDropCompleted(success);
                
                event.consume();
            }
        });
		
		rectangleFabrique.setOnDragDone(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* the drag-and-drop gesture ended */
                System.out.println("onDragDone");
                
                /* if the data was successfully moved, clear it */
                if (event.getTransferMode() == TransferMode.MOVE) {
                	//rectangleFabrique.setText("");
                }
                
                event.consume();
            }
        });
	}
	
}