package Otomate;

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

	public void setPiegee(boolean pie) {
    	piegee = pie;
    }
}