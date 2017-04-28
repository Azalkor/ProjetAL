package model;

import com.sun.javafx.geom.Vec2d;

import javafx.geometry.Point2D;

public interface Shape {
	public Point2D getCentre();
	public void rotation(float deg);
	public void translation(Vec2d dir);
}
