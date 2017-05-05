package model;

import com.sun.javafx.geom.Vec2d;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public interface Shape {
	public Point2D getPosition();
	public Point2D getCentre();
	public void rotation(double deg);
	public void translation(Vec2d dir);
	public Shape clone();
	public int egale(Shape s);
	public void notifier();
	public void addObserveur(IShapeObserver obs);
	public int putId();
	public int getId();
	public void setCouleur(Color couleur);
	public void setParent(Shape s);
	public Shape getParent();
}
