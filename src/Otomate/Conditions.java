package Otomate;

public enum Conditions {
    
    //Objets directement construits
	CaseVide ("Case vide", 0),
    CheminN ("Chemin au Nord", 1),
    CheminS ("Chemin au Sud", 2),
    CheminE ("Chemin au Est", 3),
    CheminO ("Chemin au Ouest", 4),
    EnnemiN ("Ennemi au Nord", 5),
    EnnemiS ("Ennemi au Sud", 6),
    EnnemiE ("Ennemi au Est", 7),
    EnnemiO ("Ennemi au Ouest", 8),
    Comestible ("Comestible sur ma Case", 9),
    //Pompe ("pompe sur ma Case", 10),
    Arme ("un arme sur la case", 10),	//pompe ou couteau ou branche
	ObstacleN ("Obstacle au nord", 11),
	ObstacleS ("Obstacle au sud", 12),
	ObstacleE ("Obstacle à l'est", 13),
	ObstacleO ("Obstacle à l'ouest", 14),
	Fouillable ("est fouillable", 15),
	Seringue ("la drogue c'est bon", 16),
	VieManquante ("le personnage a perdu de la vie", 17),
	Cactus ("Cactus sur ma case", 18),
	ViePleine ("Les pvs du personnages sont au max", 19);
	
    private int id;
    private int value;
    private int type;
    private String name = "";
    
    //Constructeur
    Conditions(String name,int value){
        this.value = value;
        this.name = name;
    }
    
    Conditions(int id,int value,int type){
    	this.id=id;
    	this.value=value;
    	this.type=type;
    }
    
    public int getValue(){
    	return value;
    }
    
    public int getType(){
    	return type;
    }
    
    public int getId(){
    	return id;
    }
    
    public void setType(int i){
    	type=i;
    }
    
    public void setId(int i){
    	id=i;
    }
    
    public void setValeur(int val){
    	value=val;
    }
    
    public int getValeur(){
        return value;    
    }
    
    public String toString(){
        return name;
    }
    
    /*public static Conditions fromint(int x){
        switch(x) {
        case 1:
            return CheminN;
        case 2:
            return CheminS;
        case 3:
            return CheminE;
        case 4:
            return CheminO;
        case 5:
            return EnnemiN;
        case 6:
            return EnnemiS;
        case 7:
            return EnnemiE;
        case 8:
            return EnnemiO;
        case 9:
            return Comestible;
        case 10:
            return Arc;
        case 11:
            return Sabre;
        }
        return null;
    }*/
    
    
}