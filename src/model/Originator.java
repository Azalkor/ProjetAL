package model;

public class Originator {

	private Shape etat;
	
	public void setEtat(Shape etat){
		this.etat = etat;
	}
	
	public Memento save() {
		return new Memento(etat);
	}
	
	
	public Shape restaure(Memento m) {
		etat = m.getEtat();
		return etat;
	}
}
