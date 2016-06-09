package Otomate;

import Affichage.*;
<<<<<<< HEAD
import Otomate.historique.*;
=======
import Otomate.historique.Historique;
>>>>>>> master

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.io.File;

public class Jeu {
	
    public static int random(int min, int max) throws InterruptedException{
    	Random r = new Random();
    	int k = min + r.nextInt(max - min);

    	return k;
    }
    
    //Attributs
    public static Grille plateau;
    public static List<Joueur> joueurs;
    public static List<$Personnage> persos;
    public static Historique historique;
    
    //Methodes
    
    public static int initPartie(){
    	System.out.println("Partie avec deux joueurs.");
    	System.out.println("On r�cup�re les deux automates des joueurs :");
    	Joueur J1 = new Joueur("../../automates/Automate1.xml",false);
    	Joueur J2 = new Joueur("../../automates/Automate2.xml",true);
    	joueurs = new ArrayList<Joueur>();
    	joueurs.add(J1);
    	joueurs.add(J2);
    	
    	plateau = new Grille();
    	Grille.initialisergrille(joueurs);
    	
    	return 0;
    }
    
    public static boolean finPartie() {
        return false;
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
    
    public static List<Joueur> addJoueurs(String fichiers) throws InterruptedException {
        List<Joueur> joueurs = new LinkedList<Joueur>();
      // int k = 1;//random(1,1);
        for(int i=0; i<1; i++) {
            joueurs.add(new Joueur(fichiers,false/*i==k*/));
        }
        return joueurs;
    }
    
//    public static void main(String[] pArgs) throws InterruptedException {
//        plateau = new Grille();
//       // File repertoire = new File("../automate/");                // "../automates/" --> r�pertoire des automates en .xml
//        String fichiers = new File("AutomateenXML.xml").toString();                      // liste des noms de fichiers d'automates
//        joueurs = addJoueurs(fichiers);
//        
//    //    System.out.println("coucou" +1 );
//        Grille.initialisergrille(joueurs);                                        // cr�ation de la grille
//       // affichagePartie(plateau, joueurs);                          // lancement de l'affichage graphique
//   //     System.out.println("coucou");
//        //Affichage.recharger(plateau,joueurs,historique);
//        Affichage.charger();
//        while(/*!finPartie()*/true) {
//        	System.out.println(joueurs.get(0).getPersonnagesI(0).getPosition().getX() +" "+ joueurs.get(0).getPersonnagesI(0).getPosition().getY());
//            Thread.sleep(200);                             // (faux) timer 1 seconde
//            melange();
//            for(int i=0; i<joueurs.size(); i++) {
//            	System.out.println("random : " + random(1,5));
//            	//System.out.println("SBLEU : "+joueurs.get(i).getPersonnagesI(0).etat+"\n");
//            	
//            	
//            	//IL FAUT PARCOURIR LA LISTE DES PERSO CHECK EFFETS PAR TOUR --> voir AttEst
//            	//on decremente effdrogue apr�s avoir check et piege 
//            	
//            	
//                //joueurs.get(i).getPersonnagesI(0).jouer(plateau, joueurs);
//            }
//        }
//    }
    
    public static void initJoueurs(int nbJoueurs, List<String> xmls) {
    	
    }
    
    public static void main(String[] pArgs) throws InterruptedException {
<<<<<<< HEAD
    	plateau = new Grille();
    	int nbJoueurs = 2;
    	List<String> xmls;
    	initJoueurs(nbJoueurs, xmls);
=======
    	historique = new Historique();
        plateau = new Grille();
       // File repertoire = new File("../automate/");                // "../automates/" --> r�pertoire des automates en .xml
        String fichiers = new File("AutomateenXML.xml").toString();                      // liste des noms de fichiers d'automates
        joueurs = addJoueurs(fichiers);
        
    //    System.out.println("coucou" +1 );
        Grille.initialisergrille(joueurs);                                        // cr�ation de la grille
       // affichagePartie(plateau, joueurs);                          // lancement de l'affichage graphique
   //     System.out.println("coucou");
        //Affichage.recharger(plateau,joueurs,historique);
        Affichage.charger();
        while(/*!finPartie()*/true) {
        	System.out.println(joueurs.get(0).getPersonnagesI(0).getPosition().getX() +" "+ joueurs.get(0).getPersonnagesI(0).getPosition().getY());
            Thread.sleep(200);                             // (faux) timer 1 seconde
            melange();
            for(int i=0; i<joueurs.size(); i++) {
            	System.out.println("random : " + random(1,5));
            	//System.out.println("SBLEU : "+joueurs.get(i).getPersonnagesI(0).etat+"\n");
            	
            	
            	//IL FAUT PARCOURIR LA LISTE DES PERSO CHECK EFFETS PAR TOUR --> voir AttEst
            	//on decremente effdrogue apr�s avoir check et piege 
            	
            	
                //joueurs.get(i).getPersonnagesI(0).jouer(plateau, joueurs);
            }
        }
>>>>>>> master
    }
}
    