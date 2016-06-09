/**
 * 
 */
package Otomate;

import java.awt.Color;
import java.util.List;

/**
 * @author alicia
 * Un historique permet tout d'abord d'afficher à l'ecran le résumé des actions, tour par tour
 * Mais permettrait éventuellement de retracer une partie (backwards, move forward)
 * 
 * Un historique est une liste d'actions, ordonnées temporellement et par tour... ?
 */
public class Historique {
	
	/**
	 * 
	 * @author alicia
	 * Un Tour est une liste d'actions et de personnages
	 */
	public class Tour {
		private List<Evenement> evenements;
		
	}
	
	/**
	 * 
	 * @author alicia
	 * Triplet compris dans l'historique, permettant d'afficher le texte de la bonne couleur
	 */
	public class Evenement{
		$Personnage personnage;
		String description;
		
		public Evenement($Personnage p, String description){
			this.personnage = p;
			this.description = description;
		}
		
	}
	
	
	private List<Tour> partie;
	
	public void printInColor(String s, Color c){
		
	}
	
	public static void main(String[] args) {
		Color c = new Color(0, 20, 200);
		System.out.println(c.toString());
		
	}
	
}
