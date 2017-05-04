package model;

import com.sun.javafx.geom.Vec2d;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Rectangle extends AbstractShape{
	private double largeur;
	private double hauteur;
	private boolean bordsRond;
	
	public double getLargeur() {
		return largeur;
	}
	public void setLargeur(double largeur) {
		this.largeur = largeur;
	}

	public double getHauteur() {
		return hauteur;
	}
	public void setHauteur(double hauteur) {
		this.hauteur = hauteur;
	}
	
	public Rectangle(double largeur, double hauteur, Point2D pos, Color couleur) {
		super(pos, couleur);
		super.centreRotation = new Point2D(super.position.getX() + largeur / 2,
				super.position.getY()+ 30 / 2);
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
	
	public void rotation(double angle) {
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
	
	public Rectangle clone(){
		return new Rectangle(largeur, hauteur, super.position,  super.getCouleur());
	}
	
	public int egale(Rectangle obj) {
		System.out.println("Appele methode egale RECTANGLE");
		if(this.hauteur == obj.getHauteur() && this.largeur == obj.getLargeur())
			return 1;
		System.out.println("dsl"+ this.hauteur + "pas égale à "+obj.getHauteur()+ "ou "+this.largeur+ "pas égale à" + obj.getLargeur());
		return 0;
	}
	
	
}
