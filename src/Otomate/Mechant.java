package Otomate;

import Parser.Parser;

public class Mechant extends $Personnage{
    
    public Mechant(String file){
        a.nbconditions = 12;
        vie = 10;
        inventaire = 0;
        Parser P = new Parser(file);;
        nbetats = P.nb_etat;
        a.actions = P.act;
        a.transitions = P.auto;
        etat = P.etat_init;
    }
}