package model;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Model {
	private static Model instance;
	private ShapeGroup group;
	private ShapeGroup toolbar;
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
		toolbar = new ShapeGroup();
		historique = new Memoire();
		index = -1;
		originator = new Originator();
	}
	
	
	public void AddRectToolBar(float largeur, float hauteur, Point2D pos, Color couleur){
		System.out.println("largeur : "+largeur+" hauteur : "+hauteur +" posX : "+pos.getX()+" posY : "+pos.getY() );
		toolbar.addShape(new Rectangle(largeur, hauteur, pos,  couleur));
		this.ActionPerformed();
	}
	
	public void AddPolyToolbar(int nbCotes,int longueurCotes, Point2D pos, Color couleur) {
		toolbar.addShape(new Polygone(nbCotes, longueurCotes, pos, couleur));
		this.ActionPerformed();
	}
	
	public void DrawRect(float largeur, float hauteur, Point2D pos, Color couleur){
		System.out.println("largeur : "+largeur+" hauteur : "+hauteur +" posX : "+pos.getX()+" posY : "+pos.getY() );
		group.addShape(new Rectangle(largeur, hauteur, pos,  couleur));
		this.ActionPerformed();
	}
	
	public void DrawPoly(int nbCotes,int longueurCotes, Point2D pos, Color couleur) {
		group.addShape(new Polygone(nbCotes, longueurCotes, pos, couleur));
		this.ActionPerformed();
	}

	public void ActionPerformed() {
		originator.setEtat(group);
		historique.addMemento(originator.save());
		index++;
	}

	public void Undo() {
		if (index > 0) {
			index--;
			group = (ShapeGroup) group.restaure(historique.getMemento(index));
		}
	}

	public void Redo() {
		if (index < historique.getTaille()) {
			index++;
			group = (ShapeGroup) originator.restaure(historique.getMemento(index));
		}
	}

}
