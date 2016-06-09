package Otomate;

import java.util.List;

public abstract class $Personnage {
    
    //Attributs
    protected int vie;
    protected int etat;
    protected Coordonnees position;
    protected Automate a;
    protected int inventaire;
    protected String nom;
    
    //Constructeur
    protected $Personnage(String file)
    {
    	a = new Automate(file);
    	etat = a.etat_initial();
    	position = new Coordonnees(1,7);
        vie = 10;
        inventaire = 0;
        nom ="Bob sans nom";
    }
    
    //Mthodes
    
    public int nbEtat(){
        return a.nbetats();
    }
    
    void setEtat(int symbole){
        if(a.transitions[symbole][etat] != 0)
            etat = a.transitions[symbole][etat];
    }
    
    public Automate getAutomate(){
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
    
    //Override
    public String toString(){
    	String s = "PV : " + vie + "\n";
    	s += "Inventaire : " + /*Contenus.fromint(inventaire).toString() +*/ "\n";
    	s += "Position : " + position.toString() + "\n";
    	s += "Automate : " + a.toString() +"\n";
    	s += "Etat courant : " + etat;
    	return s;
    }

	public String getNom() {
		return nom;
	}
}