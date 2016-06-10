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
    public Joueur(List<String> xmls) {
    	personnages = new LinkedList<>();
    	Random rnd = new Random();
    	int k = rnd.nextInt(xmls.size());
        for(int i=0; i<xmls.size(); i++) {
        	personnages.add(new Gentil(xmls.get(i)));
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