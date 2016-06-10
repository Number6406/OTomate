package Otomate;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * Pour l'instant un joueur a UN SEUL Personnage  ---> PLUS MAINTENANT LOL MDR XD
 *
 */
public class Joueur {
    String name;
    //Attributs
    private List<$Personnage> personnages;
    
    //M�thodes
    public Joueur(List<String> xmls) {
    	personnages = new LinkedList<>();
        for
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