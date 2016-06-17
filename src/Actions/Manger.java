package Actions;

import java.util.List;

import Otomate.$Personnage;
import Otomate.Gentil;
import Otomate.Grille;

public class Manger extends $Action {
	public Manger(String succes, String echec) {
		super(succes, echec);
	}

	public Manger() {
		valeur = 11;
	}

	public void todo(List<Integer> l, $Personnage p, List<$Personnage> lp, Grille g) {
		if (p instanceof Gentil) {
			if ((p.getInventaire() == 7) || (p.getInventaire() == 6)) {
				int gain = Grille.random(5, 10);
				((Gentil) p).setVie(((Gentil) p).getVie() + gain);
				if (((Gentil) p).getVie() > ((Gentil) p).getViemax())
					((Gentil) p).setVie(((Gentil) p).getViemax());
				p.setInventaire(0);
				effect = true;
			}
			else {
				effect = false;
			}
		}
	}
}
