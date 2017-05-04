package model;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Model {
	private static Model instance;
	private ShapeGroup group;
	public Memoire historique;
	private int index;
	Originator originator;

	public ShapeGroup getGroup() {
		return group;
	}

	public static Model getInstance() {
		if (instance == null) {
			Model.instance = new Model();
		}
		return Model.instance;
	}

	private Model() {
		group = new ShapeGroup();
		historique = Memoire.getInstance();
		index = -1;
		originator = new Originator();
		this.ActionPerformed();
	}
	
	public void CreateRect(float largeur, float hauteur, Point2D pos, Color couleur){
		System.out.println("largeur : "+largeur+" hauteur : "+hauteur +" posX : "+pos.getX()+" posY : "+pos.getY() );
		group.addShape(new Rectangle(largeur, hauteur, pos,  couleur));
		this.ActionPerformed();
	}
	
	public void CreatePoly(int nbCotes,int longueurCotes, Point2D pos, Color couleur) {
		group.addShape(new Polygone(nbCotes, longueurCotes, pos, couleur));
		this.ActionPerformed();
	}

	public void ActionPerformed() {
		group.setEtat(group);
		historique.addMemento(group.save());
		System.out.println("from" + index);
		index++;
		System.out.println("ActionPerformed\nTo " + index + "\n");
	}

	public void Undo() {
		if (index > 0) {
			System.out.println("from" + index);
			index--;
			System.out.println("To" + index);
			group = (ShapeGroup) group.restaure(historique.getMemento(index));
		}
	}

	public void Redo() {
		if (index < historique.getTaille()) {
			index++;
			group = (ShapeGroup) originator.restaure(historique.getMemento(index));
		}
	}

	public void Liste() {
		group.liste();
	}
}
