package Otomate;

import Affichage.*;
import java.util.List;
import java.util.Random;

public class Jeu {
    
    //Attributs
    Grille plateau;
    List<Joueur> joueurs;
    
    //Méthodes
    public void initialisergrille() {
        
    }
    public boolean finPartie() {
        int k = 0;
        for(int i=0; i<joueurs.size(); i++) {
            if(joueurs.get(i).getPersonnages().get(k).getVie() > 0) {
                k++;
            }
        }
        return k<=1;
    }
    public void melange() {
        Random rnd = new Random();
        int k;
        for(int i=joueurs.size(); i>0 ; i--) {
            k = rnd.nextInt(i);
            joueurs.add(joueurs.get(k));
            joueurs.remove(k);
        }
    }
    public static void main(String[] pArgs) {
        plateau = new Grille();
        joueurs = new List();
        FILE repertoire = new FILE("../automates/");                // "../automates/" --> répertoire des automates en .xml
        String[] fichiers = repertoire.list();                      // liste des noms de fichiers d'automates
        for(int i=0; i<fichiers.length; i++) {
            joueurs.add(new Joueur(fichiers[i]);                    // création de la liste des joueurs/personnages + automates associés
        }
        initialisergrille();                                        // création de la grille
        affichagePartie(plateau, joueurs);                          // lancement de l'affichage graphique
        while(!finPartie()) {                                       // *-*-* BOUCLE PRINCIPALE [start] *-*-*
            melange();                                              // mélange des joueurs dans la liste --> ordre de jeu aléatoire
            for(int i=0; i<joueurs.size(); i++) {
                joueurs.get(i).get(0).jouer(plateau, joueurs);      // action de tous les joueurs
            }
            refresh(plateau, joueurs);                              // mise à jour de l'affichage graphique
        }                                                           // *-*-* BOUCLE PRINCIPALE [end] *-*-*
        affichageFin();                                             // affichage de la fin de partie
    }
    
}