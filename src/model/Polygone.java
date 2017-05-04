package model;

import com.sun.javafx.geom.Vec2d;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Polygone extends AbstractShape {
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

	public Polygone(int nbCotes, float longueurCotes, Point2D pos, Point2D centreRot, Color couleur) {
		super(pos, centreRot, couleur);
		this.nbCotes = nbCotes;
		this.longueurCotes = longueurCotes;
	}

	public void translation(Vec2d dir) {
		super.translation(dir);
		super.centreRotation = super.centreRotation.add(dir.x, dir.y);
	}

	public double[] getPoints() {
		Point2D centre = new Point2D(0, 0);

		double[] points = new double[nbCotes * 2];

		// On trouve le bon rayon
		double angle_b = 2 * Math.PI / nbCotes;
		Point2D a = new Point2D(Math.cos(0), Math.sin(0));
		Point2D b = new Point2D(Math.cos(angle_b), Math.sin(angle_b));

		double r = (longueurCotes / a.distance(b));

		for (int i = 0; i < nbCotes; i+=2) {
			double angle = i/2 * (2 * Math.PI / nbCotes);
			points[i] = r * Math.cos(angle) + centre.getX();
			points[i + 1] = r * Math.sin(angle) + centre.getY();
		}
		return points;
	}

	public Shape copy() {
		Polygone copy = new Polygone(nbCotes, longueurCotes, super.position, super.centreRotation, super.getCouleur());
		return copy;
	}

}
