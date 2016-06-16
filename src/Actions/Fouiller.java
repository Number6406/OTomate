package Actions;

import java.util.List;

import Otomate.$Personnage;
import Otomate.Gentil;
import Otomate.Grille;
import Otomate.Jeu;

public class Fouiller extends $Action {

	public Fouiller(String succes, String echec) {
		super(succes, echec);
		// TODO Auto-generated constructor stub
	}

	private boolean anti = false, pompe = false, rem = false;
	int trouve;

	public Fouiller() {
		valeur = 14;
	}

	public void todo(List<Integer> l, $Personnage p, List<$Personnage> lp, Grille g) {
		if (p instanceof Gentil) {
			int rnd;
			if (l.get(0) == 15) {
				if (g.Pos(p.getPosition()).getValeur() == 4) { // on se
																	// trouve
																	// dans un
																	// immeuble
					rnd = Grille.random(0, 101);
					if (rnd < 30) {// trouve des bandages 30% de chances
						((Gentil) p).setRemede(2);
						rem = true;
					}
					else if (rnd < 40) { // trouve un fusil a pompe 10% de chances
						((Gentil) p).setArme(g.getUnivers().getObjets().get(14));
						pompe = true;
						trouve = 14;
					}
					else if (rnd < 70) {// trouve une boite de conserve 30% de chances
						p.setInventaire(6); trouve = 6;
					}
					else  {// trouve une bouteille 30% chances
						p.setInventaire(7);
						trouve = 7;
					}
				}
				else if (g.Pos(p.getPosition()).getValeur() == 9) {	//on se trouve dans un hopital
					rnd = Grille.random(0, 101);
					if (rnd < 50) { // Recupere un antidote 50% chances
						((Gentil) p).setRemede(1);
						anti = true;
					}
					else {// Recupere des bandages 50% chances
						((Gentil) p).setRemede(2);
						rem = true;
					}
				}
				else if (g.Pos(p.getPosition()).getValeur() == 10) {	//on se trouve dans un grange
					rnd = Grille.random(0, 101);
					if (rnd < 25) { // S'equipe d'un fusil a pompe 25% chances
						((Gentil) p).setArme(g.getUnivers().getObjets().get(14));
						pompe = true;
						trouve = 14;
					} else if (rnd < 40) {// Trouve des bandages 15% chances
						((Gentil) p).setRemede(2);
						rem = true;
					}
					else {// Ramasse une bouteille 60% chances
						p.setInventaire(7);
						trouve = 7;
					}
				}
				effect = true;
			}
			else {
				effect = false;
			}
		}
	}

	public String toString() {
		if (effect == true ) {
			if(pompe == true) {
			return (" a trouvé " + Jeu.univers.getObjets().get(14).getName() + " lors de sa recherche :o");
			}
			else if (anti == true) {
				return ("a trouvé " + Jeu.univers.getNomAntidote());
			} else if (rem == true) {
				return ("a trouvé " + Jeu.univers.getNomRemede());
			} else {
				return ("a trouvé " + Jeu.univers.getObjets().get(trouve).getName());
			}
		}
		else
			return ("fouille mais ne trouve rien");
	}
}
