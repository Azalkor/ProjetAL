package model;



import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Model {
	public ShapeGroup group;
	public Memoire historique;
	private int index;
	Originator originator ;
	
	public Model(){
		group = new ShapeGroup();
		historique = Memoire.getInstance();
		index = -1;
		originator = new Originator();
	}
	
	public void CreateRect(float largeur, float hauteur, Point2D pos, Point2D centreRot, Color couleur){
		System.out.println("largeur : "+largeur+" hauteur : "+hauteur +" posX : "+pos.getX()+" posY : "+pos.getY()+ " centreX : "+centreRot.getX()+" centreY : "+centreRot.getY());
		group.addShape(new Rectangle(largeur, hauteur, pos, centreRot, couleur));
		this.ActionPerformed();
	}
	
	public void CreatePoly(int nbCotes,int longueurCotes, Point2D pos, Point2D centreRot, Color couleur) {
		group.addShape(new Polygone(nbCotes, longueurCotes, pos, centreRot, couleur));
		this.ActionPerformed();
	}
	
	public void ActionPerformed(){
		originator.setEtat(group);
		historique.addMemento(originator.save());
		index++;
	}
	
	public void Undo(){
		if(index > 1){
			index--;
			group = (ShapeGroup) originator.restaure( historique.getMemento(index) );
		}		
	}
	
	public void Redo(){
		if (index < historique.getTaille()){
			index++;
			group = (ShapeGroup) originator.restaure( historique.getMemento(index) );
		}
	}
}
