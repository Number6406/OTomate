package Otomate;

import java.util.List;

public class Conditions2 {
    
    //Objets directement construits
    private int id;
    private int direction;
    private int type;
    /*
     *      { 0 : SP
    		{ 1 : N
	   d €  { 2 : E
    		{ 3 : S
    		{ 4 : O

	i < condMax

       		{ 0 : mur
       		{ 1 : objet
	type € 	{ 2 : bloque
       		{ 3 : ennemi
       		{ 4 : chemin
 
     */
 
    //Constructeur
    
    public boolean estVrai(Grille g,Coordonnees pos,List<Objet> l,List<Joueur> lj){
    	Coordonnees next=pos.CalculCase(direction);
    	int i,max=l.size();
    	int Cid=g.get(next.getX(), next.getY()).getValeur();
    	
    	for(i=0;i<max;i++){
    		if(l.get(i).id==Cid){
    			switch(type){
    			
    			case 0:
    				if(l.get(i).type==5 && l.get(i).passable==0) return true;
    				else return false;
    			
    			case 1:
    				if(l.get(i).type==1 || l.get(i).type==2 || l.get(i).type==3) return true;
    				else return false;
    			
    			case 2:
    				if(l.get(i).passable==0) return true;
    				return false;
    			
    			case 3:
    				// A FAIRE
    				return false;
    				//if(l.get(i).type==5) return true;
    			
    			case 4:
    				if(l.get(i).passable==1) return true;
    				else return false;
    			}
    		}
    	}
    	return false;
    }
    
    public Conditions2(int id,int direction,int type){
    	this.id=id;
    	this.direction=direction;
    	this.type=type;
    }
    
    public int getValue(){
    	return direction;
    }
    
    public int getType(){
    	return type;
    }
    
    public int getId(){
    	return id;
    }
    
    public void setType(int i){
    	type=i;
    }
    
    public void setId(int i){
    	id=i;
    }
    
    public void setValeur(int val){
    	direction=val;
    }
    
    public int getValeur(){
        return direction;    
    }
    
    
}