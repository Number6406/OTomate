package Otomate;

public class Actions {
    
    //Attributs
    protected Case[][] listeActions;
    
    //Méthodes
    
//Retourne l'action correspondant au symbole lu et à l'état du personnage
    public int action(int symbole, int etat) {
        return listeActions[symbole][etat].element;
    }
    
}