package Otomate;

import ImageEditor.ImageColor;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Gentil extends $Personnage {
	/*
	 * arme = 25.pompe 15.couteau 5 .branche correspond au degats supp
	 */

	/*
	 * type drogue = 0.nickel 1.bonus hp max 2.malus hp.max 3. + hp/tour 4. -
	 * hp/tour 5.adrenaline 6. passe 7. + vie 8. - vie
	 */

	/*
	 * remede = 1.antidote 2.bandage
	 */

	private int vie; // vie actuelle NE PAS DEPASSER viemax
	private int arme, remede, drogue;
	private int paralysie, piege; // nb de tour a jouer, att avant prochain
									// piege
	private int efdrogue, efsaignement; // calcule le nombre de tours restant et
										// effet a effectuer
	private boolean infecte, saignement;
        
        protected static BufferedImage basicSprite = null;
        protected static ImageColor ic = new ImageColor(basicSprite);
        protected static int basicColor = ic.toRGB(10, 64, 7);

	public Gentil(String file, Color couleur) {
		super(file, couleur);
		arme = remede = drogue = 0;
		infecte = saignement = false;
		vie = viemax;
		paralysie = 1;
		piege = 10;
		efdrogue = 0;
		efsaignement = 0;
                try {
                    basicSprite = ImageIO.read(new File(this.getClass().getResource(spriteURL).getFile()));
                    sprite = ic.changeColor(basicColor, couleur.getRGB());
                } catch (IOException ex) {
                    System.out.println(ex);
                }
	}

	public int getArme() {
		return arme;
	}

	public void setArme(int Arme) {
		this.arme = Arme;
	}

	public int getRemede() {
		return remede;
	}

	public void setRemede(int Remede) {
		this.remede = Remede;
	}

	public int getParalysie() {
		return paralysie;
	}

	public void setParalysie(int Paralysie) {
		this.paralysie = Paralysie;
	}

	public int getEfdrogue() {
		return arme;
	}

	public void setEfdrogue(int Efdrogue) {
		this.efdrogue = Efdrogue;
	}

	public int getVie() {
		return vie;
	}

	public void setVie(int Vie) {
		this.vie = Vie;
	}

	public boolean getSaignement() {
		return saignement;
	}

	public void setSaignement(boolean Saignement) {
		this.saignement = Saignement;
	}

	public boolean getInfecte() {
		return infecte;
	}

	public void setInfecte(boolean Infecte) {
		this.infecte = Infecte;
	}

	public String toString() {
		return "// Gentil //\n" + super.toString();
	}

	public int getDrogue() {
		return drogue;
	}

	public void setDrogue(int Drogue) {
		this.drogue = Drogue;
	}

}