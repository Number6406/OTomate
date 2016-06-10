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
    
    public static boolean finPartie() {
    	boolean passe=false, un=false;
        for(int i=0; i<joueurs.size(); i++) {
        	if(joueurs.get(i).mechant) {
        		if(joueurs.get(i).getPersonnages().size()==0) {
        			return true;
        		} else {
        			if(un) {return false;}
            		passe=true;
        		}
        	} else {
        		if(joueurs.get(i).getPersonnages().size()!=0) {
        			if(passe) {return false;}
        			un=true;
        		}
        	}
        }
        return true;
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
    
    public static int nbGentils(List<List<String>> xmls, int nZombie) {
    	int k=0;
    	for(int i=0; i<xmls.size(); i++) {
    		if(i!=nZombie) {
	    		for(int j=0; j<xmls.get(i).size(); j++) {
	    			k++;
	    		}
    		}
    	}
    	return k;
    }
    
    public static void initJoueurs(int nbPersoParZombie, int nZombie, List<List<String>> xmls) {
    	joueurs = new LinkedList<Joueur>();
    	int nZ = nbGentils(xmls, nZombie)/nbPersoParZombie;
    	for(int i=0; i<xmls.size(); i++) {
    		if(i==nZombie) {
    			joueurs.add(new Joueur(xmls.get(i),true,nZ));
    		} else {
    			joueurs.add(new Joueur(xmls.get(i),false,42));
    		}
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
    	List<String> xmlsGentils = new LinkedList<String>();
    	List<String> xmlsMechants = new LinkedList<String>();
    	// <- Fin variables
    	initJoueurs(nbPersoParZombie, nZombie, xmls);
    	refPersos = new LinkedList<Integer>();
    	String tempHistorique;
    	Grille.initialisergrille(joueurs);
    	Affichage.charger();
    	while(!finPartie()) {
    		melange();
    		for(int i=0; i<refPersos.size(); i++) {
    			tempHistorique = joueurs.get(refPersos.get(i)/100).getPersonnagesI(refPersos.get(i)-(refPersos.get(i)/100)).jouer();
    			//tempHistorique sera la chaîne renvoyée par l'action d'un joueur
    		}
    		Thread.sleep(200);
    	}
    }
}