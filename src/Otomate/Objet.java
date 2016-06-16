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
	private int id;
	private int type;
	private int use;
	private String name;
	private boolean passable;
	private String path;
	private String piege;
	
	// Constructeur
	public Objet(int id, int type, int use, String name, boolean passable, String path, String piege) {
		this.id = id;
		this.type = type;
		this.use = use;
		this.name = name;
		this.passable = passable;
		this.path = path;
		this.piege = piege;
	}
	
	// Getteurs
    public int getId() {
        return id;
    }
        
	public int getType() {
		return type;
	}

	public int getUse() {
		return use;
	}

	public String getName() {
		return name;
	}

	public boolean estPassable() {
		return passable;
	}
	
	public String getPath(){
		return path;
	}
	
	public String getPathPiege(){
		return piege;
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

	public void setPassable(boolean b) {
		passable = b;
	}

}
