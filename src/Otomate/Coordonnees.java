package Otomate;

public class Coordonnees{
    
    //Attributs
    protected int abs;
    protected int ord;
    
    public int getX(){
    	return abs;
    }
    
    public int getY(){
    	return ord;
    }
    
    public void setX(int x){
    	abs=x;
    }
    
    public void setY(int y){
    	ord=y;
    }
}