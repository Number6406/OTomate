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
    //Bandage ("un bandage", 13),
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
    
    /*public static Contenus fromint(int x){
        switch(x) {
        case 0:
            return Herbe;
        case 1:
            return Terre;
        case 2:
            return Gravier;
        case 3:
            return Sable;
        case 4:
            return Eau;
        case 5:
            return Immeuble;
        case 6:
            return EauContaminee;
        case 7:
            return Conserve;
        case 8:
            return Bouteille;
        case 9:
            return Couteau;
        case 10:
            return Pompe;
        case 11:
        	return Hopital;
        case 12:
        	return Grange;
        case 13:
        	return Cactus;
        case 14:
        	return Bandage;
        case 15:
        	return Armure;
        }
        return null;
    }*/
    
}