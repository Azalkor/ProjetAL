package model;

public class Memento {

	private Shape etat;
	
	public Memento(Shape etat){
		this.etat = etat;
	}
	
	public Shape getEtat() {
		return etat;
	}
}
