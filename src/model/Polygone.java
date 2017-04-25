package model;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Polygone extends AbstractShape{
	private int nbCotes;
	private float longueurCotes;
	
	public int getNbCotes() {
		return nbCotes;
	}
	public void setNbCotes(int nbCotes) {
		this.nbCotes = nbCotes;
	}
	
	public float getLongueurCotes() {
		return longueurCotes;
	}
	public void setLongueurCotes(float longueurCotes) {
		this.longueurCotes = longueurCotes;
	}
	
	public Polygone(int nbCotes, float longueurCotes, Point2D pos, Point2D centreRot, Color couleur) {
		super(pos,centreRot,couleur);
		this.nbCotes = nbCotes;
		this.longueurCotes = longueurCotes;
	}
	
}
