package Otomate;

import ImageEditor.ImageColor;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Gentil extends $Personnage {

    /*
	 * arme = numero de l'objet
	 * (25.pompe 15.couteau 5 .branche correspond au degats supp)
     */

 /*
	 * type drogue = 0.nickel 1.bonus hp max 2.malus hp.max 3. + hp/tour 4. -
	 * hp/tour 5.adrenaline 6. passe 7. + vie 8. - vie
     */

 /*
	 * remede = 1.antidote 2.bandage
     */
    private Objet arme;
    private int remede, drogue;
    private int paralysie, piege; // nb de tour a jouer, att avant prochain
    // piege
    private int efdrogue; // calcule le nombre de tours restant et
    // effet a effectuer
    private boolean infecte, saignement;

    public Gentil(String file, Color couleur) {
        super(file, couleur);
        arme = null;
        remede = drogue = 0;
        infecte = saignement = false;
        vie = viemax;
        paralysie = 1;
        piege = 10;
        efdrogue = 0;
        nom = "Gentil";
    }
    
    public boolean estArme() {
    	return arme!=null;
    }

    public int getDmg() {
        if (arme == null) {
            return dmg;
        } else {
            return arme.getUse();
        }
    }

    public Gentil() {
		super();
	}

    public Objet getArme() {
        return arme;
    }

    public void setArme(Objet Arme) {
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
        return efdrogue;
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

    public int getPiege() {
        return piege;
    }

    public void setPiege(int nbtour) {
        piege = nbtour;
    }

    @Override
    public String getEffets() {
        String effets = super.getEffets();

        if (infecte) {
            effets += "Infecte ";
        }
        if (saignement) {
            effets += "Saignement ";
        }

        return effets;
    }

}
