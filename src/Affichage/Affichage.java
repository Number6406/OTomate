package Affichage;

import Otomate.Jeu;
import java.awt.BorderLayout;

public class Affichage {
    
    //Attibuts
    private static FenetreJeu jeu;
    
    //Méthodes    
    public static void main(String[] args) {
        jeu = new FenetreJeu();
        jeu.charger();
    }
    
}