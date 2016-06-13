package Otomate;

import Affichage.*;
import Otomate.historique.Evenement;
import Otomate.historique.Historique;
import Parser.ParserConditions;
import Parser.ParserObjet;
import java.awt.Color;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

public class Jeu {


	// Attributs
	public static Grille plateau;
	public static List<Joueur> joueurs;
	public static List<Integer> refPersos;
	public static Historique historique;
	//private static List<Conditions2> listCond;
	//private static List<Objet> listCont;

	// Methodes

	public static boolean finPartie() {
		boolean passe = false, un = false;
		for (int i = 0; i < joueurs.size(); i++) {
			if (joueurs.get(i).mechant) {
				if (joueurs.get(i).getPersonnages().size() == 0) {
					return true;
				} else {
					if (un) {
						return false;
					}
					passe = true;
				}
			} else {
				if (joueurs.get(i).getPersonnages().size() != 0) {
					if (passe) {
						return false;
					}
					un = true;
				}
			}
		}
		return true;
	}

	public static void remplir() {
		refPersos.clear();
		for (int i = 0; i < joueurs.size(); i++) {
			for (int j = 0; j < joueurs.get(i).getPersonnages().size(); j++) {
				refPersos.add(i * 100 + j);
			}
		}
	}

	public static void melange() {
		remplir();
		Random rnd = new Random();
		int k;
		for (int i = refPersos.size(); i > 0; i--) {
			k = rnd.nextInt(i);
			refPersos.add(refPersos.get(k));
			refPersos.remove(k);
		}
	}

	public static int nbGentils(List<List<String>> xmls, int nZombie) {
		int k = 0;
		for (int i = 0; i < xmls.size(); i++) {
			if (i != nZombie) {
				for (int j = 0; j < xmls.get(i).size(); j++) {
					k++;
				}
			}
		}
		return k;
	}

	public static void initJoueurs(int nbPersoParZombie, int nZombie, List<List<String>> xmls, List<Color> couleurs) {
		joueurs = new LinkedList<Joueur>();
		int nZ = nbGentils(xmls, nZombie) / nbPersoParZombie;
		for (int i = 0; i < xmls.size(); i++) {
			if (i == nZombie) {
				joueurs.add(new Joueur(xmls.get(i), true, nZ, couleurs.get(i)));
			} else {
				joueurs.add(new Joueur(xmls.get(i), false, 42, couleurs.get(i)));
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
        String fichiers = new File("auto.xml").toString();
    	System.out.println(fichiers);
        xmlsGentils.add("AutomateenXML.xml");
    	List<String> xmlsMechants = new LinkedList<String>();
        xmlsMechants.add("AutomateenXML.xml");
    	List<List<String>> xmls = new LinkedList<>();
    	xmls.add(xmlsGentils);
    	xmls.add(xmlsMechants);
    	List<Conditions2> listCond = new LinkedList<>();
    	List<Objet> listCont = new LinkedList<>();
    	ParserConditions p1 = new ParserConditions(fichiers);
    	ParserObjet p2 = new ParserObjet(fichiers);
    	listCond = plateau.condparser(fichiers);
    	//System.out.println("Encore avant : " + p1.list.size());
    	listCont = p2.list;
    	// <- Fin variables
    	int j,p;
    	initJoueurs(nbPersoParZombie, nZombie, xmls, couleurs);
    	refPersos = new LinkedList<Integer>();
    	String tempHistorique;
    	Grille.initialisergrille(joueurs);
    	//Affichage.charger();
    	//int nbTotal = (nbJoueurs-1)*nbPersoParJoueur+((nbJoueurs-1)*nbPersoParJoueur/nbPersoParZombie);
    	while(!finPartie()){
    		melange();
    		historique.addTour();
    		for(int i=0; i<refPersos.size(); i++) {
    			j = refPersos.get(i)/100;
    			p = refPersos.get(i)%100;
    			if (joueurs.get(j).getPersonnagesI(p) instanceof Gentil){
        			Gentil gentilperso=((Gentil) joueurs.get(j).getPersonnagesI(p));
    				while (gentilperso.getParalysie()>0){
    					if(gentilperso.getEfdrogue() != 0){
    						if(gentilperso.getDrogue() == 3){
    							gentilperso.setVie(gentilperso.getVie()+5);
    							if(gentilperso.getVie() > gentilperso.getViemax()){
    								gentilperso.setVie(gentilperso.getViemax());
    							}
    						}
    						if(gentilperso.getDrogue() == 4){
    							gentilperso.setVie(gentilperso.getVie()-5);
    						}
    						if(gentilperso.getDrogue() == 5){
    							gentilperso.setParalysie(2);
    						}
    						if(gentilperso.getDrogue() == 6){
    							gentilperso.setParalysie(0);
    						}					
    						//((Gentil) p).efdrogue --;  PENSER A LE METTRE A LA FIN DE LA GRANDE BOUCE DE TOUR
    					}
    					else{
    						gentilperso.setDrogue(0);
    					}
    					tempHistorique = joueurs.get(j).getPersonnagesI(p).jouer(listCond,plateau,listCont,joueurs);
    	    			historique.ceTour().addEvenement(new Evenement(gentilperso, tempHistorique));
    					((Gentil) joueurs.get(j).getPersonnagesI(p)).setParalysie(((Gentil) joueurs.get(j).getPersonnagesI(p)).getParalysie()-1);
    					Thread.sleep(200);
    				}
    			}
    			else {
    				tempHistorique = joueurs.get(j).getPersonnagesI(p).jouer(listCond,plateau,listCont,joueurs);
        			historique.ceTour().addEvenement(new Evenement(joueurs.get(j).getPersonnagesI(p), tempHistorique));
    			}
				//tempHistorique sera la chaîne renvoyée par l'action d'un joueu
//    			$Personnage persoCourant = joueurs.get(refPersos.get(i)/100).getPersonnagesI(refPersos.get(i)-(refPersos.get(i)/100));
//    			tempHistorique = persoCourant.jouer();
    			//tempHistorique sera la chaine renvoyee par l'action d'un joueur
    		}
    		Thread.sleep(200);
    		
    	}
    }
}