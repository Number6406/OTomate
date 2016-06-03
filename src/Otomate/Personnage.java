package Otomate;

public class Personnage {
    
    //Attributs
    protected int vie;
    protected int arme;
    protected int consommable;
    protected int etat;
    public Coordonnees position;
    protected Automate t;
    protected Automate a;
    
    //Méthodes
    public Personnage(String xml) {
        /** Parser XML
         * Creation des automates t et a
         * Remplissage des attributs du personnage
         * */
    }
    
    public Personnage(){
    	position = new Coordonnees();
    	vie=0;
    	arme=0;
    	consommable=0;
    	etat=0;
    }
   /* void setEtat(int symbole){
        if(t[symbole][etat] != 0)
            etat = t[symbole][etat];
    }
   */
    
}