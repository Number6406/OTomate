/**
 * 
 */
package Otomate.historique;

import java.util.ArrayList;
import java.util.List;

import Otomate.Gentil;

/**
 * @author alicia
 * Un historique permet tout d'abord d'afficher à l'ecran le résumé des actions, tour par tour
 * Mais permettrait éventuellement de retracer une partie (backwards, move forward)
 * 
 * Un historique est une liste d'actions, ordonnées temporellement et par tour... ?
 */
public class Historique{
	
	// Attributs //
	
	private List<Tour> partie;
	private int tourCourant;
		
	
	public Historique(){
		this.tourCourant = 0;
		this.partie = new ArrayList<Tour>();
	}
	
	public void addTour(){
		tourCourant++;
		partie.add(new Tour());
	}
	
	public Tour ceTour(){
		return partie.get(tourCourant-1);
	}

	public Tour getTour(int i) {
		return partie.get(i);
	}
	
	public int nbTour(){
		return partie.size();
	}
	
	
	public String toString(){
		String s = "Historique : \n";
		int i = 1;
		for(Tour t : partie){
			s += "Tour " + i + "\n" + t.toString();
			i++;
		}
		return s;
	}	
	
	
	public static void main(String[] args) {
		Gentil g = new Gentil("AutomateenXML.xml");
		Historique h = new Historique();
		Evenement e = new Evenement(g, "mange du chocolat");
		h.addTour();
		h.ceTour().addEvenement(new Evenement(g, "fais la vaisselle"));
		h.ceTour().addEvenement(e);
		h.addTour();
		h.ceTour().addEvenement(new Evenement(g, "a mal aux pieds"));
		System.out.println(h.toString());
		
	}
	
}
