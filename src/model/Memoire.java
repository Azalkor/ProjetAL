package model;

import java.util.ArrayList;

public class Memoire {

	private ArrayList<Memento> historique;
	//private static Memoire instance;

	/*
	public static Memoire getInstance(){
		if(instance == null){
			return new Memoire();
		}

		return instance;
	}
	*/

	public Memoire(){
		historique = new ArrayList<Memento>();
	}

	public void addMemento(Memento m) {
		historique.add(m);
	}

	public Memento getMemento(int index) {
		System.out.println("Etat censé être restauré à l'index" + index );
		return historique.get(index);
	}

	
	public int getTaille(){
		return historique.size();
	}
	
	public void list(){
		Originator origin = new Originator();
		for (Memento memento : historique) {
			ShapeGroup mdr =  (ShapeGroup) origin.restaure(memento);
		}
	}
}
