package Otomate;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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
    public Joueur(List<String> xmls, boolean mechant, int nbMechants, int nbPersosParJoueur) {
    	personnages = new LinkedList<>();
    	if(mechant) {
    		personnages.add(new Mechant(xmls.get(0)));
    		for(int i=1; i<nbMechants; i++) {
    			personnages.add(new Mechant(personnages.get(0)));
    		}
    	} else {
    		for(int i=0; i<nbPersosParJoueur; i++) {
    			if(i<xmls.size()) {
    				personnages.add(new Gentil(xmls.get(i)));
    			} else {
    				personnages.add(new Gentil(xmls.get(0)));
    			}
    		}
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