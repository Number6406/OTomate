package Otomate;
/*
 * b � [0,1] � |N

i < iMax

    	{ 1 : arme
type �  { 2 : consommable
    	{ 3 : antidote ?
    	{ 4 : terrain
    	{ 5 : mur

use � { si arme = degat
    { si consommable = regen
 */

public class Objet {
	
	// Attributs
	int id;
	int type;
	int use;
	String name;
	int passable;
	String path;
	
	// Constructeur
	public Objet(int id, int type, int use, String name, int passable) {
		this.id = id;
		this.type = type;
		this.use = use;
		this.name = name;
		this.passable = passable;
	}
	
	// Getteurs
	public int getType() {
		return type;
	}

	public int getUse() {
		return use;
	}

	public String getName() {
		return name;
	}

	public int getPassable() {
		return passable;
	}
	
	public String getPath(){
		return path;
	}
	
	
	// Setteurs
	public void setType(int i) {
		type = i;
	}

	public void setUse(int i) {
		use = i;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassable(int i) {
		passable = i;
	}

}
