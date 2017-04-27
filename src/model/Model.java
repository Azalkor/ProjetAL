package model;



import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Model {
	public ShapeGroup group;
	
	public Model(){
		group = new ShapeGroup();
	}
	
	public void CreateRect(float largeur, float hauteur, Point2D pos, Point2D centreRot, Color couleur){
		System.out.println("largeur : "+largeur+" hauteur : "+hauteur +" posX : "+pos.getX()+" posY : "+pos.getY()+ " centreX : "+centreRot.getX()+" centreY : "+centreRot.getY());
		group.addShape(new Rectangle(largeur, hauteur, pos, centreRot, couleur));
	}
	
	public void CreatePoly(int nbCotes,int longueurCotes, Point2D pos, Point2D centreRot, Color couleur) {
		group.addShape(new Polygone(nbCotes, longueurCotes, pos, centreRot, couleur));
	}
}
