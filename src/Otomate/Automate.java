package Otomate;

public class Automate {
    
    //Attributs
    protected int[][] transitions;
    protected Case[][] actions;
    protected int nbconditions;
    
    //Constructeur
    public Automate(String filepath) {
        
    }
    
    public int transition(int etatCourant, int symbole) {  // renvoie le nouvel �tat + l'action #CommentOnFait --> nouveau type duet ?
        return transitions[symbole][etatCourant];
    }
    
    public int action(int etatCourant, int symbole){
        return actions[symbole][etatCourant].element;
    
    }
    
}