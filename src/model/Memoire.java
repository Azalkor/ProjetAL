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
		return historique.get(index);
	}

	
	public int getTaille(){
		return historique.size();
	}
	
	public void list(){
		Originator origin = new Originator();
		int cpt = 0;
		for (Memento memento : historique) {
			ShapeGroup sg =  (ShapeGroup) origin.restaure(memento);
			System.out.println("Memento" + cpt + ":");
			sg.liste();
			
		}
	}
}
