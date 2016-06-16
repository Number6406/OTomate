package Otomate;

import java.util.List;

public class Conditions {
    
    //Objets directement construits
    private int id;
    private int direction;
    private int type;
  //Liste conditions par case 
    /*CaseVide ("Case vide", 0),  
      CheminN ("Chemin au Nord", 1),  
      CheminS ("Chemin au Sud", 2),  
      CheminE ("Chemin au Est", 3),  
      CheminO ("Chemin au Ouest", 4),  
      EnnemiN ("Ennemi au Nord", 5),  
      EnnemiS ("Ennemi au Sud", 6),  
      EnnemiE ("Ennemi au Est", 7),  
      EnnemiO ("Ennemi au Ouest", 8),  
      Comestible ("Comestible sur ma Case", 9),  
      Arme ("un arme sur la case", 10),  //pompe ou couteau ou branche  
      ObstacleN ("Obstacle au nord", 11),  
	  ObstacleS ("Obstacle au sud", 12),  
	  ObstacleE ("Obstacle a l'est", 13),
	  ObstacleO ("Obstacle a l'ouest", 14),
	  Fouillable ("est fouillable", 15),  
	  Seringue ("la drogue c'est bon", 16),  
	  Destructible ("Objet destructible sur ma case", 17);
    */  
    /*
     *      { 0 : SP
    		{ 1 : N
	   d �{ 2 : E
    		{ 3 : S
    		{ 4 : O

	i < condMax

       		{ 0 : mur
       		{ 1 : objet
	type �{ 2 : bloque
       		{ 3 : ennemi
       		{ 4 : chemin
 
     */
 
    //Constructeur
    
    public boolean estVrai(Grille g,Coordonnees pos,List<Objet> l,$Personnage jo,List<Joueur> lj){
    	//System.out.println("Le type est a : "+ type);
    	if(pos.getX()==g.tailleX()-1 && direction==2) return type==0||type==2;
    	if(pos.getY()==g.tailleY()-1 && direction==3) return type==0||type==2;
    	
    	Coordonnees next=pos.CalculCase(direction);
    	
    	int i,j,max=l.size();
    	if(next.getX()>g.tailleX()-1 && direction == 2){return type==0||type==2;}
    	if(next.getX()<0 && direction == 4){return type==0||type==2;}
    	if(next.getY()>g.tailleY()-1 && direction == 3){return type==0||type==2;}
    	if(next.getY()<0 && direction == 1){return type==0 ||type==2;}
    	//System.out.println("direction="+direction);
    	//System.out.println("next.getX()="+next.getX());
    	//System.out.println("next.getY()="+next.getY());
    	int Cid=g.get(next.getX(), next.getY()).getValeur();
    	//System.out.println("direction : "+direction);
    	//System.out.println("contenu : "+ Cid);
    	//System.out.println(max);
    	for(i=0;i<max;i++){
    		//System.out.println("id = "+l.get(i).id +"\nCid="+Cid);
    		if(l.get(i).getId()==Cid){
    			//System.out.println("tamer");
    			switch(type){
    			
    			case 0:
    				if(l.get(i).getType()==2 && !(l.get(i).estPassable())) return true;
    				else return false;
    			
    			case 1:
    				if(l.get(i).getType()==1) return true;
    				else return false;
    			
    			case 2:
    				if(!(l.get(i).estPassable())) return true;
    				return false;
    			
    			case 3:
    				for(i=0;i<lj.size();i++){
    					List<$Personnage> lp=lj.get(i).getPersonnages();
    					for(j=0;j<lp.size();j++){
    						if(lp.get(j).getPosition().getX()==next.getX() && lp.get(j).getPosition().getY()==next.getY()){
    						if(jo instanceof Gentil){
    							if(lp.get(j) instanceof Mechant) return true;
    						}
    						else{
    							if(lp.get(j) instanceof Gentil) return true;
    						}
    						}
    					}
    					
    				}
    				return false;
    			
    			case 4:
    				if(l.get(i).estPassable() && !(new Conditions(20,direction,3).estVrai(g, pos, l, jo, lj)) ) return true;
    				else return false;
        			
        		case 5:
        			if(l.get(i).getType() == 1) return true;
        			else return false;
        		
        		case 6:
        			if(l.get(i).getType() == 3) return true;
        			else return false;
        			
        		case 7:
        			if(l.get(i).getType() == 6) return true;
        			else return false;
        			
        		case 8:
        			if(l.get(i).getType() == 7) return true;
        			else return false;
    			}
    		}
    	}
    	//System.out.println("coucou");
    	return false;
    }
    
    public Conditions(int id,int direction,int type){
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