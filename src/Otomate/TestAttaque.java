package Otomate;

import java.awt.Color;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import Affichage.Affichage;
import Affichage.FenetreMenu;
import Otomate.historique.Historique;

public class TestAttaque {
	
public static void debutPartie(int numeroUnivers, int nZombie, int nbPersoParZombie, List<List<String>> xmls, List<Color> couleurs) {
        
    	Jeu.univers = new Univers(numeroUnivers);
        Jeu.historique = new Historique();
        
        joueurZombie = nZombie;
        
        initJoueurs(names, nbPersoParZombie, nZombie, xmls, couleurs);
        
        plateau = new Grille(joueurs, univers);
        
        refPersos = new LinkedList<Integer>();
        
        try {
            Affichage.charger();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	public void main(String[] pArgs) throws InterruptedException, IOException{

		FenetreMenu menuJeu = new FenetreMenu();

        while (!Jeu.commencerJeu) {
            Thread.sleep(100);
        }

        if(!Jeu.charge) {debutPartie(nUnivers, nZombie, nbPersoParZombie, xmls, couleurs);}
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

	}

}
