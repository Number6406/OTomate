package Otomate;

import java.util.List;

public abstract class $Personnage {
    
    //Attributs
    protected int vie;
    protected int etat;
    protected Coordonnees position;
    protected Automate a;
    protected int inventaire;
    
    //Constructeur
    protected $Personnage(String file)
    {
    	a = new Automate(file);
    	int l = a.nbetats();
    	int k = a.nbconditions();
    	int i,j;
		System.out.println("actions :" );
    	for(i=0; i<k; i++){
    		for(j=0;j<l;j++){
    			System.out.print(a.action(j,i)+"  ");
    		}
    		System.out.print("\n");
    	}
		System.out.println("\n\ntransitions :" );
    	for(i=0; i<k; i++){
    		for(j=0;j<l;j++){
    			System.out.print(a.transition(j,i)+ "  ");
    		}
    		System.out.print("\n");
    	}
    	etat = a.etat_init;
    	position = new Coordonnees(2,13);
        vie = 10;
        inventaire = 0;
    }
    
    //Mthodes
    
    public void jouer(Grille plateau, List<Joueur> joueurs) {
        plateau.Maj(this, Grille.takeOne(Grille.ActionsPossibles(this)), joueurs); //#EffortMaximum
    }
    
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
    /**
     * Renvoie l'ancien contenu de l'inventaire du personnage en le remplacant par l'objet donn.
     */
    public int ramasser(int objet){
        int contenu = this.inventaire;
        this.inventaire = objet;
        return contenu;
    }
    
    /**
     * Fonctions de dplacement sur la map
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