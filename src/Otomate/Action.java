package Otomate;

public class Action{
    
    //Attributs
    protected int val;         //valeur de l'action
    
    //Constructeurs
    public Action(){
        val = 0;
    }
    
    public Action(int x){
        val = x;
    }
    
    public void set_Action(int x){
        val = x;
    }
    
    public int get_Action(){
        return val;
    }
    
}