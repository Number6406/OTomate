package Otomate;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import Otomate.historique.Historique;

public class SaveJeu {
	public Grille plateau;
    public List<Joueur> joueurs;
    public int joueurZombie;
    public Univers univers;
    
    public SaveJeu() {
    	joueurs = new LinkedList<Joueur>();
    }
}
