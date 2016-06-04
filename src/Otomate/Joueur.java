package Otomate;

import java.util.List;


public class Joueur {
    String name;
    //Attributs
    private List<Personnage> personnages;
    
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
    
    public Personnage getPersonnagesI(int i){
    	return personnages.get(i);
    }
    
    public int getSizePersonnages(){
    	return personnages.size();
    }
    
}