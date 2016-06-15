package Otomate;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Mechant extends $Personnage {

	public Mechant(String file, Color couleur) {
            super(file, couleur);
	}

	public Mechant($Personnage cpy) {
		super(cpy);
		vie = viemax;
	}

	public String toString() {
		return "// Méchant //\n" + super.toString();
	}
}