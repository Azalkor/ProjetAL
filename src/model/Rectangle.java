package model;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Rectangle extends AbstractShape{
	private float largeur;
	private float hauteur;
	private boolean bordsRond;
	
	public float getLargeur() {
		return largeur;
	}
	public void setLargeur(float largeur) {
		this.largeur = largeur;
	}

	public float getHauteur() {
		return hauteur;
	}
	public void setHauteur(float hauteur) {
		this.hauteur = hauteur;
	}
	
	public Rectangle(float largeur, float hauteur, Point2D pos, Point2D centreRot, Color couleur) {
		super(pos, centreRot, couleur);
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.bordsRond = false;
	}
	
	public boolean isBordRond(){
		return bordsRond;
	}
	
	public void arrondirBords(){
		bordsRond = true;
	}
	
	public void bordDroit(){
		bordsRond = false;
	}
}
