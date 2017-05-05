package model;

import java.util.ArrayList;

import com.sun.javafx.geom.Vec2d;

import javafx.geometry.Point2D;

public class ShapeGroup implements Shape {
	private ArrayList<ShapeObserver> observeurs;
	private ArrayList<Shape> shapes;
	private Shape etat;

	public void setEtat(Shape etat) {
		this.etat = etat;
	}

	public Memento save() {
		System.out.println("Etat Sauvegardé:\n");
		ShapeGroup group = (ShapeGroup) etat;
		group.liste();
		return new Memento(etat);
	}

	public Shape restaure(Memento m) {
		etat = m.getEtat();
		return etat;
	}

	public Point2D getCentre() {
		int i = 0;
		int x = 0;
		int y = 0;
		if (!shapes.isEmpty())
			System.out.println(shapes.size());
			for (Shape s : shapes) {
				x += s.getCentre().getX();
				y += s.getCentre().getY();
				i++;
			}
		if (i > 0) {
			x /= i;
			y /= i;
		}
		return new Point2D(x, y);
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

	public ShapeGroup() {
		shapes = new ArrayList<Shape>();
	}

	@Override
	public void rotation(double deg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void translation(Vec2d dir) {
		// TODO Auto-generated method stub
		for (Shape shape : shapes) {
			shape.translation(dir);
		}
	}

	public void addShape(Shape shape) {
		shapes.add(shape);
	}

	public void liste() {
		String str = new String();
		for (Shape shape : shapes) {
			str = str + shape.toString();
		}
		System.out.println(str);
	}


	@Override
	public ShapeGroup clone() {
		ShapeGroup copy = new ShapeGroup();
		for (Shape shape : shapes) {
			copy.addShape(shape);
		}
		return copy;
	}

	@Override
	public Point2D getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	public int egal(ShapeGroup compare) {
		ArrayList<Shape> comp = compare.getShapes();
		if(shapes.size() != comp.size()){
			System.out.println("Failed: unequal size");
			return 0;
		}
		for(int i = 0 ; i< shapes.size(); i++){
			if(shapes.get(i) instanceof Rectangle && comp.get(i) instanceof Rectangle){
				Rectangle test2 = (Rectangle) comp.get(i);
				Rectangle test = (Rectangle) shapes.get(i);
				if(test.egale(test2) != 1){
					System.out.println("Failed:" + shapes.get(i) + "not equal to"+ comp.get(i));
					return 0;
				}
			}
			if(shapes.get(i) instanceof Polygone && comp.get(i) instanceof Polygone){
				Polygone test = (Polygone)shapes.get(i);
				Polygone test2 = (Polygone)comp.get(i);
				if(test.egale(test2) != 1){
					System.out.println("Failed:" + shapes.get(i) + "not equal to"+ comp.get(i));
					return 0;
				}
			}
		}
			
		return 1;
	}


	@Override
	public void notifier() {
		for (ShapeObserver observer : observeurs) {
			observer.update();
		}
		for (Shape shape : shapes) {
			shape.notifier();
		}
	}

	@Override
	public int egale(Shape s) {
		throw new UnsupportedOperationException();
	}


}
