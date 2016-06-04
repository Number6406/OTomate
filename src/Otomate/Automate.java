package Otomate;

public class Automate {
    
    //Attributs
    protected int[][] transitions;
    protected Case[][] actions;
    protected int nbconditions;
    
    //Constructeur
    public Automate(String filepath) {
        
    }
    
    public int transition(int etatCourant, int symbole) {  // renvoie le nouvel état + l'action #CommentOnFait --> nouveau type duet ?
        return transitions[symbole][etatCourant];
    }
    
}