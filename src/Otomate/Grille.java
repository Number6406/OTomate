package Otomate;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import Actions.*;

public class Grille {
    
	public static int random(int min, int max){
    	Random r = new Random();
    	int k = min + r.nextInt(max - min);

    	return k;
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
        List<$Personnage> list = new LinkedList<>();
        int i,j,k,nbCond = J.get(0).getPersonnagesI(0).getAutomate().nbconditions();   //nbCond contient le nombre de condition (soit la "hauteur" de nos automates)
        for(i=0; i<l; i++){
        	for(j=0;j<J.get(i).getSizePersonnages();j++){
        	    list.add(J.get(i).getPersonnagesI(j));    	
        	}
        	
            i += J.get(i).getSizePersonnages();
        }
        
        for(i=0;i<l;i++){
            for(j=i+1; j<l; j++){
                if(coinsAutomates.get(i).abs < coinsAutomates.get(j).abs && coinsAutomates.get(i).abs + list.get(i).getPosition().getX() < coinsAutomates.get(j).abs){}
                else if(coinsAutomates.get(i).getX() < coinsAutomates.get(j).getX() && coinsAutomates.get(i).getY() < coinsAutomates.get(j).getY() && coinsAutomates.get(i).getY() + nbCond < coinsAutomates.get(j).getY()){}
                else if(coinsAutomates.get(i).getX() < coinsAutomates.get(j).getX() && coinsAutomates.get(i).getY() > coinsAutomates.get(j).getY() && coinsAutomates.get(i).getY() > coinsAutomates.get(j).getY() + nbCond){}
                else if(coinsAutomates.get(i).getX() > coinsAutomates.get(j).getX() && coinsAutomates.get(i).getX() > coinsAutomates.get(j).getX() + list.get(j).getPosition().getX()){}
                else if(coinsAutomates.get(i).getX() > coinsAutomates.get(j).getX() && coinsAutomates.get(i).getY() < coinsAutomates.get(j).getY() && coinsAutomates.get(i).getY() + nbCond < coinsAutomates.get(j).getY()){}
                else if(coinsAutomates.get(i).getX() > coinsAutomates.get(j).getX() && coinsAutomates.get(i).getY() > coinsAutomates.get(j).getY() && coinsAutomates.get(i).getY() > coinsAutomates.get(j).getY() + nbCond){}
                else
                    System.out.println("Erreur les automates se superposent on a un probleme pour la generation de leur coordonnees : erreur dans goAutomates !");
            }
        }
        int jdeb, kdeb;
        int x=0, s, y;
        i=0;
        while(x<J.size())
        {
            y=0;
            s = J.get(x).getSizePersonnages();
            while(y<s && i<l)
            {
                //for(i=0;i<l;i++){               //nombre d'automates a placer
                    jdeb = coinsAutomates.get(i).getX();
                    for(j=jdeb; j<J.get(x).getPersonnagesI(y).nbEtat()/*nbetats.get(i)+jdeb*/; j++){      //parcours de lignes
                        kdeb = coinsAutomates.get(i).getY();
                        for(k=kdeb;k<nbCond+kdeb;k++){          //parcours des colonnes
                            g[j][k] = J.get(x).getPersonnagesI(y).a.getActions(k-kdeb, j-jdeb);
                        }
                    }
                //}
                y++;
                i++;
            }
            x++;
        }
    }
    
    public static List<Coordonnees> goAutomates(List<$Personnage> list, int dimh, int dimv){
        List<Coordonnees> res = new LinkedList<Coordonnees>();
        Random rnd = new Random();
        Coordonnees newc = new Coordonnees();
        int nb = list.size();
        int i, j, k;
        for(k=0; k<list.size(); k++){
            i = rnd.nextInt(nb);       //donne le numro de la case "h"
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
            if(j == k){             //c'est pour verifier qu'on est pas tomba dans le if et que c'est bon la case est dispo
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
        List<$Personnage> list = new LinkedList<>();
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
                    taille.add(l.get(i).getPersonnagesI(k).nbEtat());
                }
            }
        }
        setNbetats(taille);
        int maxi = max(taille);
        int dimh = maxi * list.size();
        if(dimh<20)
            dimh = 20;
        maxi = l.get(0).getPersonnagesI(0).a.nbconditions();
        int dimv = maxi * list.size();
        if(dimv<20)
            dimv = 20;
            
      //cration die la map dimh/dimv avec minimum 150/150
        
        g = new Case[dimh][dimv];
        for(i=0;i<dimh;i++){
        	for(j=0;j<dimv;j++){
        		g[i][j]=new Case();
        	}
        }
        
        for(i=0; i<dimh; i++){
            for(j=0; j<dimv; j++){
                k = random(0, 16);        //car 10 actions possibles numrotes de 1  10 
                g[i][j].element = k;
            }
        }
        
        coinsAutomates = goAutomates(list, dimh, dimv);
        Placements(l);
    }
    
//    public static Action takeOne(List<Case> l){
//        Action a = new Action();
//        if(l.isEmpty() == true)
//            return a;
//        else{
//        	System.out.println("Je peux faire : "+l.size()+" choses differentes.");
//            int i = random(0, l.size());
//            System.out.println("Je vais faire l'action : "+l.get(i).element);
//            a.set_Action(l.get(i).element);
//            System.out.println("J'ai fait l'action : "+l.get(i).element);
//            return a;
//        }
//    }

//Retourne la case de coordonnees c
    public static Case Pos(Coordonnees c){
        return g[c.getX()][c.getY()];
    }
    
    
//On rappelle que l'"origine" du repere de la grille est en haut  gauche donc un deplacement au nord = -1 en ord et +1 pour aller vers le sud cependant
//on garde +1 pour l'est en abs et -1 pour l'ouest
  
//Met a jour la map = change le numero si besoin est
    public void Maj($Personnage P, $Action A, List<Joueur> J, List<Integer> l){
    	int i,j;
    	List<$Personnage> list = new LinkedList<>();
    	int longperso = J.size();
    	for(i=0; i<longperso; i++){
        	for(j=0;j<J.get(i).getSizePersonnages();j++){
        	    list.add(J.get(i).getPersonnagesI(j));    	
        	}
        	
            i += J.get(i).getSizePersonnages();
        }
    	A.todo(l,P,list, this);
    }
    
	public static List<Integer> getNbetats() {
		return nbetats;
	}

	public static void setNbetats(List<Integer> nbetats) {
		Grille.nbetats = nbetats;
	}
}