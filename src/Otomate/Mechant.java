package Otomate;

import java.awt.Color;

public class Mechant extends $Personnage {

	public Mechant(String file, Color couleur,String name) {
            super(file, couleur,name);
            vie = viemax;
	}

	public Mechant() {
		super();
	}

	public Mechant($Personnage perso, Color couleur, String name, Coordonnees c) {
		super(perso,couleur, name);
		vie = viemax;
		setPosition(c);
	}

	public String toString() {
		return "// Méchant //\n" + super.toString();
	}
	public String getEtatString(){
		return "";
	}
}