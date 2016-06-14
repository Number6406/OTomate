package Otomate;

import Affichage.*;
import Otomate.historique.Evenement;
import Otomate.historique.Historique;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class Jeu {


	// Attributs
	public static Grille plateau;
	public static List<Joueur> joueurs;
	public static List<Integer> refPersos;
	public static Historique historique;
	public static Univers univers;
        public static int period = 1000;

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
	 * Initialise toutes les variables pour lancer la partie.
	 */
	public static void debutPartie(int numeroUnivers){
		univers = new Univers(numeroUnivers);
		historique = new Historique();
		// TODO pour reduire la taille du main

    	// Variables définies grâce au menu d'affichage ->
    	//int nbJoueurs = 2;
    	//int nbPersoParJoueur = 2;
    	int nZombie = 1;				// Variable possiblement tirée au sort
    	int nbPersoParZombie = 2;
    	List<String> xmlsGentils = new LinkedList<String>();
        xmlsGentils.add("AutomateenXML.xml");
    	List<String> xmlsMechants = new LinkedList<String>();
        xmlsMechants.add("AutomateenXML.xml");
    	List<List<String>> xmls = new LinkedList<>();
    	xmls.add(xmlsGentils);
    	xmls.add(xmlsMechants);
    	List<Color> couleurs = new LinkedList<>();
    	couleurs.add(Color.red);
    	couleurs.add(Color.black);
    	
    	// <- Fin variables
    	initJoueurs(nbPersoParZombie, nZombie, xmls, couleurs);
    	System.out.println("taille joueurs "+joueurs.size());
    	plateau = new Grille(joueurs);
    	System.out.println("taille joueurs 2 "+joueurs.size());
    	refPersos = new LinkedList<Integer>();
    	//String tempHistorique;
    	plateau.initialisergrille(joueurs);
    	try {
			Affichage.charger();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public static void initJoueurs(int nbPersoParZombie, int nZombie, List<List<String>> xmls, List<Color> couleurs) {
		joueurs = new LinkedList<Joueur>();
		int nZ = nbGentils(xmls, nZombie) / nbPersoParZombie;
		for (int i = 0; i < xmls.size(); i++) {
			//System.out.println("nZombie = "+nZombie);
			System.out.println(i);
			if (i == nZombie) {
				//System.out.println("test");
				//System.out.println(i);
				joueurs.add(new Joueur(xmls.get(i), true, nZ, couleurs.get(i)));
			} else {
				//System.out.println("nope");
				joueurs.add(new Joueur(xmls.get(i), false, 42, couleurs.get(i)));
			}
		}
	}
	
	// FONCTIONS DE GESTION DE STATUS
	public static void gereParalysie($Personnage P) throws InterruptedException{
		String th = new String();
		while(((Gentil)P).getParalysie()>0){
			((Gentil) P).setParalysie(((Gentil) P).getParalysie()-1);
			effetsDrogue(P);
			th = P.jouer(univers.getConditions(),plateau,univers.getObjets(),joueurs);
			Thread.sleep(period);
		}
		historique.ceTour().addEvenement(new Evenement(P, th));
		//((Gentil) P).setParalysie(((Gentil) P).getParalysie()-1);
		//((Gentil) P).setEfdrogue(((Gentil) P).getEfdrogue()-1);
		//System.out.println("gentilkijou");
	}
	
	public static void saigne($Personnage P){
		if(P instanceof Gentil){
			if(((Gentil)P).getSaignement()) P.setVie(P.getVie()-5);
		}
	}
	
	public static void junky(List<$Personnage> lp) throws InterruptedException{
		int i,max=lp.size();
		for(i=0;i<max;i++){
			saigne(lp.get(i));
			gereParalysie(lp.get(i));
		}
	}
	
	public static void effetsDrogue($Personnage P){
		if(((Gentil)P).getEfdrogue() != 0){
			if(((Gentil) P).getDrogue() == 3){
				((Gentil) P).setVie(((Gentil) P).getVie()+5);
				if (P.getVie() > P.getViemax()){
					P.setVie(P.getViemax());
				}
			}
			if(((Gentil)P).getDrogue() == 4)
				P.setVie(P.getVie()-5);
			if(((Gentil)P).getDrogue() == 5)
				((Gentil)P).setParalysie(2);
			if(((Gentil)P).getDrogue() == 6)
				((Gentil)P).setParalysie(0);
			((Gentil) P).setEfdrogue(((Gentil)P).getEfdrogue()-1);
		}
	}
	
	public static boolean soinInstantane($Personnage P){
		if(((Gentil) P).getSaignement() == true && ((Gentil) P).getRemede() == 2){
			((Gentil) P).setSaignement(false);
			return true;
		}
		else if(((Gentil) P).getInfecte() == true && ((Gentil) P).getRemede() == 1){
			((Gentil) P).setInfecte(false);
			return true;
		}
		return false;
	}
	
	public static void veriftransfo($Personnage P, Mechant E, List<Joueur> l){
		if(P instanceof Gentil){
			if(P.getVie() == 0 && ((Gentil) P).getInfecte() == true){
				int i,j;
				for(i=0; i<l.size(); i++){
					for(j=0; j<l.get(i).getSizePersonnages(); j++){
						if(l.get(i).getPersonnagesI(j) == P){
							l.get(i).getPersonnages().remove(j);
						}
						else if(l.get(i).getPersonnagesI(j) == E){
							l.get(i).getPersonnages().add(P);
						}
					}
				}
				P = new Mechant(E);
			}
			else if(P.getVie() == 0){
				int i,j;
				for(i=0; i<l.size(); i++){
					for(j=0; j<l.get(i).getSizePersonnages(); j++){
						if(l.get(i).getPersonnagesI(j) == P){
							l.get(i).getPersonnages().remove(j);
						}
					}
				}
			}
		}
		else{
			if(P.getVie() == 0){
				int i,j;
				for(i=0; i<l.size(); i++){
					for(j=0; j<l.get(i).getSizePersonnages(); j++){
						if(l.get(i).getPersonnagesI(j) == P){
							l.get(i).getPersonnages().remove(j);
						}
					}
				}
			}
		}
	}
	
	// UN TOUR DE JEU
	/**
	 * Fait jouer Un personnage.
	 * @param P
	 * @throws InterruptedException
	 */
	public static void tourDePerso($Personnage P) throws InterruptedException{
		String tempHistorique;
		Mechant E = new Mechant();
		if (P instanceof Gentil){
			Gentil gentilperso=((Gentil) P);
			System.out.println(gentilperso.getParalysie());
			if(soinInstantane(gentilperso) == false){
    			gereParalysie(gentilperso);
				if (gentilperso.getParalysie()<1){
			//		System.out.println("passe tour drogue ou drogue dissipe");
					gentilperso.setParalysie(gentilperso.getParalysie()+1);
				}    				
			}
			int i,j;
			for(i=0; i<joueurs.size(); i++){
				for(j=0; j<joueurs.get(i).getSizePersonnages(); j++){
					if(joueurs.get(i).getPersonnagesI(j) instanceof Mechant)
						E = ((Mechant) joueurs.get(i).getPersonnagesI(j));
				}
			}
		}
		else {
			tempHistorique = P.jouer(univers.getConditions(),plateau,univers.getObjets(),joueurs);
			E = ((Mechant) P);
			historique.ceTour().addEvenement(new Evenement(P, tempHistorique));
			//System.out.println("mechantkijou");
			Thread.sleep(period);
		}
		veriftransfo(P, E, joueurs);
	}
	
	/**
	 * Effectue un tour de jeu en changeant l'ordre de jeu de chaque personnage
	 */
	public static void tour(){
		
		int j,p;
		
		melange(); // Mélange la liste des personnages
		historique.addTour(); // Ajout un tour à l'historique
		
		
		for(int i=0; i<refPersos.size(); i++) {
			j = refPersos.get(i)/100;
			p = refPersos.get(i)%100;
			try {
				tourDePerso(joueurs.get(j).getPersonnagesI(p));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Affichage.ajouterTour(historique.ceTour());
			//System.out.println("FIN DE TOUR");
		}
		// TODO enlever les morts.
	}
	
	/**
	 * Fonction principale de Jeu
	 * @param pArgs
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	// TODO : Raccourcir la fonction !
    public static void main(String[] pArgs) throws InterruptedException, IOException {
    	debutPartie(1);
    	//int nbTotal = (nbJoueurs-1)*nbPersoParJoueur+((nbJoueurs-1)*nbPersoParJoueur/nbPersoParZombie);
    	while(!finPartie()){
    		tour();    		
    	}
    	// TODO Annoncer gagnant
    }
}