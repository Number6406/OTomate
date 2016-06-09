package Otomate;

import java.awt.Color;
import java.util.List;

public abstract class $Personnage {
    
    //Attributs
    protected int viemax;
    protected int etat;
    protected Coordonnees position;
    protected Automate a;
    protected int inventaire;
    protected String nom;
    protected int dmg;
    protected Color couleur;
    
    //Constructeur
    protected $Personnage(String file)
    {
    	a = new Automate(file);
    	etat = a.etat_initial();
    	position = new Coordonnees(1,7);
        viemax = 100;
        inventaire = 0;
        nom ="Bob";
        dmg = 10;
        couleur = Color.BLUE; 
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
        return viemax;
    }
    
    public void setVie(int Vie){
        this.viemax=Vie;
    }
    
    public Coordonnees getPosition(){
        return this.position;
    }
     
    public int getInventaire(){
        return inventaire;
    }

	public String getNomHtml() {
		return "<font color=\"rgb(" + couleur.getRed() +"," + couleur.getGreen() + "," + couleur.getBlue() + ")\">" + nom + "</font>";
	}
	
	public String getNom() {
		return nom;
	}
	
	public Color getCouleur(){
		return couleur;
	}
	
	public void setColor(Color c){
		this.couleur = c;
	}
    
    //Override
    public String toString(){
    	String s = "PV : " + viemax + "\n";
    	s += "Inventaire : " + /*Contenus.fromint(inventaire).toString() +*/ "\n";
    	s += "Position : " + position.toString() + "\n";
    	s += "Automate : " + a.toString() +"\n";
    	s += "Etat courant : " + etat;
    	return s;
    }
}