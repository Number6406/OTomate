package Otomate;

import Affichage.*;
import Otomate.historique.Evenement;
import Otomate.historique.Historique;

import java.awt.Color;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.print.attribute.standard.NumberOfDocuments;

public class Jeu {

    // Attributs
    public static Grille plateau;
    public static List<Joueur> joueurs;
    public static List<Integer> refPersos;
    private static int joueurZombie;
    public static Historique historique;
    public static Univers univers;
    public static int vitesse1 = 1000, vitesse2 = 500, vitesse3 = 250;
    public static int period = vitesse1;
    public static boolean pause = false;
    public static boolean step = false;
    
    // Nécessaire au lancement du jeu
    private static boolean commencerJeu = false;
    private static int numeroUnivers;
    private static int nZombie;
    private static int nbPersoParZombie;
    private static List<List<String>> xmls;
    private static List<Color> couleurs;

    // Methodes
    /**
     * Verifie la fin de partie dans un jeu. Le jeu est fini quand il n'y a plus
     * que des mechant ou plus que des gentils
     *
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
            } else if (joueurs.get(i).getPersonnages().size() != 0) { // Des gentils sont en vie
                if (passe) { // Et des méchants !
                    return false;
                }
                un = true; // Il y a des gentils en vie
            }
        }
        return true; // Tout les gentils n'ont plus de personnages
    }

    /**
     * Initialise toutes les variables pour lancer la partie.
     */
    public static void debutPartie(int numeroUnivers, int nZombie, int nbPersoParZombie, List<List<String>> xmls, List<Color> couleurs) {
        univers = new Univers(numeroUnivers);
        historique = new Historique();
        // TODO pour reduire la taille du main

        // Variables définies grâce au menu d'affichage ->
        //int nbJoueurs = 2;
        //int nbPersoParJoueur = 2;
        initJoueurs(nbPersoParZombie, nZombie, xmls, couleurs);
        joueurs.get(1).getPersonnagesI(0).setPosition(new Coordonnees(0,1));
        System.out.println("taille joueurs " + joueurs.size());
        plateau = new Grille(joueurs,univers);
        System.out.println("taille joueurs 2 " + joueurs.size());
        refPersos = new LinkedList<Integer>();
        //String tempHistorique;
       // plateau.initialisergrille(joueurs);
        System.out.println("FIN INIT");
        try {
            Affichage.charger();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Remplit la table de reference des personnages (avec des entiers) pour la
     * mélanger ensuite 101 signifie Joueur 1 personnage 01. 340 signifie Joueur
     * 3 personnage 40.
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
     * Mélange la table des reférences de personnages pour mélanger les tours de
     * jeu
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
     *
     * @param xmls la liste des fichier xmls pour les automates de personnages
     * @param nZombie le numéro du joueur zombie
     * @return le nombre de personnages gentils
     */
    public static int nbGentils(List<List<String>> xmls, int nZombie) {
        int k = 0;
        for (int i = 0; i < xmls.size(); i++) {
            if (i != nZombie) {
                k += xmls.get(i).size();
            }
        }
        return k;
    }

    /**
     * Initialise les joueurs de la partie
     *
     * @param nbPersoParZombie, le nombre de personnage pour un zombie
     * @param nZombie, le numéro du joueur qui joue zombie
     * @param xmls, la liste des liste de xmls pour les personnages
     */
    public static void initJoueurs(int nbPersoParZombie, int nZombie, List<List<String>> xmls, List<Color> couleurs) {
        joueurs = new LinkedList<Joueur>();
        int nZ = nbGentils(xmls, nZombie) / nbPersoParZombie;
        for (int i = 0; i < xmls.size(); i++) {
            System.out.println("i="+i);
            if (i == nZombie) {
                joueurs.add(new Joueur(xmls.get(i), true, nZ, couleurs.get(i)));
            } else {
                joueurs.add(new Joueur(xmls.get(i), false, 42, couleurs.get(i)));
            }
        }
    }

    // FONCTIONS DE GESTION DE STATUS
    public static void gereParalysie($Personnage P) throws InterruptedException {
        String th = new String();
        while (((Gentil) P).getParalysie() > 0) {
            ((Gentil) P).setParalysie(((Gentil) P).getParalysie() - 1);
            effetsDrogue(P);
            th = P.jouer(plateau, joueurs,univers);
            historique.ceTour().addEvenement(new Evenement(P, th));
            Thread.sleep(period);
        }
    }

    public static void saigne($Personnage P) {
        if (P instanceof Gentil) {
            if (((Gentil) P).getSaignement()) {
                P.setVie(P.getVie() - 5);
            }
        }
    }
    public static void infecte($Personnage P) {
        if (P instanceof Gentil) {
            if (((Gentil) P).getInfecte()) {
                P.setVie(P.getVie() - 5);
            }
        }
    }

    public static void junky(List<$Personnage> lp) throws InterruptedException {
        int i, max = lp.size();
        for (i = 0; i < max; i++) {
            saigne(lp.get(i));
            gereParalysie(lp.get(i));
        }
    }

    public static void effetsDrogue($Personnage P) {
        if (((Gentil) P).getEfdrogue() != 0) {
            if (((Gentil) P).getDrogue() == 3) {
                ((Gentil) P).setVie(((Gentil) P).getVie() + 5);
                if (P.getVie() > P.getViemax()) {
                    P.setVie(P.getViemax());
                }
            }
            if (((Gentil) P).getDrogue() == 4) {
                P.setVie(P.getVie() - 5);
            }
            if (((Gentil) P).getDrogue() == 5) {
                ((Gentil) P).setParalysie(2);
            }
            if (((Gentil) P).getDrogue() == 6) {
                ((Gentil) P).setParalysie(0);
            }
            ((Gentil) P).setEfdrogue(((Gentil) P).getEfdrogue() - 1);
        }
    }

    public static boolean soinInstantane($Personnage P) {
    	//System.out.println("pk tu viens l� wesh");
        if (((Gentil) P).getSaignement() == true && ((Gentil) P).getRemede() == 2) {
            ((Gentil) P).setSaignement(false);
            return true;
        } else if (((Gentil) P).getInfecte() == true && ((Gentil) P).getRemede() == 1) {
            ((Gentil) P).setInfecte(false);
            return true;
        }
        return false;
    }

   /* public static void veriftransfo($Personnage P, Mechant E, List<Joueur> l) {
        if (P instanceof Gentil) {
            if (P.getVie() == 0 && ((Gentil) P).getInfecte() == true) {
                int i, j;
                for (i = 0; i < l.size(); i++) {
                    for (j = 0; j < l.get(i).getSizePersonnages(); j++) {
                        if (l.get(i).getPersonnagesI(j) == P) {
                            l.get(i).getPersonnages().remove(j);
                        } else if (l.get(i).getPersonnagesI(j) == E) {
                            l.get(i).getPersonnages().add(P);
                        }
                    }
                }
                P = new Mechant(E);
            } else if (P.getVie() == 0) {
                int i, j;
                for (i = 0; i < l.size(); i++) {
                    for (j = 0; j < l.get(i).getSizePersonnages(); j++) {
                        if (l.get(i).getPersonnagesI(j) == P) {
                            l.get(i).getPersonnages().remove(j);
                        }
                    }
                }
            }
        } else if (P.getVie() == 0) {
            int i, j;
            for (i = 0; i < l.size(); i++) {
                for (j = 0; j < l.get(i).getSizePersonnages(); j++) {
                    if (l.get(i).getPersonnagesI(j) == P) {
                        l.get(i).getPersonnages().remove(j);
                    }
                }
            }
        }
    }*/

    // UN TOUR DE JEU
    /**
     * Fait jouer Un personnage.
     *
     * @param P
     * @throws InterruptedException
     */
    public static void tourDePerso($Personnage P) throws InterruptedException {
        String tempHistorique;
        Mechant E;
        if (P instanceof Gentil) {
            Gentil gentilperso = ((Gentil) P);
            saigne(gentilperso);
            infecte(gentilperso);
            if (soinInstantane(gentilperso) == false) {
                gereParalysie(gentilperso);
                if (gentilperso.getParalysie() < 1) {
                    gentilperso.setParalysie(gentilperso.getParalysie() + 1);
                }
            }
            int i, j;
            for (i = 0; i < joueurs.size(); i++) {
                for (j = 0; j < joueurs.get(i).getSizePersonnages(); j++) {
                    if (joueurs.get(i).getPersonnagesI(j) instanceof Mechant) {
                        E = ((Mechant) joueurs.get(i).getPersonnagesI(j));
                    }
                }
            }
            System.out.println("tour gentil");
        } else {
            tempHistorique = P.jouer(plateau,joueurs,univers);
            E = ((Mechant) P);
            historique.ceTour().addEvenement(new Evenement(P, tempHistorique));
            System.out.println("temps choisit "+period);
            Thread.sleep(period);
            System.out.println("tour mechant");
        }
    }

    /**
     * Effectue un tour de jeu en changeant l'ordre de jeu de chaque personnage
     */
    public static void tour() {

        int j, p;

        melange(); // Mélange la liste des personnages
        historique.addTour(); // Ajout un tour à l'historique

        for (int i = 0; i < refPersos.size(); i++) {
            j = refPersos.get(i) / 100;
            p = refPersos.get(i) % 100;
            try {
                tourDePerso(joueurs.get(j).getPersonnagesI(p));
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        String findetour = croqueMorts(joueurs);
        historique.ceTour().addEvenement(new Evenement(null, findetour));
        Affichage.ajouterTour(historique.ceTour());
        // TODO enlever les morts.
    }

    private static String croqueMorts(List<Joueur> lesJoueurs) {
    	String s = "<i>Fin de Tour</i> : ";
    	for(Joueur player : lesJoueurs){
    		int j = 0;
    		for($Personnage perso : player.getPersonnages()){
    			if(perso.getVie()<=0){
    				if(perso instanceof Gentil){
    					if(((Gentil)perso).getInfecte() ){
    						Mechant nouveauMechant = new Mechant(perso,lesJoueurs.get(joueurZombie).getCouleur());
    						lesJoueurs.get(joueurZombie).getPersonnages().add(nouveauMechant);
    						s += perso.getNomHtml() + " est transformé. ";
        					player.getPersonnages().remove(j);
    					} else {
        					player.getPersonnages().remove(j);
    						s += perso.getNomHtml() + " est mort. ";
    					}
    				} else {
    					player.getPersonnages().remove(j);
						s += perso.getNomHtml() + " est mort. ";
    				}
    			}
    			j++;
    		}
    	}
    	
		return s;
	}

	public static void changeSpeed() {
        if (period <= vitesse3) { // Si vitesse maximale, on revient à une vitesse minimale
            period = vitesse1;
        } else if (period <= vitesse2) { // Vitesse intermédiaire vers vitesse max
            period = vitesse3;
        } else { // Vitesse minimale vers intermédiaire
            period = vitesse2;
        }
       
    }
    
    public static void play_pause() {
        if(pause) pause = false;
        else pause = true;
    }
    
    public static void step() {
        step = true;
    }
    
    public static void setNbZombie(int nb) {
        nZombie = nb;
    }
    
    public static void setNbPersoParZ(int nb) {
        nbPersoParZombie = nb;
    }
    
    public static void setXMLS(List<List<String>> val) {
        xmls = val;
    }
    
    public static void setCouleurP(List<Color> c) {
        couleurs = c;
    }
    
    public static void go() {
        commencerJeu = true;
    }
    
    /**
     * Fonction principale de Jeu
     *
     * @param pArgs
     * @throws InterruptedException
     * @throws IOException
     */
    // TODO : Raccourcir la fonction !
    public static void main(String[] pArgs) throws InterruptedException, IOException {
        
        FenetreMenu menuJeu = new FenetreMenu();
        
        while(!commencerJeu){ Thread.sleep(100); }
        
        debutPartie(1,nZombie,nbPersoParZombie,xmls,couleurs);
        //int nbTotal = (nbJoueurs-1)*nbPersoParJoueur+((nbJoueurs-1)*nbPersoParJoueur/nbPersoParZombie);
        while (!finPartie()) {
            while(pause) { if(step) {break;} Thread.sleep(100); } step = false;
            tour();
            Affichage.again();
        }
        historique.addTour();
        historique.ceTour().addEvenement(new Evenement(null, "Fin de Jeu !"));
        Affichage.ajouterTour(historique.ceTour());
        for(int i=0; i<joueurs.size(); i++) {
        	if(joueurs.get(i).getPersonnages().size()!=0) {
        		if(joueurs.get(i).mechant) {System.out.println("Partie finie : les zombies ont gagné !");}
        		else {System.out.println("Partie finie : les humains ont gagné !");}
        	}
        }
        Affichage.again();
        System.out.println("partie finie lol");
        // TODO Annoncer gagnant
    }
}
