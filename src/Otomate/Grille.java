package Otomate;

public class Grille {
    
    //Attributs
    public Case[][] g;
    public int tailleX,tailleY;
    
    public Grille(){
    	int i,j;    
    	this.tailleX=16;
    	this.tailleY=16;
    	g = new Case[16][16];
    	for(i=0;i<tailleX;i++){
    		for(j=0;j<tailleY;j++){
    			g[i][j]=new Case();
    		}
    	}
    } 	
    
    public void set(int val,int i,int j){
    g[i][j].setValeur(val);
    }
    
}