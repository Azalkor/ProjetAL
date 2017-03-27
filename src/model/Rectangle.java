package model;

public class Rectangle extends AbstractShape{
	private float largeur;
	private float hauteur;
	
	public float getLargeur() {
		return largeur;
	}
	public void setLargeur(float largeur) {
		this.largeur = largeur;
	}

	public float getHauteur() {
		return hauteur;
	}
	public void setHauteur(float hauteur) {
		this.hauteur = hauteur;
	}

	public Rectangle(){}
	
	public Rectangle(float largeur, float hauteur) {
		this.largeur = largeur;
		this.hauteur = hauteur;
	}
	
	public void arrondirBords(){}
}
