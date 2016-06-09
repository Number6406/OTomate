package Otomate;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * Pour l'instant un joueur a UN SEUL Personnage
 *
 */
public class Joueur {
    String name;
    //Attributs
    private List<$Personnage> personnages;
    
    //M�thodes
    public Joueur(String xml, boolean mechant) {
    	personnages = new LinkedList<>();
        if(mechant) {
            personnages.add(new Mechant(xml));
        } else {
            personnages.add(new Gentil(xml));
        }
    }
    public String getName() {
        return name;
    }
    
    public List<$Personnage> getPersonnages() {
        return personnages;
    }
    
    public $Personnage getPersonnagesI(int i){
    	return personnages.get(i);
    }
    
    public int getSizePersonnages(){
    	return personnages.size();
    }
    
}