package Otomate;

public enum Actions {
    
    //Objets directement construits
    Rien ("Ne fais rien", 1),
    AvancerN ("Avance au Nord", 2),
    AvancerS ("Avance au Sud", 3),
    AvancerE ("Avance à l'Est", 4),
    AvancerO ("Avance à l'Ouest", 5),
    Ramasser ("Ramasse un objet sur sa case", 6),
    FrapperN ("Frappe au Nord", 7),
    FrapperS ("Frappe au Sud", 8),
    FrapperE ("Frappe à l'Est", 9),
    FrapperO ("Frappe à l'Ouest", 10),;
    
    private int value;
    private String name = "";
    
    //Constructeur
    Actions(String name,int value){
        this.value = value;
        this.name = name;
    }
    
    
    
    public int getValeur(){
        return value;    
    }
    
    public String toString(){
        return name;
    }
    
    public static Actions fromint(int x){
        switch(x) {
        case 1:
            return Rien;
        case 2:
            return AvancerN;
        case 3:
            return AvancerS;
        case 4:
            return AvancerE;
        case 5:
            return AvancerO;
        case 6:
            return Ramasser;
        case 7:
            return FrapperN;
        case 8:
            return FrapperS;
        case 9:
            return FrapperE;
        case 10:
            return FrapperO;
        }
        return null;
    }
    
}