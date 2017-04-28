package model;

import java.util.ArrayList;

import com.sun.javafx.geom.Vec2d;

import javafx.geometry.Point2D;

public class ShapeGroup implements Shape{
	private ArrayList<Shape> shapes;
	
	public Point2D getCentre(){
		int i=0;
		int x=0;
		int y=0;
		if(!shapes.isEmpty())
			for (Shape s : shapes) {
				x+=s.getCentre().getX();
				y+=s.getCentre().getY();
				i++;
			}
		if(i>0){
			x/=i;
			y/=i;
		}
		return new Point2D(x,y);
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}
	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

	public  ShapeGroup() {
		shapes = new ArrayList<Shape>();
	}
	
	@Override
	public void rotation(float deg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void translation(Vec2d dir) {
		// TODO Auto-generated method stub
		for(Shape shape : shapes){
			shape.translation(dir);
		}
	}
	
	public void addShape(Shape shape){
		shapes.add(shape);
	}
}
