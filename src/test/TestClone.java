package test;

import static org.junit.Assert.*;

import org.junit.Test;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import model.Polygone;
import model.Rectangle;
import model.ShapeGroup;

public class TestClone {

	@Test
	public void test() {
		ShapeGroup collection = new ShapeGroup();
		ShapeGroup collectionCopy = collection.clone();
		Point2D rectPos = new Point2D(25, 30);
		Rectangle rect = new Rectangle(50, 30, rectPos,  Color.BLACK);
		Rectangle rectCopy =  rect.clone();
		collection.addShape(rect);
		collectionCopy.addShape(rectCopy);
		
		Point2D polPos = new Point2D(35, 20);
		Polygone pol = new Polygone(6, 20, polPos, Color.BLUE);
		Polygone polCopy = pol.clone();
		collection.addShape(pol);
		collectionCopy.addShape(polCopy);
		collection.liste();
		collectionCopy.liste();
		assertEquals(collection.egal(collectionCopy), 1);
	}

}
