package Otomate;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Grille {
    
	public static int random(int min, int max){
    	int k = (int) (min + (Math.random() * (max - min)));
    	System.out.println("RANDOMDEMERGE : "+k);
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
        nbetats = taille;
        int maxi = max(taille);
        int dimh = maxi * list.size();
        if(dimh<20)
            dimh = 20;
        maxi = l.get(0).getPersonnagesI(0).a.nbconditions();
        int dimv = maxi * list.size();
        if(dimv<20)
            dimv = 20;
            
      //cration de la map dimh/dimv avec minimum 150/150
        
        g = new Case[dimh][dimv];     
        for(i=0;i<dimh;i++){
        	for(j=0;j<dimv;j++){
        		g[i][j]=new Case();
        	}
        }
        
        for(i=0; i<dimh; i++){
            for(j=0; j<dimv; j++){
                k = random(1, 10);        //car 10 actions possibles numrotes de 1  10 
                g[i][j].element = k;
            }
        }
        
        coinsAutomates = goAutomates(list, dimh, dimv);
        Placements(l);
    }

//Retourne la liste des conditions du personnage
//On retrouve en element numéro : 0:centre; 1:Nord; 2:Sud; 3:Ouest; 4:Est
    public static List<Integer> Conditions($Personnage P){
        List<Integer> l = new LinkedList<>();
        int i, k=0;
        i=Pos(P.getPosition()).getValeur();		//recup du numero contenu
        if(i>=0 && i<=5){
        	if(i == 1 || i==2 || i==3){
        		l.add(9);
        	}
        	else
        		l.add(0);
        }
        else if(i<9){
        	System.out.println("ERRUUUUUUR");
        }
        else
        	l.add(10);
        $Personnage p2 = P;
        p2.getPosition().setY(P.getPosition().getY()-1);
        if(p2.position.getX()>0 && p2.position.getY()>0){
        i=(Pos(p2.getPosition()).getValeur());
        if(i>=0 && i<=5 || i>8){
        		l.add(1);
        }
        else
        	l.add(-1);
        }
        p2.getPosition().setY(P.getPosition().getY()+1);
        if(p2.position.getX()>0 && p2.position.getY()>0){
        i = (Pos(p2.getPosition()).getValeur());
        if(i>=0 && i<=5 || i>8){
    		l.add(2);
        }
        else
        	l.add(-1);
        }
        p2.getPosition().setX(P.getPosition().getX()-1);
        if(p2.position.getX()>0 && p2.position.getY()>0){
        i = (Pos(p2.getPosition()).getValeur());
        if(i>=0 && i<=5 || i>8){
    		l.add(4);
        }
        else
        	l.add(-1);
        }
        p2.getPosition().setX(P.getPosition().getX()+1);
        if(p2.position.getX()>0 && p2.position.getY()>0){
        i = (Pos(p2.getPosition()).getValeur());
        if(i>=0 && i<=5 || i>8){
    		l.add(3);
        }
        else
        	l.add(-1);
        }
        System.out.println("La liste l vaut :"+l.toString());
        return l;
        /*l.add(Pos(P.getPosition()).getValeur());
        $Personnage p2 = P;
        p2.getPosition().setY(P.getPosition().getY()-1);
        l.add(Pos(p2.getPosition()).getValeur());
        p2.getPosition().setY(P.getPosition().getY()+1);
        l.add(Pos(p2.getPosition()).getValeur());
        p2.getPosition().setY(P.getPosition().getY());
        p2.getPosition().setX(P.getPosition().getX()-1);
        l.add(Pos(p2.getPosition()).getValeur());
        p2.getPosition().setX(P.getPosition().getX()+1);
        l.add(Pos(p2.getPosition()).getValeur());
        System.out.println("La liste l vaut :"+l.toString());
        return l;*/
    }
    
//Retourne une liste d'actions possibles pour P
    public static List<Case> ActionsPossibles($Personnage P){
    	List<Integer> l = Conditions(P);
    	int s = l.size();
    	List<Case> la = new LinkedList<Case>();
    	if(s>0){
    		if(l.get(0)>0 && P.a.transition(P.etat, l.get(0)) != 0){         //si transition possible depuis l'etat o le personnage se trouve avec la condition de la case valide
    			la.add(P.a.getActions(P.etat, l.get(0)));
    			System.out.println("0 :On ajoute l'action :"+P.a.getActions(P.etat, l.get(0)).element);
    		}
    		if(s>1){
    			if(l.get(1)>0 && P.a.transition(P.etat, l.get(1)) != 0){         //regarde au nord
    				la.add(P.a.getActions(P.etat, l.get(1)));
    				System.out.println("1 :On ajoute l'action :"+P.a.getActions(P.etat, l.get(1)).element);
    			}
    			if(s>2){
    				if(l.get(2)>0 && P.a.transition(P.etat, l.get(2)) != 0){         //Sud
    					la.add(P.a.getActions(P.etat, l.get(2)));
    					System.out.println("2 :On ajoute l'action :"+P.a.getActions(P.etat, l.get(2)).element);
    				}
    				if(s>3){
    					if(l.get(3)>0 && P.a.transition(P.etat, l.get(3)) != 0){          //Ouest
    						la.add(P.a.getActions(P.etat, l.get(3)));
    						System.out.println("3 :On ajoute l'action :"+P.a.getActions(P.etat, l.get(3)).element);
    					}
    					if(s>4){
    						if(l.get(4)>0 && P.a.transition(P.etat, l.get(4)) != 0){         //EST
    							la.add(P.a.getActions(P.etat, l.get(4)));
    							System.out.println("4 :On ajoute l'action :"+P.a.getActions(P.etat, l.get(4)).element);
    						}
    					}
    				}
    			}
    		}
    	}
    	return la;
    }
    
    public static Action takeOne(List<Case> l){
        Action a = new Action();
        if(l.isEmpty() == true)
            return a;
        else{
        	System.out.println("Je peux faire : "+l.size()+" choses differentes.");
            int i = random(0, l.size()-1);
            System.out.println("Je vais faire l'action : "+l.get(i).element);
            a.set_Action(l.get(i).element);
            System.out.println("J'ai fait l'action : "+l.get(i).element);
            return a;
        }
    }

//Retourne la case de coordonnees c
    public static Case Pos(Coordonnees c){
        return g[c.getX()][c.getY()];
    }
    
    /**
     * Fonction permettant de frapper  un emplacement alatoire s'il y a des entits  proximit
     */
     public void Frapper($Personnage acteur) {
         
     }
    
    
//On rappelle que l'"origine" du repere de la grille est en haut  gauche donc un deplacement au nord = -1 en ord et +1 pour aller vers le sud cependant
//on garde +1 pour l'est en abs et -1 pour l'ouest
  
//Met a jour la map = change le numero si besoin est
    public void Maj($Personnage P, Action A, List<Joueur> J){
        
        Coordonnees position = P.getPosition();
        
        Case C = Pos(position);
        Case N = Pos(new Coordonnees(position.getX(), position.getY()-1));
        Case S = Pos(new Coordonnees(position.getX(), position.getY()+1));
        Case E = Pos(new Coordonnees(position.getX()+1, position.getY()));
        Case O = Pos(new Coordonnees(position.getX()-1, position.getY()));
        String Description = "";
        
        
        if(P instanceof Gentil){
            if(A.get_Action() == 0)                  //indifférent
                return;
                
            else if(A.get_Action() == Actions.Rien.getValeur()){  // Ne rien faire
                Description = "Ne fais rien.";
                System.out.println(Description);
                return;
            }
            else if(A.get_Action() == Actions.AvancerN.getValeur()){            //Avancer au nord
                if(N.estChemin()){
                    P.deplacementHaut();
                    Description = "Se deplace au Nord.";
                } else {
                    Description = "Tente de se deplacer au Nord mais echoue.";
                }
                System.out.println(Description);
                return;
            }
            
            else if(A.get_Action() == Actions.AvancerS.getValeur()){             //Reculer
                if(S.estChemin()) {
                    P.deplacementBas();
                    Description = "Se deplace au Sud.";
                } else {
                    Description = "Tente de se deplacer au Sud mais achoue.";
                }
                System.out.println(Description);
                return;
            }
            
            else if(A.get_Action() == Actions.AvancerE.getValeur()){            //Partir a droite
                if(E.estChemin()) {
                    P.deplacementDroite();
                    Description = "Se deplace a l'Est.";
                } else {
                    Description = "Tente de se deplacer a l'Est mais achoue.";
                }
                System.out.println(Description);
                return;
            }
            
            else if(A.get_Action() == Actions.AvancerO.getValeur()){            //Partir a gauche
                if(O.estChemin()) {
                    P.deplacementGauche();
                    Description = "Se deplace a l'Ouest.";
                } else {
                    Description = "Tente de se deplacer a l'Ouest mais achoue.";
                }
                System.out.println(Description);
                return;
            }
            
            else if(A.get_Action() == Actions.Ramasser.getValeur()){            //Ramasser  
                
                if(C.estRamassable()){
                    Description += "Ramasse " + Contenus.fromint(C.getValeur()).toString() + "et pose ";
                    int aPoser = P.ramasser(C.getValeur());
                    C.setValeur(aPoser);
                    Description += Contenus.fromint(aPoser).toString();
                }
                else {
                    Description = "Tente de ramasser " + Contenus.fromint(C.getValeur()).toString() + "mais echoue.";
                }
            }
            else if(A.get_Action() == Actions.FrapperN.getValeur()){ //Frapper au nord
                Coordonnees v = new Coordonnees();
                v.setX(P.getPosition().getX());
                v.setY(P.getPosition().getY()-1);
                $Personnage E1=null;
                int nb = J.size();                      //nombre de joueurs
                int x=0, s, i;
                while(x<nb){                             //Tant que tous les joueurs n'ont pas ete check
                   s = J.get(x).getSizePersonnages();     //nombre de personnages que possede le joueur x
                   for(i=0; i<s; i++){
                       if(J.get(x).getPersonnagesI(i).getPosition() == v){
                           E1 = J.get(x).getPersonnagesI(i);
                           i = s;
                           x = nb;
                       }
                   }
                   x++;
                }
                
                if((!E1.equals(null))&&(E1 instanceof Mechant)){
                    if(P.getInventaire() == 10){
                        if(E1.getVie()>3)
                            E1.setVie(E1.getVie()-3);
                        else
                            E1.setVie(0);
                    }
                    else if(P.getInventaire() == 11){
                        if(E1.getVie()>5)
                            E1.setVie(E1.getVie()-5);
                        else
                            E1.setVie(0); 
                    }
                    else{
                        if(E1.getVie()>1)
                            E1.setVie(E1.getVie()-1);
                        else
                            E1.setVie(0);         //le personnage est mort on met la case a vide
                    }
                    return;
                }
            }
            
            else if(A.get_Action() == Actions.FrapperS.getValeur()){ //Frapper au sud
                Coordonnees v=new Coordonnees();
                v.setX(P.getPosition().getX());
                v.setY(P.getPosition().getY()+1);
                $Personnage E1=null;
                int nb = J.size();                      //nombre de joueurs
                int x=0, s, i;
                while(x<nb){                             //Tant que tous les joueurs n'ont pas ete check
                   s = J.get(x).getSizePersonnages();     //nombre de personnages que possede le joueur x
                   for(i=0; i<s; i++){
                       if(J.get(x).getPersonnagesI(i).getPosition() == v){
                           E1 = J.get(x).getPersonnagesI(i);
                           i = s;
                           x = nb;
                       }
                   }
                   x++;
                }
                if((!E1.equals(null))&&(E1 instanceof Mechant)){
                    if(P.getInventaire() == 10){
                        if(E1.getVie()>3)
                            E1.setVie(E1.getVie()-3);
                        else
                            E1.setVie(0);
                    }
                    else if(P.getInventaire() == 11){
                        if(E1.getVie()>5)
                            E1.setVie(E1.getVie()-5);
                        else
                            E1.setVie(0); 
                    }
                    else{
                        if(E1.getVie()>1)
                            E1.setVie(E1.getVie()-1);
                        else
                            E1.setVie(0);         //le personnage est mort on met la case a vide
                    }
                    return;
                }
            }
            
            else if(A.get_Action() == Actions.FrapperE.getValeur()){ //Frapper à l'est 
                Coordonnees v = new Coordonnees();
                v.setX(P.getPosition().getX()+1);
                v.setY(P.getPosition().getY());
                $Personnage E1=null;
                int nb = J.size();                      //nombre de joueurs
                int x=0, s, i;
                while(x<nb){                             //Tant que tous les joueurs n'ont pas ete check
                   s = J.get(x).getSizePersonnages();     //nombre de personnages que possede le joueur x
                   for(i=0; i<s; i++){
                       if(J.get(x).getPersonnagesI(i).getPosition() == v){
                           E1 = J.get(x).getPersonnagesI(i);
                           i = s;
                           x = nb;
                       }
                   }
                   x++;
                }
                if((E1 instanceof Mechant)&&(!E1.equals(null))){
                    if(P.getInventaire() == 10){
                        if(E1.getVie()>3)
                            E1.setVie(E1.getVie()-3);
                        else
                            E1.setVie(0);
                    }
                    else if(P.getInventaire() == 11){
                        if(E1.getVie()>5)
                            E1.setVie(E1.getVie()-5);
                        else
                            E1.setVie(0);
                    }
                    else{
                        if(E1.getVie()>1)
                            E1.setVie(E1.getVie()-1);
                        else
                            E1.setVie(0);         //le personnage est mort on met la case a vide
                    }
                    return;
                }
            }
            
            else if(A.get_Action() == Actions.FrapperO.getValeur()){ //Frapper à l'ouest
                Coordonnees v = new Coordonnees();
                v.setX(P.getPosition().getX()-1);
                v.setY(P.getPosition().getY());
                $Personnage E1=null;
                int nb = J.size();                      //nombre de joueurs
                int x=0, s, i;	
                while(x<nb){                             //Tant que tous les joueurs n'ont pas ete check
                   s = J.get(x).getSizePersonnages();     //nombre de personnages que possede le joueur x
                   for(i=0; i<s; i++){
                       if(J.get(x).getPersonnagesI(i).getPosition() == v){
                           E1 = J.get(x).getPersonnagesI(i);
                           i = s;
                           x = nb;
                       }
                   }
                   x++;
                }
                if((!E1.equals(null))&&(E1 instanceof Mechant)){
                    if(P.getInventaire() == 10){
                        if(E1.getVie()>3)
                            E1.setVie(E1.getVie()-3);
                        else
                            E1.setVie(0);
                    }
                    else if(P.getInventaire() == 11){
                        if(E1.getVie()>5)
                            E1.setVie(E1.getVie()-5);
                        else
                            E1.setVie(0);
                    }
                    else{
                        if(E1.getVie()>1)
                            E1.setVie(E1.getVie()-1);
                        else
                            E1.setVie(0);         //le personnage est mort on met la case a vide
                    }
                    return;
                }
            }
            return;
        }
        else if(P instanceof Mechant){
            if(A.get_Action() == 0)
                return;
                
            else if(A.get_Action() == Actions.Rien.getValeur())
                return;
                
            else if(A.get_Action() == Actions.AvancerN.getValeur()){
                if(N.estChemin())
                    P.deplacementHaut();
                return;
            }
            
            else if(A.get_Action() == Actions.AvancerS.getValeur()){
                if(S.estChemin())
                    P.deplacementBas();
                return;
            }
            
            else if(A.get_Action() == Actions.AvancerE.getValeur()){
                if(E.estChemin())
                    P.deplacementDroite();
                return;
            }
            
            else if(A.get_Action() == Actions.AvancerO.getValeur()){
                if(O.estChemin())
                    P.deplacementGauche();
                return;
            }
            
            else if(A.get_Action() == Actions.Ramasser.getValeur()){
                if(C.estRamassable()){
                    Description += "Ramasse " + Contenus.fromint(C.getValeur()).toString() + "et pose ";
                    int aPoser = P.ramasser(C.getValeur());
                    C.setValeur(aPoser);
                    Description += Contenus.fromint(aPoser).toString();
                }
                return;
            }
            
            else if(A.get_Action() == Actions.FrapperN.getValeur()){
                Coordonnees v = new Coordonnees();
                v.setX(P.getPosition().getX());
                v.setY(P.getPosition().getY()-1);
                $Personnage E1 = null;
                int nb = J.size();
                int x=0, s, i;
                while(x<nb){
                    s = J.get(x).getSizePersonnages();
                    for(i=0; i<s; i++){
                        if(J.get(x).getPersonnagesI(i).getPosition() == v){
                            E1 = J.get(x).getPersonnagesI(i);
                            i = s;
                            x = nb;
                        }
                    }
                    x++;
                }
                
                if(E1 != null && E1 instanceof Gentil){
                    if(E1.getVie() > 5)
                        E1.setVie(E1.getVie()-5);
                    else
                        E1.setVie(0);
                }
                return;
            }
            
            else if(A.get_Action() == Actions.FrapperS.getValeur()){
                Coordonnees v = new Coordonnees();
                v.setX(P.getPosition().getX());
                v.setY(P.getPosition().getY()+1);
                $Personnage E1 = null;
                int nb = J.size();
                int x=0, s, i;
                while(x<nb){
                    s = J.get(x).getSizePersonnages();
                    for(i=0; i<s; i++){
                        if(J.get(x).getPersonnagesI(i).getPosition() == v){
                            E1 = J.get(x).getPersonnagesI(i);
                            i = s;
                            x = nb;
                        }
                    }
                    x++;
                }
                
                if(E1 != null && E1 instanceof Gentil){
                    if(E1.getVie() > 5)
                        E1.setVie(E1.getVie()-5);
                    else
                        E1.setVie(0);
                }
                return;
            }
            
            else if(A.get_Action() == Actions.FrapperE.getValeur()){
                Coordonnees v = new Coordonnees();
                v.setX(P.getPosition().getX()+1);
                v.setY(P.getPosition().getY());
                $Personnage E1 = null;
                int nb = J.size();
                int x=0, s, i;
                while(x<nb){
                    s = J.get(x).getSizePersonnages();
                    for(i=0; i<s; i++){
                        if(J.get(x).getPersonnagesI(i).getPosition() == v){
                            E1 = J.get(x).getPersonnagesI(i);
                            i = s;
                            x = nb;
                        }
                    }
                    x++;
                }
                
                if(E1 != null && E1 instanceof Gentil){
                    if(E1.getVie() > 5)
                        E1.setVie(E1.getVie()-5);
                    else
                        E1.setVie(0);
                }
                return;
            }
            
            else if(A.get_Action() == Actions.FrapperO.getValeur()){
                Coordonnees v = new Coordonnees();
                v.setX(P.getPosition().getX()-1);
                v.setY(P.getPosition().getY());
                $Personnage E1 = null;
                int nb = J.size();
                int x=0, s, i;
                while(x<nb){
                    s = J.get(x).getSizePersonnages();
                    for(i=0; i<s; i++){
                        if(J.get(x).getPersonnagesI(i).getPosition() == v){
                            E1 = J.get(x).getPersonnagesI(i);
                            i = s;
                            x = nb;
                        }
                    }
                    x++;
                }
                
                if(E1 != null && E1 instanceof Gentil){
                    if(E1.getVie() > 5)
                        E1.setVie(E1.getVie()-5);
                    else
                        E1.setVie(0);
                }
                return;
            }
        }
    }
}