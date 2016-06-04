package Otomate;

import java.util.LinkedList;
import java.util.List;

public class Grille {
    
    //Attributs
    private Case[][] g;
    private List<Coordonnees> coinsAutomates;
    private List<Integer> nbetats;
    
    //Méthodes
    
//Place les automates au bon endroit sur la map 
//***/!\ /!\ /!\ /!\ /!\ Vérifier que les automates ne se supperposent pas au cas où !!! A FAIRE
    public void Placements(List<Joueur> J) {
        int l = coinsAutomates.size();
        int i,j,k,nbCond = J.get(0).getPersonnages().get(0).getAutotate().nbConditions();   //nbCond contient le nombre de condition (soit la "hauteur" de nos automates)
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
        l.add(g.Pos(P.position).getValeur());
        Personnage p2 = P;
        p2.ord = P.ord-1;
        l.add(g.Pos(p2.position).getValeur());
        p2.ord = P.ord+1;
        l.add(g.Pos(p2.position).getValeur());
        p2.ord = P.ord;
        p2.abs = P.abs-1;
        l.add(g.Pos(p2.position).getValeur());
        p2.abs = P.abs+1;
        l.add(g.Pos(p2.position).getValeur());
        return l;
    }
    
//Retourne une liste d'actions possibles pour P
    public List<Action> ActionsPossibles(Personnage P){
        List<Integer> l = Conditions(P);
        List<Action> la = new LinkedList<>();
        //if() ??
        
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
            if(g.Pos(P.position).getValeur() > 9){
                if(P.arme == 0){                                //on rammasse l'arme et on vide la case
                    P.arme = g.Pos(P.position).getValeur();
                    g.Pos(P.position).setValeur(0);
                }
                else if(G.Pos(P.position).getValeur() > P.arme){        //on récupère l'arme qui est plus efficace et on remplace la case
                    int weapon = P.arme;
                    P.arme = G.Pos(P.position).getValeur();
                    G.Pos(P.position).setValeur(weapon);
                }
            }
            else if(G.Pos(P.position).getValeur() == 9)
                if(P.consommable == 0){
                    P.consommable = 9;
                    G.Pos(P.position).setValeur(0);
                }
            return;
        }
        
        else if(A.val == 7){ //Frapper au nord
            Coordonnees v;
            v.abs = P.position.abs;
            v.ord = P.position.ord-1;
            Personnage E;
            int nb = J.size();                      //nombre de joueurs
            int x=0, s, i;
            while(x<l){                             //Tant que tous les joueurs n'ont pas été check
               s = J.get(x).personnages.size();     //nombre de personnages que possède le joueur x
               for(i=0; i<s; i++){
                   if(J.get(x).personnages.get(i).position == v){
                       E = J.get(x).personnages.get(i);
                       i = s;
                       x = l;
                   }
               }
               x++;
            }
            if(P.arme == 0){
                if(E.vie>1)
                    E.vie--;
                else
                    g.Pos(P.position).setValeur(0);         //le personnage est mort on met la case à vide
            }
            else if(P.arme == 10){
                if(E.vie>3)
                    E.vie -= 3;
                else
                    g.Pos(P.position).setValeur(0);
            }
            else if(P.arme == 11){
                if(E.vie>5)
                    E.vie -= 5;
                else
                    g.Pos(P.position).setValeur(0); 
            }
            return;
        }
        
        else if(A.val == 8){ //Frapper au sud
            Coordonnees v;
            v.abs = P.position.abs;
            v.ord = P.position.ord+1;
            Personnage E;
            int nb = J.size();                      //nombre de joueurs
            int x=0, s, i;
            while(x<l){                             //Tant que tous les joueurs n'ont pas été check
               s = J.get(x).personnages.size();     //nombre de personnages que possède le joueur x
               for(i=0; i<s; i++){
                   if(J.get(x).personnages.get(i).position == v){
                       E = J.get(x).personnages.get(i);
                       i = s;
                       x = l;
                   }
               }
               x++;
            }
            if(P.arme == 0){
                if(E.vie>1)
                    E.vie--;
                else
                    g.Pos(P.position).setValeur(0);         //le personnage est mort on met la case à vide
            }
            else if(P.arme == 10){
                if(E.vie>3)
                    E.vie -= 3;
                else
                    g.Pos(P.position).setValeur(0);
            }
            else if(P.arme == 11){
                if(E.vie>5)
                    E.vie -= 5;
                else
                    g.Pos(P.position).setValeur(0); 
            }
            return;
        }
        
        else if(A.val == 9){ //Frapper à l'est 
            Coordonnees v;
            v.abs = P.position.abs+1;
            v.ord = P.position.ord;
            Personnage E;
            int nb = J.size();                      //nombre de joueurs
            int x=0, s, i;
            while(x<l){                             //Tant que tous les joueurs n'ont pas été check
               s = J.get(x).personnages.size();     //nombre de personnages que possède le joueur x
               for(i=0; i<s; i++){
                   if(J.get(x).personnages.get(i).position == v){
                       E = J.get(x).personnages.get(i);
                       i = s;
                       x = l;
                   }
               }
               x++;
            }
            if(P.arme == 0){
                if(E.vie>1)
                    E.vie--;
                else
                    g.Pos(P.position).setValeur(0);         //le personnage est mort on met la case à vide
            }
            else if(P.arme == 10){
                if(E.vie>3)
                    E.vie -= 3;
                else
                    g.Pos(P.position).setValeur(0);
            }
            else if(P.arme == 11){
                if(E.vie>5)
                    E.vie -= 5;
                else
                    g.Pos(P.position).setValeur(0); 
            }
            return;
        }
        
        else if(A.val == 10){ //Frapper à l'ouest
            Coordonnees v;
            v.abs = P.position.abs-1;
            v.ord = P.position.ord;
            Personnage E;
            int nb = J.size();                      //nombre de joueurs
            int x=0, s, i;
            while(x<l){                             //Tant que tous les joueurs n'ont pas été check
               s = J.get(x).personnages.size();     //nombre de personnages que possède le joueur x
               for(i=0; i<s; i++){
                   if(J.get(x).personnages.get(i).position == v){
                       E = J.get(x).personnages.get(i);
                       i = s;
                       x = l;
                   }
               }
               x++;
            }
            if(P.arme == 0){
                if(E.vie>1)
                    E.vie--;
                else
                    g.Pos(P.position).setValeur(0);         //le personnage est mort on met la case à vide
            }
            else if(P.arme == 10){
                if(E.vie>3)
                    E.vie -= 3;
                else
                    g.Pos(P.position).setValeur(0);
            }
            else if(P.arme == 11){
                if(E.vie>5)
                    E.vie -= 5;
                else
                    g.Pos(P.position).setValeur(0); 
            }
            return;
        }
        
        return;
    }
}