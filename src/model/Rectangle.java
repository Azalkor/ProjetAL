package model;

import com.sun.javafx.geom.Vec2d;

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
	
	public void rotation(float angle) {
		double dx = super.position.getX() - super.centreRotation.getX();
		double dy = super.position.getX() - super.centreRotation.getX();;
		double newX = super.position.getX() - dx*Math.cos(angle) + dy*Math.sin(angle);
		double newY = super.position.getX() - dx*Math.sin(angle) - dy*Math.cos(angle);
		super.position = new Point2D(newX, newY);
	}
	
	public void translation(Vec2d dir){
		super.translation(dir);
		super.centreRotation = new Point2D(super.position.getX() + largeur / 2,
				super.position.getY() + hauteur / 2);
	}
}
