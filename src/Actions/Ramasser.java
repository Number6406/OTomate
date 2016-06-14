package Actions;

import java.util.List;

import Otomate.$Personnage;
import Otomate.Gentil;
import Otomate.Grille;

public class Ramasser extends $Action {

	public Ramasser(String succes, String echec) {
		super(succes, echec);
		// TODO Auto-generated constructor stub
	}

	private boolean drogue = false;

	public Ramasser() {
		valeur = 9;
	}

	public void todo(List<Integer> l, $Personnage p, List<$Personnage> lp, Grille g) {
		if (l.get(0) != 0) { // 0 = case vide
			int aux;
			if (l.get(0) == 9) { // comestible
				aux = p.getInventaire();
				p.setInventaire(Grille.Pos(p.getPosition()).getValeur());
				Grille.Pos(p.getPosition()).setValeur(aux);
			}
			if (p instanceof Gentil) {
				if (l.get(0) == 10) { // arme
					aux = ((Gentil) p).getArme();
					((Gentil) p).setArme(Grille.Pos(p.getPosition()).getValeur());
					Grille.Pos(p.getPosition()).setValeur(aux);
				}
				else if (l.get(0) == 16) { // seringue
					if (((Gentil) p).getDrogue() == 0) {	//dans le cas ou le personnage est sain
						aux = Grille.random(0, 101);
						if (aux < 10) {	//bonus hp max
							p.setViemax(p.getViemax() + 10);
							((Gentil) p).setDrogue(1);
						}
						else if (aux < 20) {	//malus hp max
							p.setViemax(p.getViemax() - 10);
							if(p.getVie()<p.getViemax())
								p.setVie(p.getViemax());	//barre de hp = hp max si hp > hp max
							((Gentil) p).setDrogue(2);
						}
						else if (aux < 30) {	//bonus hp/tour
							p.setVie(p.getVie() + 5);
							if (p.getVie() > p.getViemax())
								p.setVie(p.getViemax());
							((Gentil) p).setEfdrogue(3);
							((Gentil) p).setDrogue(3);
						}
						else if (aux < 40) {	//malus hp/tour
							p.setVie(p.getVie() - 5);
							((Gentil) p).setEfdrogue(3);
							((Gentil) p).setDrogue(4);
						}
						else if (aux < 60) {
							((Gentil) p).setEfdrogue(2);
							if(aux<50)	//adrenaline = joue 2 tours / row sur 2 row
								((Gentil) p).setDrogue(5);
							else	//passe 2 tour
								((Gentil) p).setDrogue(6);
						}
						else if (aux < 80) {	//bonus vie
							((Gentil) p).setVie(((Gentil) p).getVie() + 10);
							if (((Gentil) p).getVie() > ((Gentil) p).getViemax())
								((Gentil) p).setVie(((Gentil) p).getViemax());
							((Gentil) p).setDrogue(7);
						}
						else {	//malus vie
							((Gentil) p).setVie(((Gentil) p).getVie() - 10);
							((Gentil) p).setDrogue(8);
						}
						drogue = true;
					}
				}
			}
			effect = true;
		}
		else {
			effect = false;
		}
	}

	public String toString() {
		if ((effect == true) && (drogue == false))
			return ("a ramasse quelque chose.");
		else if ((effect == true) && (drogue == true)) {
			return ("a ramassé de la drogue et l'a utilisée");
		} else
			return ("essaye de ramasser quelque chose. Mais il n'y a rien.");
	}
}
