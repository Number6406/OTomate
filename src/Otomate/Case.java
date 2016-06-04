package Otomate;

public class Case {
    
    // Attributs
    public int element;
    
    //Constructeurs
    public Case(){
        element = 0;
    }
    
    public Case(int valeur){
        element = valeur;
    }
    
    //Methodes
    public int getValeur(){
        return element;
    }
    

    public void setValeur(int valeur){
        element = valeur;
    }
}