package model;

public class Originator {

	private Shape etat;
	
	public void setEtat(Shape etat){
		this.etat = etat.clone();
	}
	
	public Memento save() {
		return new Memento(etat);
	}
	
	
	public Shape restaure(Memento m) {
		etat = m.getEtat();
		return this.getEtat();
	}
	
	public Shape getEtat(){
		return etat;
	}
}
