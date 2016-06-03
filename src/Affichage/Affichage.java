package Affichage;

import Otomate.Grille;
import Otomate.Jeu;

import java.awt.BorderLayout;

public class Affichage {
	
    public static int random(int min, int max){
    return (int) (min + (Math.random() * (max - min)));
    }
    //Attibuts
    private static FenetreJeu jeu;
    private static Affichage_plateau plateau;
    
    //MÃ©thodes    
    public static void main(String[] args) {
        jeu = new FenetreJeu();
        
        Grille g=new Grille();
        int i,j;
        for(i=0;i<4;i++){
        	for(j=0;j<4;j++){
        		g.set(random(0,9),i,j);
        	}
        }
        
        jeu.charger(g);

        
        
        
    }
    
}