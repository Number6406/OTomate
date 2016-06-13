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
	/**
	 * Verifie la fin de partie dans un jeu. Le jeu est fini quand il n'y a plus que des mechant ou plus que des gentils
	 * @return vrai si la partie est finie
	 */
	public static boolean finPartie() {
		boolean passe = false, un = false;
		for (int i = 0; i < joueurs.size(); i++) {
			if (joueurs.get(i).estMechant()) {
				if (joueurs.get(i).getPersonnages().size() == 0) {
					return true; // Il n'y a plus de mechants.
				} else {
					if (un) { // Il y a des gentils encore en vie
						return false;
					}
					passe = true; // On a passé les méchants
				}
			} else {
				if (joueurs.get(i).getPersonnages().size() != 0) { // Des gentils sont en vie
					if (passe) { // Et des méchants !
						return false;
					}
					un = true; // Il y a des gentils en vie
				}
			}
		}
		return true; // Tout les gentils n'ont plus de personnages
	}
	
	/**
	 * Remplit la table de reference des personnages (avec des entiers) pour la mélanger ensuite
	 * 101 signifie Joueur 1 personnage 01.
	 * 340 signifie Joueur 3 personnage 40.
	 */
	private static void remplir() {
		refPersos.clear();
		for (int i = 0; i < joueurs.size(); i++) {
			for (int j = 0; j < joueurs.get(i).getPersonnages().size(); j++) {
				refPersos.add(i * 100 + j);
			}
		}
	}
	
	/**
	 * Mélange la table des reférences de personnages pour mélanger les tours de jeu
	 */
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

	/**
	 * Renvoie le nombre de personnages gentils du jeu
	 * @param xmls la liste des fichier xmls pour les automates de personnages
	 * @param nZombie le numéro du joueur zombie
	 * @return le nombre de personnages gentils
	 */
	public static int nbGentils(List<List<String>> xmls, int nZombie) {
		int k = 0;
		for (int i = 0; i < xmls.size(); i++) {
			if (i != nZombie) {
				k+= xmls.get(i).size();
			}
		}
		return k;
	}
	
	/**
	 * Initialise les joueurs de la partie
	 * @param nbPersoParZombie, le nombre de personnage pour un zombie
	 * @param nZombie, le numéro du joueur qui joue zombie
	 * @param xmls, la liste des liste de xmls pour les personnages
	 */
	public static void initJoueurs(int nbPersoParZombie, int nZombie, List<List<String>> xmls) {
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

	/**
	 * Fonction principale de Jeu
	 * @param pArgs
	 * @throws InterruptedException
	 */
	// TODO : Raccourcir la fonction !
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
    	ParserObjet p2 = new ParserObjet("objet.xml");
    	System.out.println(p2.list.size()+" +++++++++++++++++++++++++++++++++");
    	listCond = plateau.condparser(fichiers);
    	//System.out.println("Encore avant : " + p1.list.size());
    	listCont = plateau.objparser("objet.xml");
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
    					}
    					else{
    						gentilperso.setDrogue(0);
    					}
    					tempHistorique = joueurs.get(j).getPersonnagesI(p).jouer(listCond,plateau,listCont,joueurs);
    	    			historique.ceTour().addEvenement(new Evenement(gentilperso, tempHistorique));
    					((Gentil) joueurs.get(j).getPersonnagesI(p)).setParalysie(((Gentil) joueurs.get(j).getPersonnagesI(p)).getParalysie()-1);
    					((Gentil) joueurs.get(j).getPersonnagesI(p)).setEfdrogue(((Gentil) joueurs.get(j).getPersonnagesI(p)).getEfdrogue()-1);
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