package Otomate;

public class Automate {
    
    //Attributs
    protected int[][] transitions;
    protected Case[][] actions;
    protected int nbconditions;
    protected int nbetat;
    
    //Constructeur
    public Automate(String filepath) {
        
    }
    
    public int transition(int etatCourant, int symbole) {  // renvoie le nouvel état + l'action #CommentOnFait --> nouveau type duet ?
        return transitions[symbole][etatCourant];
    }
    
    public int nbConditions() {
        return this.nbconditions;
    }
    
    public int nbEtat(){
        return nbetat;
    }
    
}