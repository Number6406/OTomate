/**
 * 
 */
package Otomate.historique;

import java.util.ArrayList;
import java.util.List;

import Otomate.Gentil;
import jus.util.assertion.Require;

/**
 * @author alicia
 * Un historique permet tout d'abord d'afficher à l'ecran le résumé des actions, tour par tour
 * Mais permettrait éventuellement de retracer une partie (backwards, move forward)
 * 
 * Un historique est une liste d'actions, ordonnées temporellement et par tour... ?
 */
public class Historique{
	
	// Attributs //
	private List<Tour> partie; // Liste des tours
	private int tourCourant; // Numéro du tour courant
		
	
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
	/**
	 * 
	 * @param i le numéro de tour à récupérer
	 * @return LE tour i 
	 * @require TourExistant : i <= nbTour() && i>0
	 */
	public Tour getTour(int i) throws Require{
		if(!(i <= nbTour() && i>0)){
			throw new Require("TourExistant");
		}
		return partie.get(i-1);
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
