package Otomate;

public class Personnage {
    
    //Attributs
    protected int vie;
    protected int arme;
    protected int consommable;
    protected int etat;
    protected Coordonnees position;
    protected Automate t;
    protected Automate a;
    
    //Méthodes
    public Personnage(String xml) {
        /** Parser XML
         * Creation des automates t et a
         * Remplissage des attributs du personnage
         * */
    }
   /* void setEtat(int symbole){
        if(t[symbole][etat] != 0)
            etat = t[symbole][etat];
    }
   */
    
}