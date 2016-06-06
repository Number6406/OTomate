package Otomate;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Grille {
    
    public static int random(int min, int max){
    return (int) (min + (Math.random() * (max - min)));
    }
    
    //Attributs
    private static Case[][] g;
    private static List<Coordonnees> coinsAutomates;
    private static List<Integer> nbetats;
    public int tailleX;
    public int tailleY;
    
    public void set(int val, int x, int y){
    	g[x][y].setValeur(val);
    }
    
    public Case get(int x,int y){
    	return g[x][y];
    }
    
    public Grille(int x, int y){
    	g = new Case[x][y];
    	tailleX=x;
    	tailleY=y;
    	int i,j;
    	for(i=0;i<tailleX;i++){
    		for(j=0;j<tailleY;j++){
    			g[i][j] = new Case();
    		}
    	}
    }
    
    public Grille(){
    	g = new Case[16][16];
    	tailleX=16;
    	tailleY=16;
    	int i,j;
    	for(i=0;i<tailleX;i++){
    		for(j=0;j<tailleY;j++){
    			g[i][j] = new Case();
    		}
    	}
    }
    
    //Methodes
    
    //Place les automates au bon endroit sur la map
    public static void Placements(List<Joueur> J) {
        int l = coinsAutomates.size();
        List<Personnage> list = new LinkedList<>();
        int i,j,k,nbCond = J.get(0).getPersonnagesI(0).getAutotate().nbConditions();   //nbCond contient le nombre de condition (soit la "hauteur" de nos automates)
        for(i=0; i<l; i++){
        	for(j=0;j<J.get(i).getSizePersonnages();j++){
        	    list.add(J.get(i).getPersonnagesI(j));    	
        	}
        	
            i += J.get(i).getSizePersonnages();
        }
        
        for(i=0;i<l;i++){
            for(j=i; j<l; j++){
                if(coinsAutomates.get(i).abs < coinsAutomates.get(j).abs && coinsAutomates.get(i).abs + list.get(i).position.getX() < coinsAutomates.get(j).abs){}
                else if(coinsAutomates.get(i).getX() < coinsAutomates.get(j).getX() && coinsAutomates.get(i).getY() < coinsAutomates.get(j).getY() && coinsAutomates.get(i).getY() + nbCond < coinsAutomates.get(j).getY()){}
                else if(coinsAutomates.get(i).getX() < coinsAutomates.get(j).getX() && coinsAutomates.get(i).getY() > coinsAutomates.get(j).getY() && coinsAutomates.get(i).getY() > coinsAutomates.get(j).getY() + nbCond){}
                else if(coinsAutomates.get(i).getX() > coinsAutomates.get(j).getX() && coinsAutomates.get(i).getX() > coinsAutomates.get(j).getX() + list.get(j).position.getX()){}
                else if(coinsAutomates.get(i).getX() > coinsAutomates.get(j).getX() && coinsAutomates.get(i).getY() < coinsAutomates.get(j).getY() && coinsAutomates.get(i).getY() + nbCond < coinsAutomates.get(j).getY()){}
                else if(coinsAutomates.get(i).getX() > coinsAutomates.get(j).getX() && coinsAutomates.get(i).getY() > coinsAutomates.get(j).getY() && coinsAutomates.get(i).getY() > coinsAutomates.get(j).getY() + nbCond){}
                else
                    System.out.println("Erreur les automates se superposent on a un problème pour la generation de leur coordonnees : erreur dans goAutomates !");
            }
        }
        int jdeb, kdeb;
        int x=0, s, y; 
        while(x<J.size())
        {
            y=0;
            s = J.get(x).getSizePersonnages();
            while(y<s)
            {
                for(i=0;i<l;i++){               //nombre d'automates a placer
                    jdeb = coinsAutomates.get(i).getX();
                    for(j=jdeb; j<nbetats.get(i)+jdeb; i++){      //parcours de lignes
                        kdeb = coinsAutomates.get(i).getY();
                        for(k=kdeb;k<nbCond+kdeb;k++){          //parcours des colonnes
                            g[j][k] = J.get(x).getPersonnagesI(y).a.actions[j-jdeb][k-kdeb];
                        }
                    }
                }
                y++;
            }
            x++;
        }
    }
    
    public static List<Coordonnees> goAutomates(List<Personnage> l, int dimh, int dimv){
        List<Coordonnees> res = new LinkedList<Coordonnees>();
        Random rnd = new Random();
        Coordonnees newc = new Coordonnees();
        int nb = l.size();
        int i, j, k;
        for(k=0; k<l.size(); k++){
            i = rnd.nextInt(nb);       //donne le numéro de la case "h"
            newc.setX(i*dimh/nb);              //abscisse correspondant
            j = rnd.nextInt(nb);
            newc.setY(j*dimv/nb);
            j=k;
            for(i=0; i<k; i++){
                if(newc.getX() == res.get(i).getX() && newc.getY() == res.get(i).getY()){
                    i=k;
                    k--;           // la c'est pour refaire le meme tour puisque la case est deja occupee
                }
            }
            if(j == k){             //c'est pour verifier qu'on est pas tombé dans le if et que c'est bon la case est dispo
                res.add(newc);
            }
        }
        return res;
    }
    
    public static int max(List<Integer> l){
        int fin = l.size();
        int i, m=0;
        for(i=0; i<fin; i++){
            if(m<l.get(i))
                m=l.get(i);
        }
        return m;
    }
    
    public static void initialisergrille(List<Joueur> l) {
        int i,j,k;
        List<Personnage> list = new LinkedList<>();
        for(i=0; i<l.size(); i++){
        	for(j=0;j<l.get(i).getSizePersonnages();j++){
        	list.add(l.get(i).getPersonnagesI(j));    	
        	}
        	
            i += l.get(i).getSizePersonnages();
        }
        List<Integer> taille = new LinkedList<Integer>();
        for(i=0; i<l.size(); i++){
            for(j=0; j<l.get(i).getSizePersonnages(); j++){
                for(k=0; k<j; k++){
                    taille.add(l.get(i).getPersonnagesI(k).a.nbEtat());
                }
            }
        }
        int maxi = max(taille);
        int dimh = maxi * list.size();
        if(dimh<20)
            dimh = 20;
        maxi = l.get(0).getPersonnagesI(0).a.nbConditions();
        int dimv = maxi * list.size();
        if(dimv<20)
            dimv = 20;
            
      //création de la map dimh/dimv avec minimum 150/150
        
        g = new Case[dimh][dimv];     
        for(i=0;i<dimh;i++){
        	for(j=0;j<dimv;j++){
        		g[i][j]=new Case();
        	}
        }
        
        for(i=0; i<dimh; i++){
            for(j=0; j<dimv; j++){
                k = random(1, 10);        //car 10 actions possibles numérotées de 1 à 10 
                g[i][j].element = k;
            }
        }
        
        coinsAutomates = goAutomates(list, dimh, dimv);
        Placements(l);
    }

//Retourne la liste des conditions du personnage
//On retrouve en element numÃ©ro : 0:centre; 1:Nord; 2:Sud; 3:Ouest; 4:Est
    public List<Integer> Conditions($Personnage P){
        List<Integer> l = new LinkedList<>();
        l.add(Pos(P.position).getValeur());
        Personnage p2 = P;
        p2.position.setY(P.position.getY()-1);
        l.add(Pos(p2.position).getValeur());
        p2.position.setY(P.position.getY()+1);
        l.add(Pos(p2.position).getValeur());
        p2.position.setY(P.position.getY());
        p2.position.setX(P.position.getX()-1);
        l.add(Pos(p2.position).getValeur());
        p2.position.setX(P.position.getX()+1);
        l.add(Pos(p2.position).getValeur());
        return l;
    }
    
//Retourne une liste d'actions possibles pour P
    public List<Case> ActionsPossibles($Personnage P){
        List<Integer> l = Conditions(P);
        List<Case> la = new LinkedList<Case>();
        if(P.a.transitions[P.etat][l.get(0)] != 0){         //si transition possible depuis l'état où le personnage se trouve avec la condition de la case valide
            la.add((P.a.actions[P.etat][l.get(0)]));
        }
        if(P.a.transitions[P.etat][l.get(1)] != 0){         //regarde au nord
            la.add(P.a.actions[P.etat][l.get(1)]);
        }
        if(P.a.transitions[P.etat][l.get(2)] != 0){         //Sud
            la.add(P.a.actions[P.etat][l.get(2)]);
        }
        if(P.a.transitions[P.etat][l.get(3)] != 0){          //Ouest
            la.add(P.a.actions[P.etat][l.get(3)]);
        }
        if(P.a.transitions[P.etat][l.get(4)] != 0){         //EST
            la.add(P.a.actions[P.etat][l.get(4)]);
        }
        
        return la;
    }
    
    public Action takeOne(List<Case> l){
        Action a = new Action();
        if(l.isEmpty() == true)
            return a;
        else{
            int i = random(0, l.size()-1);
            a.set_Action(l.get(i).element);
            return a;
        }
    }

//Retourne la case de coordonnees c
    public Case Pos(Coordonnees c){
        return g[c.getX()][c.getY()];
    }
    
    /**
     * Fonction permettant de frapper à un emplacement aléatoire s'il y a des entités à proximité
     */
     public void Frapper($Personnage acteur) {
         
     }
    
    
//On rappelle que l'"origine" du repere de la grille est en haut Ã gauche donc un deplacement au nord = -1 en ord et +1 pour aller vers le sud cependant
//on garde +1 pour l'est en abs et -1 pour l'ouest
  
//Met a jour la map = change le numero si besoin est
    public void Maj($Personnage P, Action A, List<Joueur> J){
        
        Coordonnees position = P.getPosition();
        
        Case C = Pos(position);
        Case N = Pos(new Coordonnees(position.getX(), position.getY()-1));
        Case S = Pos(new Coordonnees(position.getX(), position.getY()+1));
        Case E = Pos(new Coordonnees(position.getX()+1, position.getY()));
        Case O = Pos(new Coordonnees(position.getX()-1, position.getY());
        String Description = "";
        
        
        if(P.instanceof(Gentil)){
            if(A.get_Action() == 0)                  //indiffÃ©rent
                return;
                
            else if(A.get_Action() == Rien.getValeur()){  // Ne rien faire
                Description = "Ne fais rien."
                return;
            }
            else if(A.get_Action() == AvancerN.getValeur()){            //Avancer au nord
                if(N.estChemin()){
                    P.deplacementHaut();
                    Description = "Se déplace au Nord.";
                } else {
                    Description = "Tente de se déplacer au Nord mais échoue.";
                }
                return;
            }
            
            else if(A.get_Action() == AvancerS.getValeur()){             //Reculer
                if(S.estChemin()) {
                    P.deplacementBas();
                    Description = "Se déplace au Sud.";
                } else {
                    Description = "Tente de se déplacer au Sud mais échoue.";
                }
                return;
            }
            
            else if(A.get_Action() == AvancerE.getValeur()){            //Partir a droite
                if(E.estChemin()) {
                    P.deplacementDroite();
                    Description = "Se déplace à l'Est.";
                } else {
                    Description = "Tente de se déplacer à l'Est mais échoue.";
                }
                return;
            }
            
            else if(A.get_Action() == AvancerO.getValeur()){            //Partir a gauche
                if(O.estChemin()) {
                    P.deplacementGauche();
                    Description = "Se déplace à l'Ouest.";
                } else {
                    Description = "Tente de se déplacer à l'Ouest mais échoue.";
                }
                return;
            }
            
            else if(A.get_Action() == Ramasser.getValeur()){            //Ramasser  
                
                if(C.estRamassable()){
                    Description += "Ramasse " + Contenus.fromint(C.getValeur()).toString() + "et pose ";
                    int aPoser = P.ramasser(C.getValeur());
                    C.setValeur(aPoser);
                    Description += Contenus.fromint(aPoser).toString();
                }
                else {
                    Description = "Tente de ramasser " + Contenus.fromint(C.getValeur()).toString() + "mais échoue.";
                }
            }
            else if(A.get_Action() == FrapperN.getValeur()){ //Frapper au nord
                Coordonnees v = new Coordonnees();
                v.setX(P.position.getX());
                v.setY(P.position.getY()-1);
                Personnage E=null;
                int nb = J.size();                      //nombre de joueurs
                int x=0, s, i;
                while(x<nb){                             //Tant que tous les joueurs n'ont pas ete check
                   s = J.get(x).getSizePersonnages();     //nombre de personnages que possede le joueur x
                   for(i=0; i<s; i++){
                       if(J.get(x).getPersonnagesI(i).position == v){
                           E = J.get(x).getPersonnagesI(i);
                           i = s;
                           x = nb;
                       }
                   }
                   x++;
                }
                
                if((!E.equals(null))&&(E.instanceof(Mechant))){
                    if(P.getInventaire() == 10){
                        if(E.getVie()>3)
                            E.setVie(E.getVie()-3);
                        else
                            E.setVie(0);
                    }
                    else if(P.getInventaire() == 11){
                        if(E.getVie()>5)
                            E.setVie(E.getVie()-5);
                        else
                            E.setVie(0); 
                    }
                    else{
                        if(E.getVie()>1))
                            E.setVie(E.getVie()-1);
                        else
                            E.setVie(0);         //le personnage est mort on met la case a vide
                    }
                    return;
                }
            }
            
            else if(A.get_Action() == FrapperS.getValeur()){ //Frapper au sud
                Coordonnees v=new Coordonnees();
                v.setX(P.position.getX());
                v.setY(P.position.getY()+1);
                Personnage E=null;
                int nb = J.size();                      //nombre de joueurs
                int x=0, s, i;
                while(x<nb){                             //Tant que tous les joueurs n'ont pas ete check
                   s = J.get(x).getSizePersonnages();     //nombre de personnages que possede le joueur x
                   for(i=0; i<s; i++){
                       if(J.get(x).getPersonnagesI(i).position == v){
                           E = J.get(x).getPersonnagesI(i);
                           i = s;
                           x = nb;
                       }
                   }
                   x++;
                }
                if((!E.equals(null))&&(E.instanceof(Mechant))){
                    if(P.getInventaire() == 10){
                        if(E.getVie()>3)
                            E.setVie(E.getVie()-3);
                        else
                            E.setVie(0);
                    }
                    else if(P.getInventaire == 11){
                        if(E.getVie()>5)
                            E.setVie(E.getVie()-5);;
                        else
                            E.setVie(0); 
                    }
                    else{
                        if(E.getVie()>1)
                            E.setVie(E.getVie()-1);
                        else
                            E.setVie(0);         //le personnage est mort on met la case a vide
                    }
                    return;
                }
            }
            
            else if(A.get_Action() == FrapperE.getValeur()){ //Frapper Ã  l'est 
                Coordonnees v = new Coordonnees();
                v.setX(P.position.getX()+1);
                v.setY(P.position.getY());
                Personnage E=null;
                int nb = J.size();                      //nombre de joueurs
                int x=0, s, i;
                while(x<nb){                             //Tant que tous les joueurs n'ont pas ete check
                   s = J.get(x).getSizePersonnages();     //nombre de personnages que possede le joueur x
                   for(i=0; i<s; i++){
                       if(J.get(x).getPersonnagesI(i).position == v){
                           E = J.get(x).getPersonnagesI(i);
                           i = s;
                           x = nb;
                       }
                   }
                   x++;
                }
                if((!E.equals(null))&&(E.instanceof(Mechant))){
                    if(P.getInventaire == 10){
                        if(E.getVie()>3)
                            E.setVie(E.getVie()-3);
                        else
                            E.setVie(0);
                    }
                    else if(P.getInventaire() == 11){
                        if(E.getVie()>5)
                            E.setVie(E.getVie()-5);
                        else
                            E.setVie(0);
                    }
                    else{
                        if(E.getVie()>1)
                            E.setVie(E.getVie()-1);
                        else
                            E.setVie(0);         //le personnage est mort on met la case a vide
                    }
                    return;
                }
            }
            
            else if(A.get_Action() == FrapperO.getValeur()){ //Frapper Ã  l'ouest
                Coordonnees v = new Coordonnees();
                v.setX(P.position.getX()-1);
                v.setY(P.position.getY());
                Personnage E=null;
                int nb = J.size();                      //nombre de joueurs
                int x=0, s, i;	
                while(x<nb){                             //Tant que tous les joueurs n'ont pas ete check
                   s = J.get(x).getSizePersonnages();     //nombre de personnages que possede le joueur x
                   for(i=0; i<s; i++){
                       if(J.get(x).getPersonnagesI(i).position == v){
                           E = J.get(x).getPersonnagesI(i);
                           i = s;
                           x = nb;
                       }
                   }
                   x++;
                }
                if((!E.equals(null))&&(E.instanceof(Mechant))){
                    if(P.getInventaire)() == 10){
                        if(E.getVie()>3)
                            E.setVie(E.getVie()-3);
                        else
                            E.setVie(0);
                    }
                    else if(P.getInventaire() == 11){
                        if(E.getVie()>5)
                            E.setVie(E.getVie()-5);
                        else
                            E.setVie(0);
                    }
                    else{
                        if(E.getVie()>1)
                            E.setVie(E.getVie()-1);
                        else
                            E.setVie(0);         //le personnage est mort on met la case a vide
                    }
                    return;
                }
            }
            return;
        }
        else if(P.instanceof(Mechant)){
            if(A.get_Action() == 0)
                return;
                
            else if(A.get_Action() == Rien.getValeur())
                return;
                
            else if(A.get_Action() == AvancerN.getValeur()){
                if(N.estChemin())
                    P.deplacementHaut();
                return;
            }
            
            else if(A.get_Action() == AvancerS.getValeur()){
                if(S.estChemein())
                    P.deplacementBas();
                return;
            }
            
            else if(A.get_Action() == AvancerE.getValeur()){
                if(E.estChemin())
                    P.deplacementDroite();
                return;
            }
            
            else if(A.get_Action() == AvancerO.getValeur()){
                if(O.estChemin())
                    P.deplacementGauche();
                return;
            }
            
            else if(A.get_Action() == Ramasser.getValeur()){
                if(C.estRamassable()){
                    Description += "Ramasse " + Contenus.fromint(C.getValeur()).toString() + "et pose ";
                    int aPoser = P.ramasser(C.getValeur());
                    C.setValeur(aPoser);
                    Description += Contenus.fromint(aPoser).toString();
                }
                return;
            }
            
            else if(A.get_Action() == FrapperN.getValeur()){
                Coordonnees v = new Coordonnees();
                v.setX(P.position.getX);
                v.setY(P.position.getY-1);
                Personnage E = null;
                int nb = J.size();
                int x=0, s, i;
                while(x<nb){
                    s = J.get(x).getSizePersonnages();
                    for(i=0; i<s; i++){
                        if(J.get(x).getPersonnagesI(i).position == v){
                            E = J.get(x).getPersonnagesI(i);
                            i = s;
                            x = nb;
                        }
                    }
                    x++;
                }
                
                if(E != null && E.instanceof(Gentil)){
                    if(E.getVie() > 5)
                        E.setVie(E.getVie()-5);
                    else
                        E.setVie(0);
                }
                return;
            }
            
            else if(A.get_Action() == FrapperS.getValeur()){
                Coordonnees v = new Coordonnees();
                v.setX(P.position.getX);
                v.setY(P.position.getY+1);
                Personnage E = null;
                int nb = J.size();
                int x=0, s, i;
                while(x<nb){
                    s = J.get(x).getSizePersonnages();
                    for(i=0; i<s; i++){
                        if(J.get(x).getPersonnagesI(i).position == v){
                            E = J.get(x).getPersonnagesI(i);
                            i = s;
                            x = nb;
                        }
                    }
                    x++;
                }
                
                if(E != null && E.instanceof(Gentil)){
                    if(E.getVie() > 5)
                        E.setVie(E.getVie()-5);
                    else
                        E.setVie(0);
                }
                return;
            }
            
            else if(A.get_Action() == FrapperE.getValeur()){
                Coordonnees v = new Coordonnees();
                v.setX(P.position.getX+1);
                v.setY(P.position.getY);
                Personnage E = null;
                int nb = J.size();
                int x=0, s, i;
                while(x<nb){
                    s = J.get(x).getSizePersonnages();
                    for(i=0; i<s; i++){
                        if(J.get(x).getPersonnagesI(i).position == v){
                            E = J.get(x).getPersonnagesI(i);
                            i = s;
                            x = nb;
                        }
                    }
                    x++;
                }
                
                if(E != null && E.instanceof(Gentil)){
                    if(E.getVie() > 5)
                        E.setVie(E.getVie()-5);
                    else
                        E.setVie(0);
                }
                return;
            }
            
            else if(A.get_Action() == FrapperO.getValeur()){
                Coordonnees v = new Coordonnees();
                v.setX(P.position.getX-1);
                v.setY(P.position.getY);
                Personnage E = null;
                int nb = J.size();
                int x=0, s, i;
                while(x<nb){
                    s = J.get(x).getSizePersonnages();
                    for(i=0; i<s; i++){
                        if(J.get(x).getPersonnagesI(i).position == v){
                            E = J.get(x).getPersonnagesI(i);
                            i = s;
                            x = nb;
                        }
                    }
                    x++;
                }
                
                if(E != null && E.instanceof(Gentil)){
                    if(E.getVie() > 5)
                        E.setVie(E.getVie()-5);
                    else
                        E.setVie(0);
                }
                return;
            }
        }
    }
}