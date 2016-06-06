package Otomate;

import Parser.Parser;

public class Gentil extends $Personnage{
    
	public Gentil(){
	    	position = new Coordonnees();
	    	vie=0;
	    	//arme=0;
	    	//consommable=0;
	    	etat=0;
			inventaire=0;
		}

	public Gentil(String file){
        a.nbconditions = 12;
        vie = 10;
        inventaire = 0;
        Parser P = new Parser(file);
        a.actions = P.act;
        a.transitions = P.auto;
        nbetats = P.nb_etat;
        etat = P.etat_init;
    }
}