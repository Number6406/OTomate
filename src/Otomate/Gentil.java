package Otomate;

public class Gentil extends $Personnage{
    
    public Gentil(String file){
        a.nbconditions = 12;
        vie = 10;
        arme = 0;
        consommable = 0;
        Parser P = new Parser(file);
        a.actions = P.act;
        a.transitions = P.auto;
        nbetats = P.nb_etat;
        etat = P.etat_init;
    }
}