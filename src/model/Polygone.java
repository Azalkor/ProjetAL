package model;

public class Polygone extends AbstractShape{
	private int nbCotes;
	private float longueurCotes;
	
	public int getNbCotes() {
		return nbCotes;
	}
	public void setNbCotes(int nbCotes) {
		this.nbCotes = nbCotes;
	}
	
	public float getLongueurCotes() {
		return longueurCotes;
	}
	public void setLongueurCotes(float longueurCotes) {
		this.longueurCotes = longueurCotes;
	}
	
	public Polygone(){}
}
