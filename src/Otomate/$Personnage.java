package Otomate;

import java.util.List;

public abstract class $Personnage {
    
    //Attributs
    protected int vie;
    protected int etat;
    protected Coordonnees position;
    protected Automate a;
    protected int nbetats;
    protected int inventaire;
    
    //Méthodes
    
    public void jouer(Grille plateau, List<Joueur> joueurs) {
        plateau.Maj(this, Grille.takeOne(Grille.ActionsPossibles(this)), joueurs); // #GG #EffortMaximum #NoPainNoGain #JenAiChie
    }
    
    public int nbEtat(){
        return nbetats;
    }
    
    void setEtat(int symbole){
        if(a.transitions[symbole][etat] != 0)
            etat = a.transitions[symbole][etat];
    }
    
    public Automate getAutotate(){
        return a;
    }
    
    public int getVie(){
        return vie;
    }
    
    public void setVie(int Vie){
        this.vie=Vie;
    }
    
    public Coordonnees getPosition(){
        return this.position;
    }
     
    public int getInventaire(){
        return inventaire;
    }
    /**
     * Renvoie l'ancien contenu de l'inventaire du personnage en le remplacant par l'objet donné.
     */
    public int ramasser(int objet){
        int contenu = this.inventaire;
        this.inventaire = objet;
        return contenu;
    }
    
    /**
     * Fonctions de déplacement sur la map
     */
    public void deplacementDroite(){
        this.getPosition().incrX();
    }
    
    public void deplacementGauche(){
        this.getPosition().decrX();
    }
    
    public void deplacementBas(){
        this.getPosition().incrY();
    }
    
    public void deplacementHaut(){
        this.getPosition().decrY();
    }
}