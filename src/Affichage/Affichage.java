package Affichage;

import Otomate.Grille;
import Otomate.Jeu;
import Otomate.Personnage;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

public class Affichage {
	
    public static int random(int min, int max){
    return (int) (min + (Math.random() * (max - min)));
    }
    //Attibuts
    private static FenetreJeu jeu;
    private static Affichage_plateau plateau;
    private static List<Personnage> perso;
    //Méthodes    
    public static void main(String[] args) {
        jeu = new FenetreJeu();
        
        Grille g=new Grille();
        int i,j;
        for(i=0;i<g.tailleX;i++){
        	for(j=0;j<g.tailleY;j++){
        		g.set(random(1,9),i,j);
        	}
        }
        
        perso = new ArrayList<Personnage>();
        perso.add(new Personnage());
        
        perso.get(0).position.abs=1;
        perso.get(0).position.ord=1;
        
        
        
        jeu.charger(g,perso);

        
        
        
    }
    
}