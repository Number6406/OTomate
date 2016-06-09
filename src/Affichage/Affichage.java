package Affichage;

import Otomate.Gentil;
import Otomate.Grille;
//import Otomate.Jeu;
import Otomate.Joueur;
import Otomate.historique.Evenement;
import Otomate.historique.Historique;
import Otomate.$Personnage;
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
    private static List<$Personnage> perso;
    
    private static Historique h;
    //M�thodes    
    public static void main(String[] args) {
        jeu = new FenetreJeu();
        
        Grille g=new Grille(20,20);
        int i,j;
        for(i=0;i<g.tailleX;i++){
        	for(j=0;j<g.tailleY;j++){
        		g.set(random(1,8),i,j);
        	}
        }
        
        perso = new ArrayList<>();
        perso.add(new Gentil("../Parser/AutomateenXML.xml"));
        
        perso.get(0).getPosition().setX(1);
        perso.get(0).getPosition().setY(1);
        
        h = new Historique();
        h.addTour(); 
        h.ceTour().addEvenement(new Evenement(perso.get(0), "ne fais rien"));
        h.addTour(); 
	        h.ceTour().addEvenement(new Evenement(perso.get(0), "ne fais toujours rien"));
	        h.ceTour().addEvenement(new Evenement(perso.get(0), "ne sais rien faire"));
	        h.ceTour().addEvenement(new Evenement(perso.get(0), "s'ennuie"));
	        h.ceTour().addEvenement(new Evenement(perso.get(0), "compte les nuages"));
        
        jeu.charger(g,perso,h);
    } 
    
   public static void recharger(Grille g,List<Joueur> l,Historique h){
	   List<$Personnage> lp = new LinkedList<>();
	   int i,j,max=l.size(),max2;
	   
	   for(i=0;i<max;i++){
                max2=l.get(i).getSizePersonnages();
                for(j=0;j<max2;j++){
                    lp.add(l.get(i).getPersonnagesI(j));
                }
	   }
	   jeu = new FenetreJeu();
	   jeu.charger(g,lp,h);
	   
   }
    
}