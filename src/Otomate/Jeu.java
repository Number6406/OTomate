package Otomate;

import Affichage.*;
import Otomate.historique.Evenement;
import Otomate.historique.Historique;
import Parser.ParserConditions;
import Parser.ParserObjet;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
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
	public static Univers univers;
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
	 * Initialise toutes les variables pour lancer la partie.
	 */
	public static void debutPartie(){
		univers = new Univers(1); // TODO récupérer depuis l'interface
		historique = new Historique();
		plateau = new Grille();
		// TODO pour reduire la taille du main
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
	
	public static void gereParalysie($Personnage P, List<Conditions2> listCond, List<Objet> listCont) throws InterruptedException{
		String th = new String();
		while(((Gentil)P).getParalysie()>0){
			((Gentil) P).setParalysie(((Gentil) P).getParalysie()-1);
			effetsDrogue(P);
			th = P.jouer(listCond,plateau,listCont,joueurs);
		}
		historique.ceTour().addEvenement(new Evenement(P, th));
		((Gentil) P).setParalysie(((Gentil) P).getParalysie()-1);
		((Gentil) P).setEfdrogue(((Gentil) P).getEfdrogue()-1);
		System.out.println("gentilkijou");
		Thread.sleep(200);
	}
	
	public static void saigne($Personnage P){
		if(P instanceof Gentil){
			if(((Gentil)P).getSaignement()) P.setVie(P.getVie()-5);
		}
	}
	
	public static void junky(List<$Personnage> lp,List<Conditions2> listCond,List<Objet>listCont) throws InterruptedException{
		int i,max=lp.size();
		for(i=0;i<max;i++){
			saigne(lp.get(i));
			gereParalysie(lp.get(i),listCond,listCont);
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
	
	public static void veriftransfo($Personnage P, Mechant E){
		if(P instanceof Gentil){
			if(P.getVie() == 0 && ((Gentil) P).getInfecte() == true){
				P = new Mechant(E);
			}
		}
	}
	
	public static void tour($Personnage P, List<Conditions2> listCond, List<Objet> listCont) throws InterruptedException{
		String tempHistorique;
		if (P instanceof Gentil){
			Gentil gentilperso=((Gentil) P);
			if(soinInstantane(gentilperso) == false){
    			gereParalysie(gentilperso, listCond, listCont);
				if (gentilperso.getParalysie()<1){
					System.out.println("passe tour drogue ou drogue dissipe");
					((Gentil) P).setParalysie(((Gentil) P).getParalysie()+1);
				}    				
			}
		}
		else {
			tempHistorique = P.jouer(listCond,plateau,listCont,joueurs);
			historique.ceTour().addEvenement(new Evenement(P, tempHistorique));
			System.out.println("mechantkijou");
			Thread.sleep(200);
		}
	}

	/**
	 * Fonction principale de Jeu
	 * @param pArgs
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	// TODO : Raccourcir la fonction !
    public static void main(String[] pArgs) throws InterruptedException, IOException {
    	debutPartie();
    	// Variables définies grâce au menu d'affichage ->
    	//int nbJoueurs = 2;
    	//int nbPersoParJoueur = 2;
    	int nZombie = 1;				// Variable possiblement tirée au sort
    	int nbPersoParZombie = 2;
    	List<String> xmlsGentils = new LinkedList<String>();
        String fichiers = new File("conditions.xml").toString();
        xmlsGentils.add("AutomateenXML.xml");
    	List<String> xmlsMechants = new LinkedList<String>();
        xmlsMechants.add("AutomateenXML.xml");
    	List<List<String>> xmls = new LinkedList<>();
    	xmls.add(xmlsGentils);
    	xmls.add(xmlsMechants);
    	List<Conditions2> listCond = new LinkedList<>();
    	List<Objet> listCont = new LinkedList<>();
    	List<Color> couleurs = new LinkedList<>();
    	couleurs.add(Color.red);
    	couleurs.add(Color.black);
    	
    	//ParserConditions p1 = new ParserConditions(fichiers);
    	//ParserObjet p2 = new ParserObjet("ObjetsZombie.xml");
    	listCond = plateau.condparser(fichiers);
    	listCont = plateau.objparser("ObjetsZombie.xml");
    	// <- Fin variables
    	int j,p;
    	initJoueurs(nbPersoParZombie, nZombie, xmls, couleurs);
    	refPersos = new LinkedList<Integer>();
    	//String tempHistorique;
    	plateau.initialisergrille(joueurs);
    	Affichage.charger();
    	//int nbTotal = (nbJoueurs-1)*nbPersoParJoueur+((nbJoueurs-1)*nbPersoParJoueur/nbPersoParZombie);
    	while(!finPartie()){
    		melange();
    		historique.addTour();
    		for(int i=0; i<refPersos.size(); i++) {
    			j = refPersos.get(i)/100;
    			p = refPersos.get(i)%100;
    			tour(joueurs.get(j).getPersonnagesI(p), listCond, listCont);
    			System.out.println("FIN DE TOUR");
    			//tempHistorique sera la chaîne renvoyée par l'action d'un joueu
//    			$Personnage persoCourant = joueurs.get(refPersos.get(i)/100).getPersonnagesI(refPersos.get(i)-(refPersos.get(i)/100));
//    			tempHistorique = persoCourant.jouer();
    			//tempHistorique sera la chaine renvoyee par l'action d'un joueur
    		}
    		
    		
    	}
    }
}