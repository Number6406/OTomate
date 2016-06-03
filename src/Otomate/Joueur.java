package Otomate;

import java.util.List;


public class Joueur {
    String name;
    //Attributs
    public List<Personnage> personnages;
    
    //Méthodes
    public Joueur(String xml) {
        personnages.add(new Personnage(xml));
    }
    public String getName() {
        return name;
    }
    
    public List<Personnage> getPersonnages() {
        return personnages;
    }
    
}