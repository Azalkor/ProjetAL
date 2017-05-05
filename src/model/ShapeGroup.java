package model;

import java.util.ArrayList;

import com.sun.javafx.geom.Vec2d;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class ShapeGroup implements Shape {
	private ArrayList<IShapeObserver> observeurs;
	private ArrayList<Shape> shapes;
	private Shape etat;
	private Rectangle selection;
	private int id;
	private Shape parent;



	public Shape restaure(Memento m) {
		etat = m.getEtat();
		return etat;
	}

	public Point2D getCentre() {
		Point2D centre = new Point2D(0,0);
		if(selection!=null)
			centre.add(selection.getPosition());
		return centre;
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

	public ShapeGroup() {
		shapes = new ArrayList<Shape>();
		observeurs = new ArrayList<IShapeObserver>();
		selection=null;
	}
	
	public ShapeGroup(float largeur, float hauteur, Point2D pos) {
		shapes = new ArrayList<Shape>();
		observeurs = new ArrayList<IShapeObserver>();
		selection=new Rectangle(largeur, hauteur, pos, Color.TRANSPARENT);
	}

	@Override
	public void rotation(double deg) {
		Point2D oldPos = selection.getPosition();
		this.selection.rotation(deg);
		Point2D newPos = selection.getPosition();
		Vec2d dir = new Vec2d(newPos.getX()-oldPos.getX(), newPos.getY()-oldPos.getY());
		for (Shape shape : shapes) {
			shape.translation(dir);
			shape.rotation(deg);
		}
		notifier();

	}

	@Override
	public void translation(Vec2d dir) {
		this.selection.translation(dir);
		for (Shape shape : shapes) {
			shape.translation(dir);
		}
		notifier();
	}

	public void addShape(Shape shape) {
		if(this.parent != null){
			ShapeGroup tmp = (ShapeGroup)this.getParent();
			tmp.getShapes().remove(shape);
			tmp.setShapes(tmp.getShapes());
		}
		shape.setParent(this);
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
		for (IShapeObserver observer : observeurs) {
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

	public void addObserveur(IShapeObserver obs){
		observeurs.add(obs);
		for (Shape shape : shapes) {
			shape.addObserveur(obs);
		}
	}

	@Override
	public int putId() {
		this.id = controller.Controller.id;
		return this.id;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setCouleur(Color couleur) {
		for (Shape shape : shapes) {
			shape.setCouleur(couleur);
		}
	}
	
	public void supprimerGroup(){
		for (Shape shape : shapes) {
			this.shapes.remove(shape);
		}
		parent = null;
		id =-1;
	}
	
	public void degroup(){
		for (Shape shape : shapes) {
			ShapeGroup tmp = (ShapeGroup)this.parent;
			tmp.addShape(shape);
		}
		this.supprimerGroup();
	}

	@Override
	public void setParent(Shape s) {
		parent = s;
	}

	@Override
	public Shape getParent() {
		return this.parent;
	}

}
