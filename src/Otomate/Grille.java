package Otomate;

import java.util.LinkedList;
import java.util.List;

public class Grille {
    
    //Attributs
    private Case[][] g;
    private List<Coordonnees> coinsAutomates;
    private List<Integer> nbetats;
    public int tailleX;
    public int tailleY;
    
    public void set(int val, int x, int y){
    	g[x][y].setValeur(val);
    }
    
    public Case get(int x,int y){
    	return g[x][y];
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
    
    //Méthodes
    
//Place les automates au bon endroit sur la map
    public void Placements(List<Joueur> J) {
        int l = coinsAutomates.size();
        List<Personnage> list = new LinkedList<>();
        int i,j,k,nbCond = J.get(0).getPersonnages().get(0).getAutotate().nbConditions();   //nbCond contient le nombre de condition (soit la "hauteur" de nos automates)
        for(i=0; i<l; i++){
        	for(j=0;j<J.get(i).getSizePersonnages();j++){
        	list.add(J.get(i).getPersonnagesI(j));    	
        	}
        	
            i += J.get(i).getSizePersonnages();
        }
        
        for(i=0;i<l;i++){
            for(j=i; j<l; j++){
                if(coinsAutomates.get(i).abs < coinsAutomates.get(j).abs && coinsAutomates.get(i).abs + list.get(i).position.getX() < coinsAutomates.get(j).abs){}
                else if(coinsAutomates.get(i).abs < coinsAutomates.get(j).abs && coinsAutomates.get(i).ord < coinsAutomates.get(j).ord && coinsAutomates.get(i).ord + nbCond < coinsAutomates.get(j).ord){}
                else if(coinsAutomates.get(i).abs < coinsAutomates.get(j).abs && coinsAutomates.get(i).ord > coinsAutomates.get(j).ord && coinsAutomates.get(i).ord > coinsAutomates.get(j).ord + nbCond){}
                else if(coinsAutomates.get(i).abs > coinsAutomates.get(j).abs && coinsAutomates.get(i).abs > coinsAutomates.get(j).abs + list.get(j).position.getX()){}
                else if(coinsAutomates.get(i).abs > coinsAutomates.get(j).abs && coinsAutomates.get(i).ord < coinsAutomates.get(j).ord && coinsAutomates.get(i).ord + nbCond < coinsAutomates.get(j).ord){}
                else if(coinsAutomates.get(i).abs > coinsAutomates.get(j).abs && coinsAutomates.get(i).ord > coinsAutomates.get(j).ord && coinsAutomates.get(i).ord > coinsAutomates.get(j).ord + nbCond){}
                else
                    System.out.println("Erreur les automates se superposent on a un probl�me pour la �n�ration de leur coordonn�es !");
            }
        }
        int jdeb, kdeb;
        int x=0, s, y; 
        while(x<J.size())
        {
            y=0;
            s = J.get(x).getPersonnages().size();
            while(y<s)
            {
                for(i=0;i<l;i++){               //nombre d'automates a placer
                    jdeb = coinsAutomates.get(i).abs;
                    for(j=jdeb; j<nbetats.get(i)+jdeb; i++){      //parcours de lignes
                        kdeb = coinsAutomates.get(i).ord;
                        for(k=kdeb;k<nbCond+kdeb;k++){          //parcours des colonnes
                            g[j][k] = J.get(x).getPersonnages().get(y).a.actions[j-jdeb][k-kdeb];
                        }
                    }
                }
                y++;
            }
            x++;
        }
    }

//Retourne la liste des conditions du personnage
//On retrouve en element numéro : 0:centre; 1:Nord; 2:Sud; 3:Ouest; 4:Est
    public List<Integer> Conditions(Personnage P){
        List<Integer> l = new LinkedList<>();
        l.add(Pos(P.position).getValeur());
        Personnage p2 = P;
        p2.position.ord=(P.position.ord-1);
        l.add(Pos(p2.position).getValeur());
        p2.position.ord = P.position.ord+1;
        l.add(Pos(p2.position).getValeur());
        p2.position.ord = P.position.ord;
        p2.position.abs = P.position.abs-1;
        l.add(Pos(p2.position).getValeur());
        p2.position.abs = P.position.abs+1;
        l.add(Pos(p2.position).getValeur());
        return l;
    }
    
//Retourne une liste d'actions possibles pour P
    public List<Case> ActionsPossibles(Personnage P){
        List<Integer> l = Conditions(P);
        List<Case> la = new LinkedList<Case>();
        if(P.a.transitions[P.etat][l.get(0)] != 0){         //si transition possible depuis l'�tat o� le personnage se trouve avec la condition de la case valide
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

//Retourne la case de coordonnées c
    public Case Pos(Coordonnees c){
        return g[c.abs][c.ord];
    }
    
//On rappelle que l'"origine" du repère de la grille est en haut à gauche donc un déplacement au nord = -1 en ord et +1 pour aller vers le sud cependant
//on garde +1 pour l'est en abs et -1 pour l'ouest
  
//Met à jour la map = change le numéro si besoin est
    public void Maj(Personnage P, Action A, List<Joueur> J){
        if(A.val == 0)                  //indifférent
            return;
            
        else if(A.val == 1)             //Vide
            return;
            
        else if(A.val == 2){            //Avancer au nord
            P.position.ord--;
            return;
        }
        
        else if(A.val == 3){             //Reculer
            P.position.ord++;
            return;
        }
        
        else if(A.val == 4){            //Partir à droite
            P.position.abs++;
            return;
        }
        
        else if(A.val == 5){            //Partir à gauche
            P.position.abs--;
            return;
        }
        
        else if(A.val == 6){                //Ramasser
            if(Pos(P.position).getValeur() > 9){
                if(P.arme == 0){                                //on rammasse l'arme et on vide la case
                    P.arme = Pos(P.position).getValeur();
                    Pos(P.position).setValeur(0);
                }
                else if(Pos(P.position).getValeur() > P.arme){        //on récupère l'arme qui est plus efficace et on remplace la case
                    int weapon = P.arme;
                    P.arme = Pos(P.position).getValeur();
                    Pos(P.position).setValeur(weapon);
                }
            }
            else if(Pos(P.position).getValeur() == 9)
                if(P.consommable == 0){
                    P.consommable = 9;
                    Pos(P.position).setValeur(0);
                }
            return;
        }
        
        else if(A.val == 7){ //Frapper au nord
            Coordonnees v = new Coordonnees();
            v.abs = P.position.abs;
            v.ord = P.position.ord-1;
            Personnage E=null;
            int nb = J.size();                      //nombre de joueurs
            int x=0, s, i;
            while(x<nb){                             //Tant que tous les joueurs n'ont pas été check
               s = J.get(x).getPersonnages().size();     //nombre de personnages que possède le joueur x
               for(i=0; i<s; i++){
                   if(J.get(x).getPersonnages().get(i).position == v){
                       E = J.get(x).getPersonnagesI(i);
                       i = s;
                       x = nb;
                   }
               }
               x++;
            }
            
            if(!E.equals(null)){
            if(P.arme == 0){
                if(E.vie>1)
                    E.vie--;
                else
                    Pos(P.position).setValeur(0);         //le personnage est mort on met la case à vide
            }
            else if(P.arme == 10){
                if(E.vie>3)
                    E.vie -= 3;
                else
                    Pos(P.position).setValeur(0);
            }
            else if(P.arme == 11){
                if(E.vie>5)
                    E.vie -= 5;
                else
                    Pos(P.position).setValeur(0); 
            }
            return;
        }
        }
        
        else if(A.val == 8){ //Frapper au sud
            Coordonnees v=new Coordonnees();
            v.abs = P.position.abs;
            v.ord = P.position.ord+1;
            Personnage E=null;
            int nb = J.size();                      //nombre de joueurs
            int x=0, s, i;
            while(x<nb){                             //Tant que tous les joueurs n'ont pas été check
               s = J.get(x).getSizePersonnages();     //nombre de personnages que possède le joueur x
               for(i=0; i<s; i++){
                   if(J.get(x).getPersonnagesI(i).position == v){
                       E = J.get(x).getPersonnagesI(i);
                       i = s;
                       x = nb;
                   }
               }
               x++;
            }
            if(!E.equals(null)){
            if(P.arme == 0){
                if(E.vie>1)
                    E.vie--;
                else
                    Pos(P.position).setValeur(0);         //le personnage est mort on met la case à vide
            }
            else if(P.arme == 10){
                if(E.vie>3)
                    E.vie -= 3;
                else
                    Pos(P.position).setValeur(0);
            }
            else if(P.arme == 11){
                if(E.vie>5)
                    E.vie -= 5;
                else
                    Pos(P.position).setValeur(0); 
            }
            return;
        }
        }
        
        else if(A.val == 9){ //Frapper à l'est 
            Coordonnees v = new Coordonnees();
            v.abs = P.position.abs+1;
            v.ord = P.position.ord;
            Personnage E=null;
            int nb = J.size();                      //nombre de joueurs
            int x=0, s, i;
            while(x<nb){                             //Tant que tous les joueurs n'ont pas été check
               s = J.get(x).getSizePersonnages();     //nombre de personnages que possède le joueur x
               for(i=0; i<s; i++){
                   if(J.get(x).getPersonnagesI(i).position == v){
                       E = J.get(x).getPersonnagesI(i);
                       i = s;
                       x = nb;
                   }
               }
               x++;
            }
            if(E!=null){
            if(P.arme == 0){
                if(E.vie>1)
                    E.vie--;
                else
                    Pos(P.position).setValeur(0);         //le personnage est mort on met la case à vide
            }
            else if(P.arme == 10){
                if(E.vie>3)
                    E.vie -= 3;
                else
                    Pos(P.position).setValeur(0);
            }
            else if(P.arme == 11){
                if(E.vie>5)
                    E.vie -= 5;
                else
                    Pos(P.position).setValeur(0); 
            }
            return;
        }
        }
        
        else if(A.val == 10){ //Frapper à l'ouest
            Coordonnees v = new Coordonnees();
            v.abs = P.position.abs-1;
            v.ord = P.position.ord;
            Personnage E=null;
            int nb = J.size();                      //nombre de joueurs
            int x=0, s, i;	
            while(x<nb){                             //Tant que tous les joueurs n'ont pas été check
               s = J.get(x).getSizePersonnages();     //nombre de personnages que possède le joueur x
               for(i=0; i<s; i++){
                   if(J.get(x).getPersonnagesI(i).position == v){
                       E = J.get(x).getPersonnagesI(i);
                       i = s;
                       x = nb;
                   }
               }
               x++;
            }
            if(E!=null){
            if(P.arme == 0){
                if(E.vie>1)
                    E.vie--;
                else
                    Pos(P.position).setValeur(0);         //le personnage est mort on met la case à vide
            }
            else if(P.arme == 10){
                if(E.vie>3)
                    E.vie -= 3;
                else
                    Pos(P.position).setValeur(0);
            }
            else if(P.arme == 11){
                if(E.vie>5)
                    E.vie -= 5;
                else
                    Pos(P.position).setValeur(0); 
            }
            return;
        }
        }
        return;
    }
}