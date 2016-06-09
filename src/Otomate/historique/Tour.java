package Otomate.historique;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alicia
 * Un Tour est une liste d'actions et de personnages
 */
public class Tour {
	private List<Evenement> evenements;
	
	public Tour(){
		evenements = new ArrayList<Evenement>();
	}

	public Evenement getEvenement(int i) {
		return evenements.get(i);
	}
	
	public void removeEvenement(int i) {
		this.evenements.remove(i);
	}

	public void addEvenement(Evenement evenement) {
		this.evenements.add(evenement);
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
