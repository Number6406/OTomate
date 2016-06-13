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
        nbconditions = 12;
        System.out.println("Actions :");
        int i,j;
        for(i=0; i<nbetats; i++){
        	for(j=0; j<nbconditions; j++){
        		System.out.print(actions[i][j].element+"  ");
        	}
        	System.out.print("\n");
        }
        System.out.println("Transitions :");
        for(i=0; i<nbetats; i++){
        	for(j=0; j<nbconditions; j++){
        		System.out.print(transitions[i][j]+"  ");
        	}
        	System.out.print("\n");
        }

    }
    
    public int transition(int symbole, int etatCourant) {  // renvoie le nouvel tat + l'action #CommentOnFait --> nouveau type duet ?
        return transitions[symbole][etatCourant];
    }
    
    public Case getActions(int symbole, int etatCourant){
    	//System.out.println("Je recupere l'action :"+actions[symbole][etatCourant].element);
    	return actions[symbole][etatCourant];
    }
    
    public int action(int symbole, int etatCourant){
        return actions[symbole][etatCourant].element;
    
    }
    
    public int nbconditions(){
    	return nbconditions;
    }

	public void setConditions(int nb) {
		this.nbconditions = nb;		
	}

	public int nbetats() {
		return nbetats;
	}

	public int etat_initial() {
		return etat_init;
	}
	
	public String toString(){
		String s = "";
		int l = this.nbetats();
    	int k = this.nbconditions();
    	int i,j;
		System.out.println("actions :" );
    	for(i=0; i<k; i++){
    		for(j=0;j<l;j++){
    			System.out.print(this.action(i,j)+"  ");
    		}
    		System.out.print("\n");
    	}
		System.out.println("\n\ntransitions :" );
    	for(i=0; i<k; i++){
    		for(j=0;j<l;j++){
    			System.out.print(this.transition(i,j)+ "  ");
    		}
    		System.out.print("\n");
    	}
    	return s;
	}
    
}