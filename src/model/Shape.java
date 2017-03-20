package model;

import com.sun.javafx.geom.Vec2d;

public interface Shape {
	public void rotation(float deg);
	public void translation(Vec2d dir);
}
