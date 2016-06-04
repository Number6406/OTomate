package Affichage;

import Otomate.Grille;
//import Otomate.Jeu;
import Otomate.Joueur;
import Otomate.Personnage;

//import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Affichage {
	
    public static int random(int min, int max){
    return (int) (min + (Math.random() * (max - min)));
    }
    //Attibuts
    private static FenetreJeu jeu;
    //private static Affichage_plateau plateau;
    private static List<Personnage> perso;
    //Méthodes    
    public static void main(String[] args) {
        jeu = new FenetreJeu();
        
        Grille g=new Grille(100,100);
        int i,j;
        for(i=0;i<g.tailleX;i++){
        	for(j=0;j<g.tailleY;j++){
        		g.set(random(1,9),i,j);
        	}
        }
        
        perso = new ArrayList<Personnage>();
        perso.add(new Personnage());
        
        perso.get(0).position.setX(1);
        perso.get(0).position.setY(1);
        
        
        
        jeu.charger(g,perso);
    } 
   public static void recharger(Grille g,List<Joueur> l){
	   LinkedList<Personnage> lp = new LinkedList<Personnage>();
	   int i,j,max=l.size(),max2;
	   
	   for(i=0;i<max;i++){
		   max2=l.get(i).getSizePersonnages();
		   		for(j=0;j<max2;j++){
		   			lp.add(l.get(i).getPersonnagesI(j));
		   		}
	   }
	   jeu.charger(g,lp);
	   
   }
    
    
}