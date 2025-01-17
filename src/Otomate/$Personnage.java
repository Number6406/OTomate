package Otomate;

import java.awt.Color;
import java.util.List;

import Actions.$Action;
import ImageEditor.ImageColor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public abstract class $Personnage {

	// Attributs
	protected int viemax;
	protected int vie; // vie actuelle NE PAS DEPASSER viemax
	protected int etat;
	protected Coordonnees position;
	protected Automate a;
	protected int inventaire;
	protected String nom;
	protected int dmg;
	protected int inactivite;
	protected Color couleur;
	protected BufferedImage sprite = null;
	protected String spriteURL = null;
        protected static BufferedImage basicSprite = null;
        protected static ImageColor ic;

	// Constructeur
	protected $Personnage(String file, Color couleur, String name) {
                
                if(this instanceof Gentil) {
                    spriteURL = Jeu.univers.spriteGentil();
                } else {
                    spriteURL = Jeu.univers.spriteMechant();
                }
            
		a = new Automate(file);
		etat = a.etat_initial();
		position = new Coordonnees(3, 5);
		viemax = 100;
		inventaire = 0;
		inactivite = 20;
		nom = name;
		dmg = 10;
		this.couleur = couleur;
                try {
                    System.out.println(spriteURL);
                    basicSprite = ImageIO.read(new File(this.getClass().getResource(spriteURL).getFile()));
                    ic = new ImageColor(basicSprite);
                    int basicColor = ic.toRGB(10, 64, 7);
                    sprite = ic.changeColor(basicColor, couleur.getRGB());
                } catch (IOException ex) {
                    System.out.println(ex);
                }
	}

	protected $Personnage($Personnage cpy, Color couleur, String name) {

        if(this instanceof Gentil) {
            spriteURL = Jeu.univers.spriteGentil();
        } else {
            spriteURL = Jeu.univers.spriteMechant();
        }
        
		a = cpy.getAutomate();
		etat = a.etat_initial();
		position = new Coordonnees(0, 0);
		viemax = 100;
		inventaire = 0;
		nom = name;
		dmg = 10;
		this.couleur = couleur;
		try {
            System.out.println(spriteURL);
            basicSprite = ImageIO.read(new File(this.getClass().getResource(spriteURL).getFile()));
            ic = new ImageColor(basicSprite);
            int basicColor = ic.toRGB(10, 64, 7);
            sprite = ic.changeColor(basicColor, couleur.getRGB());
        } catch (IOException ex) {
            System.out.println(ex);
        }
	}

	// Getteurs

	public $Personnage() {
		a = new Automate();
	}

	public Automate getAutomate() {
		return a;
	}

	public int nbEtat() {
		return a.nbetats();
	}

	public int getEtat() {
		return etat;
	}

	public int getVie() {
		return vie;
	}

	public int getViemax() {
		return viemax;
	}

	public int getDmg() {
		return dmg;
	}

	public Coordonnees getPosition() {
		return this.position;
	}
        
        public int positionX() {
            return this.position.getX();
        }
        
        public int positionY() {
            return this.position.getY();
        }

	public int getInventaire() {
		return inventaire;
	}

	public String getNom() {
		return nom;
	}

	public Color getCouleur() {
		return couleur;
	}

	public String getNomHtml() {
		return "<b><font color=\"rgb(" + couleur.getRed() + "," + couleur.getGreen() + "," + couleur.getBlue() + ")\">"
				+ nom + "</font></b>";
	}

	public BufferedImage getSprite() {
		return this.sprite;
	}
        
        public void setSprite() {
            try {
                ImageIO.read(new File(this.getClass().getResource("/Graphics/Sprites/1.gif").getFile()));
            } catch (IOException ex) {
                Logger.getLogger($Personnage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public String getEffets() {
            if (vie <= 0) {
                return "Mort ";
            }
            return "";
        }
        
        public int getInactivite(){
        	return inactivite;
        }

	// Setteurs
        public void setNom(String nom) {
            this.nom = nom;
        }
        
	public void SetEtat(int symbole) {
		if (a.transition(symbole, etat) != 0)
			etat = a.transition(symbole,etat);
	}
	
	public void setEtat(int pEtat) {
		etat = pEtat;
	}

	public void setVie(int Vie) {
		this.vie = Vie;
	}

	public void setViemax(int Vie) {
		this.viemax = Vie;
	}

	public void setPosition(Coordonnees c) {
		position = c;
	}

	public void setInventaire(int Inventaire) {
		this.inventaire = Inventaire;
	}

	public void setCouleur(Color c) {
		this.couleur = c;
	}

	public void setSpriteURL(String url) {
		this.spriteURL = url;
	}
	
	public void setDmg(int d) {
		dmg = d;
	}
	
	public void setInactivite(int deathtime){
		inactivite = deathtime;
	}

	// Override
	public String toString() {
		String s = "PV : " + viemax + "\n";
		s += "Inventaire : " + /* Contenus.fromint(inventaire).toString() + */ "\n";
		s += "Position : " + position.toString() + "\n";
		s += "Automate : " + a.toString() + "\n";
		s += "Etat courant : " + etat;
		return s;
	}

	// M�thodes
	/**
	 * 
	 * @param G, la grille de Jeu
	 * @param listJoueur,la liste des joueurs
	 * @param U, l'univers
	 * @return le nom de l'action effectu�e
	 */
	public String jouer(Grille G, List<Joueur> listJoueur, Univers U){
		List<Boolean> lb = G.recupcond(this, U.getConditions(), U.getObjets(), listJoueur);
		List<Integer> lc = G.conditions(this, lb);
		List<Integer> lt = G.transitionsPossibles(this,lc);
		List<Integer> la = G.actionsPossibles(this, lc);
		$Action actionAFaire;		
		int numaction;
		if(la.size()!=0){
			int x = Grille.random(0, la.size());
			numaction = la.get(x);
			this.setEtat(lt.get(x));
		} else {
			numaction = 0; // Ne rien faire
		}
		
		if(this instanceof Gentil){
			actionAFaire = U.getActionsGentil().get(numaction);
		} else {
			actionAFaire = U.getActionsMechant().get(numaction);
		}
		G.Maj(this, actionAFaire, listJoueur, lc);
		return actionAFaire.toString();
	}

	public String getEtatString(){
		return "Sain";
	}
}