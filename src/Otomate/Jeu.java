package Otomate;

import Affichage.*;
import Otomate.historique.Historique;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Jeu {
    
    //Attributs
    public static Grille plateau;
    public static List<Joueur> joueurs;
    public static List<Integer> refPersos;
    public static Historique historique;
    
    //Methodes
    
    public static boolean finPartie(int nbTotal) {
        for(int i=0; i<joueurs.size(); i++) {
        	if(joueurs.get(i).mechant) {
        		if((joueurs.get(i).getPersonnages().size()==0) ||
        		   (joueurs.get(i).getPersonnages().size()==nbTotal)) {
        			return true;
        		}
        	}
        }
        return false;
    }
    
    public static void remplir() {
    	refPersos.clear();
    	for(int i=0; i<joueurs.size(); i++) {
    		for(int j=0; j<joueurs.get(i).getPersonnages().size(); j++) {
    			refPersos.add(i*100+j);
    		}
    	}
    }
    
    public static void melange() {
        remplir();
        Random rnd = new Random();
        int k;
        for(int i=refPersos.size(); i>0; i--) {
        	k = rnd.nextInt(i);
        	refPersos.add(refPersos.get(k));
        	refPersos.remove(k);
        }
    }
    
    public static void initJoueurs(int nbPersoParZombie, int nZombie, List<List<String>> xmls) {
    	joueurs = new LinkedList<Joueur>();
    	for(int i=0; i<xmls.size(); i++) {
    		
    	}
    }
    
    public static void main(String[] pArgs) throws InterruptedException {
    	plateau = new Grille();
    	historique = new Historique();
    	// Variables définies grâce au menu d'affichage ->
    	int nbJoueurs = 2;
    	int nbPersoParJoueur = 2;
    	int nZombie = 1;				// Variable possiblement tirée au sort
    	int nbPersoParZombie = 2;
    	List<List<String>> xmls = new LinkedList<List<String>>();
    	// <- Fin variables
    	initJoueurs(nbJoueurs, nbPersoParJoueur, nbPersoParZombie, nZombie, xmls);
    	refPersos = new LinkedList<Integer>();
    	String tempHistorique;
    	Grille.initialisergrille(joueurs);
    	Affichage.charger();
    	int nbTotal = (nbJoueurs-1)*nbPersoParJoueur+((nbJoueurs-1)*nbPersoParJoueur/nbPersoParZombie);
    	while(!finPartie(nbTotal)) {
    		melange();
    		for(int i=0; i<refPersos.size(); i++) {
    			tempHistorique = joueurs.get(refPersos.get(i)/100).getPersonnagesI(refPersos.get(i)-(refPersos.get(i)/100)).jouer();
    			//tempHistorique sera la chaîne renvoyée par l'action d'un joueur
    		}
    		Thread.sleep(200);
    	}
    }
}