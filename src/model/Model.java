package model;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Model {
	private ArrayList<Shape> shapes;
	
	public Model(){
		shapes = new ArrayList<Shape>();
	}
	
	public void CreateRect(float largeur, float hauteur, Point2D pos, Point2D centreRot, Color couleur){
		System.out.println("largeur : "+largeur+" hauteur : "+hauteur +" posX : "+pos.getX()+" posY : "+pos.getY()+ " centreX : "+centreRot.getX()+" centreY : "+centreRot.getY());
		shapes.add(new Rectangle(largeur,hauteur,pos,centreRot,couleur));
	}
}
