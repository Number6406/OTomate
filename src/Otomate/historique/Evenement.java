package Otomate.historique;

import Otomate.$Personnage;

/**
 * 
 * @author alicia
 * Couple compris dans l'historique, permettant d'afficher le texte de la bonne couleur
 */
public class Evenement{
	$Personnage personnage;
	String description;
	
	public Evenement($Personnage p, String description){
		this.personnage = p;
		this.description = description;
	}
	
	@Override
	public String toString(){
		if(personnage!=null){
		return "<html>" + personnage.getNomHtml() + " " + description + "</html>";
		} else return description;
	}
	
	
}
