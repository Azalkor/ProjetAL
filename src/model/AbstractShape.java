package model;

import com.sun.javafx.geom.Vec2d;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class AbstractShape implements Shape{
	
	protected Point2D position;
	protected Point2D centreRotation;
	private Color couleur;
	
	public AbstractShape(Point2D position, Point2D centreRotation, Color couleur) {
		this.position = position;
		this.centreRotation = centreRotation;
		this.couleur = couleur;
	}


	@Override
	public void translation(Vec2d dir) {
		position.add(position.getX()+dir.x, position.getY()+dir.y);		
	}
	
	public void changeCouleur(Color newColor) {
		this.couleur = newColor;
	}

	public Color getCouleur(){
		return this.couleur;
	}
	
	@Override
	public void rotation(float deg) {
		
	}

}
