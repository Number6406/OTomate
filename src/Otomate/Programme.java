package Otomate;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import Affichage.Affichage;
import Affichage.FenetreMenu;

public class Programme {
	

    public static int vitesse1 = 1000, vitesse2 = 500, vitesse3 = 250;
    public static int period = vitesse1;
    public static boolean pause = false;
    public static boolean step = false;
	
    // Chargement
    private static boolean charge = false;

    // Nécessaire au lancement du jeu
    private static boolean commencerJeu = false;
    private static int numeroUnivers;
    private static int nZombie;
    private static int nbPersoParZombie;
    private static List<List<String>> xmls;
    private static List<Color> couleurs;
    private static int nUnivers;
	
	public static void main(String[] args) throws InterruptedException, IOException {

        FenetreMenu menuJeu = new FenetreMenu();

        while (!commencerJeu) {
            Thread.sleep(100);
        }

        if(!charge) {Jeu.debutPartie(nUnivers, nZombie, nbPersoParZombie, xmls, couleurs);}
        else {Jeu.charger("Start.txt");}
        //int nbTotal = (nbJoueurs-1)*nbPersoParJoueur+((nbJoueurs-1)*nbPersoParJoueur/nbPersoParZombie);
        
        SaveLoad save = new SaveLoad(Jeu.createSaveJeu(), "Start.txt");
        save.save();
        
        while (!Jeu.finPartie()) {
            while (pause) {
                if (step) {
                    break;
                }
                Thread.sleep(100);
            }
            step = false;
            Jeu.tour();

            Affichage.again();
            Thread.sleep(period * 2);

        }
        Jeu.finDeJeu();

	}

}
