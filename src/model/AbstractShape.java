package model;

import java.util.ArrayList;

import com.sun.javafx.geom.Vec2d;

import controller.Controller;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public abstract class AbstractShape implements Shape{
	
	protected ArrayList<IShapeObserver> observeurs;
	protected Point2D position;
	protected Point2D centreRotation;
	protected Color couleur;
	protected int id;
	
	public AbstractShape(Point2D position, Color couleur) {
		this.position = position;
		this.couleur = couleur;
		observeurs = new ArrayList<IShapeObserver>();
	}

	public Point2D getPosition() {
		return position;
	}

	public void setPosition(Point2D position) {
		this.position = position;
		notifier();
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
		notifier();
	}

	public Shape clone(){
		throw new UnsupportedOperationException();
	}

	
	@Override
	public void translation(Vec2d dir) {
		position.add(position.getX()+dir.x, position.getY()+dir.y);		
		if (position.getX() < 0){
			position.add(- position.getX(), 0);
		}
		if (position.getY() < 0){
			position.add(0,- position.getY());
		}
		notifier();
	}

	public Color getCouleur(){
		return this.couleur;
	}
	
	@Override
	public void rotation(double deg) {
		notifier();
	}

	@Override
	public Point2D getCentre() {
		return centreRotation;
	}


	@Override
	public String toString() {
		return "AbstractShape [position=" + position + ", centreRotation=" + centreRotation + ", couleur=" + couleur
				+ "]";
	}




	



	public int egale(Object obj) {
		System.out.println("Appele methode egale ABSTRACT param obj");
		if (this == obj)
			return 1;
		if (obj == null)
			return 0;
		if (getClass() != obj.getClass())
			return 0;
		AbstractShape other = (AbstractShape) obj;
		if (centreRotation == null) {
			if (other.centreRotation != null)
				return 0;
		} else if (!centreRotation.equals(other.centreRotation))
			return 0;
		if (couleur == null) {
			if (other.couleur != null)
				return 0;
		} else if (!couleur.equals(other.couleur))
			return 0;
		if (position == null) {
			if (other.position != null)
				return 0;
		} else if (!position.equals(other.position))
			return 0;
		return 1;
	}



	@Override
	public int egale(Shape s) {
		System.out.println("Appele methode egale ABSTRACT");
		if(this.position.distance(s.getPosition())==0)
			return 1;
		return 0;
	}



	@Override
	public void notifier() {
		for (IShapeObserver observer : observeurs) {
			observer.update();
		}
	}
	
	public void addObserveur(IShapeObserver obs){
		observeurs.add(obs);
	}
	
	@Override
	public int putId(){
		id=Controller.id++;
		return id;
	}
	
	@Override
	public int getId(){
		return id;
	}
	
	
	
	
}
