package Otomate;

public class Grille {
    
    //Attributs
    public Case[][] g;
    public int tailleX,tailleY;
    
    public Grille(){
    	int i,j;    
    	this.tailleX=4;
    	this.tailleY=4;
    	g = new Case[4][4];
    	for(i=0;i<4;i++){
    		for(j=0;j<4;j++){
    		    //System.out.println("COUCOU FDP");
    			g[i][j]=new Case();
    		}
    	}
    } 	
    
    public void set(int val,int i,int j){
   // System.out.println("COUCOU FDP");
    g[i][j].setValeur(val);
    }
    
}