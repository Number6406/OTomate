package Otomate.historique;

import java.awt.Color;

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
	
	public Color getcouleur(){
		return personnage.getCouleur();
	}
	
	public String getNom(){
		return personnage.getNom();
	}
	
	public String getTexte(){
		return description;
	}
	
	@Override
	public String toString(){
		return personnage.getNom() + " " + description;			
	}
	
}
