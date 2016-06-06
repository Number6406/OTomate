package Otomate;

import Parser.Parser;

public class Gentil extends $Personnage{
    
	public Gentil(){
		
	}

	public Gentil(String file){
        a.nbconditions = 12;
        vie = 10;
        int arme = 0;
        int consommable = 0;
        Parser P = new Parser(file);
        a.actions = P.act;
        a.transitions = P.auto;
        nbetats = P.nb_etat;
        etat = P.etat_init;
    }
}