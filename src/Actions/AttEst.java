package Actions;

import java.util.List;

import Otomate.$Personnage;
import Otomate.Coordonnees;
import Otomate.Gentil;
import Otomate.Mechant;
import Otomate.Grille;

public class AttEst extends $Action {

	public AttEst(String succes, String echec) {
		super(succes, echec);
		// TODO Auto-generated constructor stub
	}

	public AttEst() {
		valeur = 7;
	}

	public void todo(List<Integer> l, $Personnage p, List<$Personnage> lp, Grille g) {
		if (l.get(2) == 7) { // 7 = ennemi a l'est et 3eme element regard a l'est
			$Personnage e = null;
			Coordonnees card = p.getPosition();
			card.setX(card.getX() + 1);
			int s = lp.size();
			int i = 0;
			while (i < s) {
				if (lp.get(i).getPosition() == card) {
					e = lp.get(i);
					i = s;
				}
			}
			if (p instanceof Gentil) {
				if (e != null && e instanceof Mechant)
					((Mechant) e).setVie(((Mechant) e).getVie() - (p.getDmg() + ((Gentil) p).getArme()));
				}
			else if (e != null && e instanceof Gentil) {
				((Gentil) e).setVie(((Gentil) e).getVie() - p.getDmg());
				if (Grille.random(0, 101) > 24)	//75% de chances de souffrir de saignement apres une attaque de zombies
					((Gentil) e).setSaignement(true);
				if (Grille.random(0, 101) > 4)	//95% de chances d'etre infecte
					((Gentil) e).setInfecte(true);
			}
			effect = true;
		}
		else { // Echec de l'action
			effect = false;
		}
	}
}
