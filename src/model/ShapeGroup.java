package model;

import java.util.ArrayList;

import com.sun.javafx.geom.Vec2d;

public class ShapeGroup implements Shape{
	private ArrayList<Shape> shapes;

	@Override
	public void rotation(float deg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void translation(Vec2d dir) {
		// TODO Auto-generated method stub
		
	}
	
	public void addShape(Shape shape){
		shapes.add(shape);
	}
}
