package model;

import com.sun.javafx.geom.Vec2d;

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
	
	public Polygone(int nbCotes, float longueurCotes, Point2D pos,  Color couleur) {
		super(pos,couleur);
		this.nbCotes = nbCotes;
		this.longueurCotes = longueurCotes;
		super.centreRotation = pos;
	}
	
	public void translation(Vec2d dir){
		super.translation(dir);
		super.centreRotation = super.centreRotation.add(dir.x, dir.y);
	}
	
	
	
	public Point2D[] getPoint(){
	Point2D centre = new Point2D(0,0);
	
	
	Point2D[] points = new Point2D[nbCotes];
	
	//On trouve le bon rayon
	double angle_b = 2*Math.PI / nbCotes;
	Point2D a = new Point2D(Math.cos(0),Math.sin(0));
	Point2D b = new Point2D(Math.cos(angle_b),Math.sin(angle_b));
	
	double r = (longueurCotes/a.distance(b));
	
	for(int i=0;i<nbCotes;i++) {
		double angle = i*(2*Math.PI / nbCotes);
		double x = r*Math.cos(angle)+centre.getX();
		double y = r*Math.sin(angle)+centre.getY();
		
		points[i] = new Point2D(x,y);
		System.out.println(points[i]);
		}
	return points;
	}
	
	
	
	public int egale(Object obj) {
		if (this == obj)
			return 1;
		if (!super.equals(obj))
			return 0;
		if (getClass() != obj.getClass())
			return 0;
		Polygone other = (Polygone) obj;
		if (Float.floatToIntBits(longueurCotes) != Float.floatToIntBits(other.longueurCotes))
			return 0;
		if (nbCotes != other.nbCotes)
			return 0;
		return 1;
	}
	
	
	public Polygone clone(){
		Polygone copy = new Polygone(nbCotes, longueurCotes, super.position, super.getCouleur());
		return copy;
	}
	
}
