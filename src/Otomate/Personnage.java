package Otomate;

import java.util.List;

public class Personnage {
    
    //Attributs
    protected int vie;
    protected int arme;
    protected int consommable;
    protected int etat;
    public Coordonnees position;
    protected Automate a;
    
    //MÃ©thodes
    public Personnage(String xml) {
        /** Parser XML
         * Creation des automates t et a
         * Remplissage des attributs du personnage
         * */
    }
    public void setEtat(int symbole){
        if(a.transition(symbole,etat) != 0)
            etat = a.transition(symbole,etat);
    }
    
    void jouer(Grille plateau, List<Joueur> joueurs) {
        plateau.Maj(this, plateau.takeOne(plateau.ActionsPossibles(this)), joueurs); //  #GG #EffortMaximum #JaiSuéPourCa #TropFort #NoPainNoGain #MDR
    }
    
    public int getEtat(){
        return etat;
    }
    
    public Personnage(){
    	position = new Coordonnees();
    	vie=0;
    	arme=0;
    	consommable=0;
    	etat=0;
    }
    
    public Automate getAutotate() {
        return this.a;
    }
    
    public int getVie() {
        return this.vie;
    }
    
}