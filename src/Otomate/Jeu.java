package Otomate;

import Affichage.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.io.File;

public class Jeu {
	
    public static int random(int min, int max){
    return (int) (min + (Math.random() * (max - min)));
    }
    
    //Attributs
    public static Grille plateau;
    static List<Joueur> joueurs;
    
    //Méthodes
    
    public static boolean finPartie() {
        int k = 0;
        for(int i=0; i<joueurs.size(); i++) {
            if(joueurs.get(i).getPersonnages().get(k).getVie() > 0) {
                k++;
            }
        }
        return k<=1;
    }
    public static void melange() {
        Random rnd = new Random();
        int k;
        for(int i=joueurs.size(); i>0 ; i--) {
            k = rnd.nextInt(i);
            joueurs.add(joueurs.get(k));
            joueurs.remove(k);
        }
    }
    public static List<Joueur> addJoueurs(String[] fichiers) {
        List<Joueur> joueurs = new LinkedList<Joueur>();
        Random rnd = new Random();
        int k = rnd.nextInt(fichiers.length);
        for(int i=0; i<fichiers.length; i++) {
            joueurs.add(new Joueur(fichiers[i],i==k));
        }
        return joueurs;
    }
    public static void main(String[] pArgs) throws InterruptedException {
        plateau = new Grille();
        File repertoire = new File("../automates/");                // "../automates/" --> répertoire des automates en .xml
        String[] fichiers = repertoire.list();                      // liste des noms de fichiers d'automates
        joueurs = addJoueurs(fichiers);
        Grille.initialisergrille(joueurs);                                        // création de la grille
       // affichagePartie(plateau, joueurs);                          // lancement de l'affichage graphique
        
        Affichage.recharger(plateau,joueurs);
        
        while(!finPartie()) {
            Thread.sleep(1000);                             // (faux) timer 1 seconde
            melange();
            for(int i=0; i<joueurs.size(); i++) {
                joueurs.get(i).getPersonnagesI(0).jouer(plateau, joueurs);
            }
            Affichage.recharger(plateau, joueurs);
        }
        
        //affichageFin();                                             // affichage de la fin de partie
    }
    
}