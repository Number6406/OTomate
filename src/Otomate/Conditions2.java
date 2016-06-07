package Otomate;

public class Conditions2 {
    
    //Objets directement construits
    private int id;
    private int value;
    private int type;
    
    //Constructeur
    
    public Conditions2(int id,int value,int type){
    	this.id=id;
    	this.value=value;
    	this.type=type;
    }
    
    public int getValue(){
    	return value;
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
    	value=val;
    }
    
    public int getValeur(){
        return value;    
    }
    
    
}