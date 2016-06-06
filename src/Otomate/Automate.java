package Otomate;

import Parser.Parser;

public class Automate {
    
    //Attributs
    protected int[][] transitions;
    protected Case[][] actions;
    protected int nbconditions;
    protected int nbetats;
    protected int etat_init;
    
    //Constructeur
    public Automate(String filepath) {
        Parser P = new Parser(filepath);
        actions = P.act;
        transitions = P.auto;
        nbetats = P.nb_etat;
        etat_init = P.etat_init;
        nbconditions = 12;
    }
    
    public int transition(int etatCourant, int symbole) {  // renvoie le nouvel �tat + l'action #CommentOnFait --> nouveau type duet ?
        return transitions[symbole][etatCourant];
    }
    
    public int action(int etatCourant, int symbole){
        return actions[symbole][etatCourant].element;
    
    }
    
    public int nbconditions(){
    	return nbconditions;
    }

	public void setConditions(int i) {
		this.nbconditions = i;		
	}

	public int nbetats() {
		return nbetats;
	}
    
}