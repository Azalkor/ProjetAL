package model;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Model {
	private static Model instance=null;
	private ShapeGroup group;
	private ShapeGroup toolbar;
	public Memoire historique;
	private int index;
	Originator originator;

	public ShapeGroup getGroup() {
		return group;
	}

	public ShapeGroup getToolbar() {
		return toolbar;
	}



	public static Model getInstance() {
		if (instance == null) {
			Model.instance = new Model();
		}
		return Model.instance;
	}

	private Model() {
		group = new ShapeGroup();
		group.addObserveur(new ShapeObserver());
		toolbar = new ShapeGroup();
		toolbar.addObserveur(new ShapeObserver());
		historique = new Memoire();
		index = -1;
		originator = new Originator();
	}
	
	
	public void AddRectToolBar(double largeur, double hauteur, Point2D pos, Color couleur){
		System.out.println("largeur : "+largeur+" hauteur : "+hauteur +" posX : "+pos.getX()+" posY : "+pos.getY() );
		toolbar.addShape(new Rectangle(largeur, hauteur, pos,  couleur));
		this.ActionPerformed();
	}
	
	public void AddPolyToolbar(int nbCotes,int longueurCotes, Point2D pos, Color couleur) {
		toolbar.addShape(new Polygone(nbCotes, longueurCotes, pos, couleur));
		this.ActionPerformed();
	}
	
	public void DrawRect(double largeur, double hauteur, Point2D pos, Color couleur){
		System.out.println("largeur : "+largeur+" hauteur : "+hauteur +" posX : "+pos.getX()+" posY : "+pos.getY() );
		group.addShape(new Rectangle
				(largeur, hauteur, pos,  couleur));
		this.ActionPerformed();
	}
	
	public void DrawPoly(int nbCotes,float longueurCotes, Point2D pos, Color couleur) {
		group.addShape(new Polygone(nbCotes, longueurCotes, pos, couleur));
		this.ActionPerformed();
	}

	public void ActionPerformed() {
		originator.setEtat(group);
		historique.addMemento(originator.save());
		index++;
		group.notifier();
	}

	public void Undo() {
		if (index > 0) {
			index--;
			group = (ShapeGroup) group.restaure(historique.getMemento(index));
		}
		group.notifier();
	}

	public void Redo() {
		if (index < historique.getTaille()) {
			index++;
			group = (ShapeGroup) originator.restaure(historique.getMemento(index));
			group.notifier();
		}
	}

}
