package Otomate;

import Parser.ParserAutomate;

public class Automate {
    
    //Attributs
    protected int[][] transitions;
    protected Case[][] actions;
    protected int nbconditions;
    protected int nbetats;
    protected int etat_init;
    
    //Constructeur
    public Automate(String filepath) {
        ParserAutomate P = new ParserAutomate(filepath);
        actions = P.act;
        transitions = P.auto;
        nbetats = P.nb_etat;
        etat_init = P.etat_init;
        nbconditions = 20;
    }
    
    public Automate() {
    	
    }
    
    public void newTrans() {
    	transitions = new int[nbetats][nbconditions];
    }
    
    public void newAction() {
    	actions = new Case[nbetats][nbconditions];
    }
    
    // Getteurs
    public int transition(int symbole, int etatCourant) {  // renvoie le nouvel etat
        return transitions[etatCourant][symbole];
    }
    
    public void setTransition(int symbole, int etat, int k) {
    	transitions[etat][symbole]=k;
    }
    
    public Case getActions(int symbole, int etatCourant){
    	return actions[etatCourant][symbole];
    }
    
    public int action(int symbole, int etatCourant){
        return actions[etatCourant][symbole].element;
    
    }
    
    public int nbconditions(){
    	return nbconditions;
    }

	public int nbetats() {
		return nbetats;
	}

	public int etat_initial() {
		return etat_init;
	}

    // Setteurs
	public void setNbCond(Integer integer) {
		nbconditions = integer;
	}
	
	public void setNbEtats(Integer integer) {
		nbetats = integer;
	}

	public void setConditions(int nb) {
		this.nbconditions = nb;		
	}
	
	public String toString(){
		String s = "";
		int l = this.nbetats();
    	int k = this.nbconditions();
    	int i,j;
		s += "Tableau des actions :\n";
    	for(i=0; i<k; i++){
    		s += "|";
    		for(j=0;j<l;j++){
    			s+= this.action(i,j) +"|";
    		}
    		s += "\n";
    	}
		s += "Tableau des transitions :\n";
    	for(i=0; i<k; i++){
    		s += "|";
    		for(j=0;j<l;j++){
    			s+= this.transition(i,j) +"|";
    		}
    		s += "\n";
    	}
    	return s;
	}

	public void setAction(int i, int j, Case c) {
		actions[i][j] = c;
	}
    
}