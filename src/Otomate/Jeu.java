package Otomate;

import Affichage.*;
import Otomate.historique.Evenement;
import Otomate.historique.Historique;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.awt.Color;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import java.io.*;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

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
    
    // Chargement
    private static boolean charge = false;

    // Nécessaire au lancement du jeu
    private static boolean commencerJeu = false;
    private static int numeroUnivers;
    private static List<String> names;
    private static int nZombie;
    private static int nbPersoParZombie;
    private static List<List<String>> xmls;
    private static List<Color> couleurs;
    private static int nUnivers;
    
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
        
        joueurZombie = nZombie;
        
        initJoueurs(names, nbPersoParZombie, nZombie, xmls, couleurs);
        
        plateau = new Grille(joueurs, univers);
        //joueurs.get(0).getPersonnagesI(0).setPosition(new Coordonnees(5,5));
        //joueurs.get(0).getPersonnagesI(1).setPosition(new Coordonnees(7,5));
        //joueurs.get(1).getPersonnagesI(0).setPosition(new Coordonnees(6,8));
        //joueurs.get(2).getPersonnagesI(0).setPosition(new Coordonnees(20,6));
        //joueurs.get(0).getPersonnagesI(0).setPosition(new Coordonnees(2,6));
        //joueurs.get(0).getPersonnagesI(1).setPosition(new Coordonnees(2,4));
        //joueurs.get(1).getPersonnagesI(0).setPosition(new Coordonnees(4,5));
        //joueurs.get(2).getPersonnagesI(0).setPosition(new Coordonnees(20,6));
        
        refPersos = new LinkedList<Integer>();
        
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
     * @param names, la liste des noms des joueurs
     * @param nbPersoParZombie, le nombre de personnage pour un zombie
     * @param nZombie, le numéro du joueur qui joue zombie
     * @param xmls, la liste des liste de xmls pour les personnages
     */
    public static void initJoueurs(List<String> names, int nbPersoParZombie, int nZombie, List<List<String>> xmls, List<Color> couleurs) {
        joueurs = new LinkedList<Joueur>();
        int nbZ = nbGentils(xmls, nZombie) / nbPersoParZombie;
        for (int i = 0; i < xmls.size(); i++) {
            if (i == nZombie) {
            	joueurs.add(new Joueur(names.get(i), xmls.get(i), true, nbZ, couleurs.get(i)));
            } else {
                joueurs.add(new Joueur(names.get(i), xmls.get(i), false, 42, couleurs.get(i)));
            }
        }
    }

    // FONCTIONS DE GESTION DE STATUS
    public static void gereParalysie($Personnage P) throws InterruptedException {
        String th = new String();
        while (((Gentil) P).getParalysie() > 0) {
            ((Gentil) P).setParalysie(((Gentil) P).getParalysie() - 1);
            effetsDrogue(P);
            th = P.jouer(plateau, joueurs, univers);
            if(((Gentil) P).getPiege() != 0){
            	((Gentil) P).setPiege(((Gentil) P).getPiege()-1);
            }
            historique.ceTour().addEvenement(new Evenement(P, th));
            //Thread.sleep(period);
        }
    }

    public static void saigne($Personnage P) {
        if (P instanceof Gentil) {
            if (((Gentil) P).getSaignement()) {
                P.setVie(P.getVie() - 2);
            }
        }
    }

    public static void infecte($Personnage P) {
        if (P instanceof Gentil) {
            if (((Gentil) P).getInfecte()) {
                P.setVie(P.getVie() - 1);
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
        else {
        	((Gentil) P).setDrogue(0);
        }
        }
    

    public static boolean soinInstantane($Personnage P) {
        //System.out.println("pk tu viens l� wesh");
        if (((Gentil) P).getSaignement() == true && ((Gentil) P).getRemede() == 2) {
            ((Gentil) P).setSaignement(false);
            ((Gentil) P).setRemede(0);
            historique.ceTour().addEvenement(new Evenement(P, univers.getNomRemede()));
            return true;
        } else if (((Gentil) P).getInfecte() == true && ((Gentil) P).getRemede() == 1) {
            ((Gentil) P).setInfecte(false);
            ((Gentil) P).setRemede(0);
            historique.ceTour().addEvenement(new Evenement(P, univers.getNomAntidote()));
            return true;
        }
        return false;
    }
    
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
        } else {
            tempHistorique = P.jouer(plateau, joueurs, univers);
            E = ((Mechant) P);
            historique.ceTour().addEvenement(new Evenement(P, tempHistorique));
            //Thread.sleep(period);
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
                Thread.sleep(period);
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
        int i,k;
        Joueur player = null;
        $Personnage perso = null;
        for (i=0; i<lesJoueurs.size(); i++) {
            player = lesJoueurs.get(i);
            if(player!=null){
            for (k=0; k<player.getSizePersonnages(); k++) {
            	perso = player.getPersonnagesI(k);
                if (perso.getVie() <= 0) {
                    if (perso instanceof Gentil) {
                        if (((Gentil) perso).getInfecte()) {
                            Mechant nouveauMechant = new Mechant(lesJoueurs.get(joueurZombie).getPersonnagesI(0), lesJoueurs.get(joueurZombie).getCouleur(),perso.getPosition());
                            lesJoueurs.get(joueurZombie).getPersonnages().add(nouveauMechant);
                      //      System.err.println("il est mort 1");
                            s += perso.getNomHtml() + " est transformé. ";
                            player.getPersonnages().remove(k);
                        } else {
                       // 	System.err.println("il est mort 2");
                        	s += perso.getNomHtml() + " est mort. ";
                            player.getPersonnages().remove(k);
                        }
                    } else {
                    //	System.err.println("il est mort 3");
                        s += perso.getNomHtml() + " est mort. ";
                        player.getPersonnages().remove(k);
                    }
                }
            }
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
        if (pause) {
            pause = false;
        } else {
            pause = true;
        }
    }

    public static void step() {
        step = true;
    }

    public static void finDeJeu() {
        String gagnant = "erreur";
        for (int i = 0; i < joueurs.size(); i++) {
            if (joueurs.get(i).getPersonnages().size() != 0) {
                if (joueurs.get(i).mechant) {
                    gagnant = "Partie finie : les " + univers.getNomMechants() + " ont gagné !";
                } else {
                    gagnant = "Partie finie : les " + univers.getNomGentils() + " ont gagné !";
                }
            }
        }
        historique.addTour();
        historique.ceTour().addEvenement(new Evenement(null, "<html><b>" + gagnant + "</b></html>"));
        Affichage.ajouterTour(historique.ceTour());
        Affichage.again();
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

    public static void setUnivers(int u) {
        nUnivers = u;
    }

    public static void go() {
        commencerJeu = true;
    }
    
    public static void chargement() {
    	charge = true;
    }
    
    public static SaveJeu createSaveJeu() {
    	SaveJeu sj = new SaveJeu();
    	sj.plateau = plateau;
    	sj.joueurs = joueurs;
    	sj.joueurZombie = joueurZombie;
    	sj.univers = univers;
    	return sj;
    }
    
    public static void sauvegarder(String chemin) throws IOException {
    	SaveLoad sl = new SaveLoad(createSaveJeu(),chemin);
    	sl.save();
    }
    
    public static void charger(String fichier) throws IOException {
    	SaveLoad sl = new SaveLoad(fichier);
    	sl.load();
    	plateau = sl.getJeu().plateau;
    	joueurs = sl.getJeu().joueurs;
    	refPersos = new LinkedList<Integer>();
    	joueurZombie = sl.getJeu().joueurZombie;
    	univers = sl.getJeu().univers;
    	historique = new Historique();
    	charge = true;
    	go();
    	
    }

    public static void setUserNames(List<String> listNames) {
        names = listNames;
    }
    
    /**
     * Fonction principale de Jeu
     *
     * @param pArgs
     * @throws InterruptedException
     * @throws IOException
     * @throws LineUnavailableException 
     * @throws UnsupportedAudioFileException 
     * @throws JavaLayerException 
     */
    // TODO : Raccourcir la fonction !
    
    public static class Music extends Thread {
    	public void run() {
    		File f = new File("/home/gwen/workspace/Otomatamer/music/Mitch.mp3");
            FileInputStream fis;
			try {
				fis = new FileInputStream(f);
	            BufferedInputStream bis = new BufferedInputStream(fis);
	            Player pl;
				try {
					pl = new Player(bis);
					pl.play();
				} catch (JavaLayerException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
    	}
    }
    
    public static void main(String[] pArgs) throws InterruptedException, IOException, UnsupportedAudioFileException, LineUnavailableException, JavaLayerException {

        FenetreMenu menuJeu = new FenetreMenu();
        
        (new Music()).start();
        
        while (!commencerJeu) {
            Thread.sleep(100);
        }

        if(!charge) {debutPartie(nUnivers, nZombie, nbPersoParZombie, xmls, couleurs);}
        else {Affichage.charger();}
        //int nbTotal = (nbJoueurs-1)*nbPersoParJoueur+((nbJoueurs-1)*nbPersoParJoueur/nbPersoParZombie);
        
        //sauvegarder();
        
        while (!finPartie()) {
            while (pause) {
                if (step) {
                    break;
                }
                Thread.sleep(100);
            }
            step = false;
            tour();

            Affichage.again();
            Thread.sleep(period * 2);

        }
        finDeJeu();
        Affichage.fin();
        // TODO Annoncer gagnant
    }
}
