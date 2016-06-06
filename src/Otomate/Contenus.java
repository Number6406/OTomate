package Otomate;

public enum Contenus {
    
    //Objets directement construits
    Terre("de la Terre", 0),
    Pomme ("une Pomme", 1),
    Carotte ("une Carotte", 2),
    Gateau ("un Gateau", 3),
    Herbe ("de l'Herbe", 4),
    Sable ("du Sable", 5),
    Arbre ("un Arbre", 6),
    Buisson ("un Buisson", 7),
    Eau ("de l'Eau", 8),
    Arc ("un Arc", 9),
    Sabre ("un Sabre", 10),;
    
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
    
    public static Contenus fromint(int x){
        switch(x) {
        case 0:
            return Terre;
        case 1:
            return Pomme;
        case 2:
            return Carotte;
        case 3:
            return Gateau;
        case 4:
            return Herbe;
        case 5:
            return Sable;
        case 6:
            return Arbre;
        case 7:
            return Buisson;
        case 8:
            return Eau;
        case 9:
            return Arc;
        case 10:
            return Sabre;
        }
        return null;
    }
    
}