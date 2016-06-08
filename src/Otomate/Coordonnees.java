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
    
    /*
     * 1 : NORTH
     * 2 : EST
     * 3 : SUD
     * 4 : OUEST
    */
    public Coordonnees CalculCase(int d){
    	Coordonnees k = new Coordonnees();
    	k.setX(this.getX());
    	k.setY(this.getY());
    	switch(d){
    		case 1:
    			k.setY(this.getY()-1);
    		break;
    		
    		case 2:
    			k.setX(this.getX()+1);
    		break;
    		
    		case 3:
    			k.setY(this.getY()+1);
    		break;
    		
    		case 4:
    			k.setX(this.getX()-1);
    		break;
    	
    	}
    return k;
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
     * Fonctions pour incr�menter ou d�cr�menter une des deux coordonn�es
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
    
    public String toString(){
    	return "(" + abs + "," + ord + ")";
    }
    
}