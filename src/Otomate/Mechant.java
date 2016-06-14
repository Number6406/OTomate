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
            vie = viemax;
            try {
                sprite = ImageIO.read(new File(this.getClass().getResource(spriteURL).getFile()));
            } catch (IOException ex) {
                Logger.getLogger(Mechant.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

	public Mechant($Personnage cpy) {
		super(cpy);
		vie = viemax;
	}

	public Mechant() {
		super();
	}

	public String toString() {
		return "// Méchant //\n" + super.toString();
	}
}