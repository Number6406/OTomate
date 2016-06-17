package Otomate;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

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