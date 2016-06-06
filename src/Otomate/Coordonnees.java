package Otomate;

public class Coordonnees{
    
    //Attributs
    protected int abs;
    protected int ord;
    
    // Constructeurs
    public Coordonnees(int x,int y){
        this.abs = x;
        this.ord = y;
    }
    
    public Coordonnees(Coordonnees C){
        this.abs = C.getX();
        this.ord = C.getY();
    }
    
    public Coordonnees(){
    	
    }
    
    
    public int getX(){
        return abs;
    }
    
    public int getY(){
        return ord;
    }
    
    public void setX(int x){
        abs = x;
    }
    
    public void setY(int y){
        ord = y;
    }
    
    /**
     * Fonctions pour incrémenter ou décrémenter une des deux coordonnées
     */
    public void incrX(){
        this.abs++;
    }
    
    public void decrX(){
        this.abs--;
    }
    
    public void incrY(){
        this.ord++;
    }
    
    public void decrY(){
        this.ord--;
    }
    
}