package Otomate.historique;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alicia
 * Un Tour est une liste d'actions et de personnages
 */
public class Tour {
	private List<Evenement> evenements;
	private int numero;
	
	public Tour(int num){
		evenements = new ArrayList<Evenement>();
		numero = num;
	}

	public Evenement getEvenement(int i) {
		return evenements.get(i);
	}
	
	public int getNumero() {
		return numero;
	}
	
	public void removeEvenement(int i) {
		this.evenements.remove(i);
	}

	public void addEvenement(Evenement evenement) {
		this.evenements.add(evenement);
	}
	
	public int nbEvenement(){
		return evenements.size();
	}
	
	@Override
	public String toString(){
		String s = "";
		for(Evenement e : this.evenements){
			s += e.toString() + "\n";
		}
		return s;
	}

}
