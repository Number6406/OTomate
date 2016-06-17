package Otomate;

import java.util.List;

public class Case {

	// Attributs
	public int element;
	public boolean piegee;

	// Constructeurs
	public Case() {
		element = 0;
		piegee = false;
	}

	public Case(int valeur) {
		element = valeur;
		piegee = false;
	}

	// Methodes
	public int getValeur() {
		return element;
	}

	public void setValeur(int valeur) {
        element = valeur;
    }

	public void setPiegee(boolean pie) {
    	piegee = pie;
    }
	
	public boolean getPiegee(){
		return piegee;
	}
	
	public boolean Passable(List<Objet> l){
		int i;
    	for(i=0;i<l.size();i++){
    		if(l.get(i).getId()==this.element) return l.get(i).estPassable();
    	}
    	
    return false;
    }
}