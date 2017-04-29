package model;

public class Memento {

	public Shape etat;
	
	public Memento(Shape etat){
		this.etat = etat;
	}
	
	public Shape getEtat() {
		ShapeGroup group = (ShapeGroup) etat;
		group.liste();
		return etat;
	}
}
