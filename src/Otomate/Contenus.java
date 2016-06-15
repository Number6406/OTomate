package Otomate;

public enum Contenus {
    
    //Objets directement construits
    Herbe ("de l'herbe", 0),
    Terre ("de la terre", 1),
    Gravier ("du gravier", 2),
    Sable ("du sable", 3),
    Eau ("de l'eau", 4),
    Immeuble ("un immeuble", 5), 	//pompe
    EauContaminee ("de l'eau contaminée", 6),
    Conserve ("une boite de conserve", 7),
    Bouteille ("une bouteille", 8),
    Couteau ("un couteau", 9),
    Hopital ("un hopital", 10),
    Grange ("une grange", 11),
    Cactus ("un cactus", 12),
    Seringue ("un peu de drogue?", 13),
	Branches ("une branche", 14),
	Pompe ("un fusil a pompe", 15);
    
    private int value;
    private String name = "";
    
    //Constructeur
    Contenus(String name,int value){
        this.value = value;
        this.name = name;
    }
    
    public int getValeur(){
        return value;    
    }
    
    public String toString(){
        return name;
    }
}