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
    
    //fonctions de v�rification d'obstruation
    public boolean estChemin(){
        return ( ( element >= Terre.getValeur() && element <= Sable.getValeur() ) || element == Arc.getValeur() || element == Sabre.getValeur() );
    }
    
    public boolean estRamassable(){
        return ( ( element >= Pomme.getValeur() && element <= Gateau.getValeur() ) || ( element == Arc.getValeur() ) || ( element == Sabre.getValeur() ) );
    }
}