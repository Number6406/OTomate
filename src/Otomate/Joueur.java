package Otomate;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * Pour l'instant un joueur a UN SEUL Personnage ---> PLUS MAINTENANT LOL MDR XD
 *
 */
public class Joueur {

    String name;
    boolean mechant;
    private Color couleur;
    //Attributs
    private List<$Personnage> personnages;

    //M�thodes
    public Joueur(String name, List<String> xmls, boolean estMechant, int nbMechants, Color couleur) {
        this.name = name;
        personnages = new LinkedList<>();
        mechant = estMechant;
        setCouleur(couleur);
        if (mechant) {
            for (int i = 0; i < nbMechants; i++) {
            	personnages.add(new Mechant(xmls.get(0), couleur,name + "_" + (i+1)));
            	//personnages.add(new Mechant(personnages.get(0),couleur));
            }
        } else {
            for (int i = 0; i < xmls.size(); i++) {
                personnages.add(new Gentil(xmls.get(i), couleur,name + "_" + (i+1)));
            }
        }
    }
    
    /** FOR DEBUG PURPOSE */
    public Joueur($Personnage p) {
        personnages = new LinkedList<>();
        setCouleur(Color.pink);
        personnages.add(p);
    }

    public Joueur() {
    	personnages = new LinkedList<$Personnage>();
	}

	public String getName() {
        return name;
    }

    public List<$Personnage> getPersonnages() {
        return personnages;
    }
    
    public void setPersonnages(List<$Personnage> l) {
    	personnages = l;
    }
    
    public $Personnage getPersonnagesI(int i){
    	return personnages.get(i);
    }

    public int getSizePersonnages() {
        return personnages.size();
    }
	public Color getCouleur() {
		return couleur;
	}
	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}
	
	public boolean estMechant() {
		return mechant;
	}

	public void setMechant(boolean estMechant) {
		mechant = estMechant;
	}

	public void setName(String l) {
		name = l;
	}
}