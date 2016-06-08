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
    
    
    //fonctions de vrification d'obstruation
    public boolean estChemin(){
        return ( ( element >= Contenus.Terre.getValeur() && element <= Contenus.Sable.getValeur() ) || element == Contenus.Arc.getValeur() || element == Contenus.Sabre.getValeur() );
    }
    
    public boolean estRamassable(){
        return ( ( element >= Contenus.Pomme.getValeur() && element <= Contenus.Gateau.getValeur() ) || ( element == Contenus.Arc.getValeur() ) || ( element == Contenus.Sabre.getValeur() ) );
    }
}