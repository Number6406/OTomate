package Otomate;

import java.awt.Color;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import Affichage.Affichage;
import Affichage.FenetreMenu;
import Otomate.historique.Historique;

public class TestFouille {
	
	public static void initialisergrille(List<Joueur> l, Grille G) {
    	int i,j,k;
        for(i=0; i<G.tailleX(); i++){
            for(j=0; j<G.tailleY(); j++){
                k = Grille.random(9, 11);        //car 15 actions possibles numerotees de 0 a 14 
                G.set(k, i, j);
            }
        }
        List<$Personnage> list = new LinkedList<>();
        for(i=0; i<l.size(); i++){
        	for(j=0;j<l.get(i).getSizePersonnages();j++){
        		list.add(l.get(i).getPersonnagesI(j));    	
        	}
        }
        G.setCoinsAutomates(G.goAutomates(list, G.tailleX(), G.tailleY()));
        G.Placements(l);
    }

    /**
     * Initialise toutes les variables pour lancer la partie.
     */
    public static void debutPartie(int numeroUnivers, int nZombie, int nbPersoParZombie, List<List<String>> xmls, List<Color> couleurs) {
        
    	Jeu.univers = new Univers(numeroUnivers);
        Jeu.historique = new Historique();
        
        Jeu.joueurZombie = nZombie;
        
        Jeu.initJoueurs(Jeu.names, nbPersoParZombie, nZombie, xmls, couleurs);
        
        Jeu.plateau = new Grille(Jeu.joueurs, Jeu.univers);
    	initialisergrille(Jeu.joueurs,Jeu.plateau);
    	Jeu.plateau.placerPersonnages(Jeu.joueurs);
        
        Jeu.refPersos = new LinkedList<Integer>();
        
        try {
            Affichage.charger();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

	public static void main(String[] pArgs) throws InterruptedException, IOException {

        FenetreMenu menuJeu = new FenetreMenu();

        while (!Jeu.commencerJeu) {
            Thread.sleep(100);
        }

        if(!Jeu.charge) {debutPartie(Jeu.nUnivers, Jeu.nZombie, Jeu.nbPersoParZombie, Jeu.xmls, Jeu.couleurs);}
        else {Affichage.charger();}
        //int nbTotal = (nbJoueurs-1)*nbPersoParJoueur+((nbJoueurs-1)*nbPersoParJoueur/nbPersoParZombie);
        
        //sauvegarder();
        
        while (!Jeu.finPartie()) {
            while (Jeu.pause) {
                if (Jeu.step) {
                    break;
                }
                Thread.sleep(100);
            }
            Jeu.step = false;
            Jeu.tour();

            Affichage.again();
            Thread.sleep(Jeu.period * 2);

        }
        Jeu.finDeJeu();
        Affichage.fin();
        // TODO Annoncer gagnant
    }
}
