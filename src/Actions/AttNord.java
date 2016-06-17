package Actions;

import java.util.List;

import Otomate.$Personnage;
import Otomate.Coordonnees;
import Otomate.Gentil;
import Otomate.Mechant;
import Otomate.Grille;

public class AttNord extends $Action {

	public AttNord(String succes, String echec) {
		super(succes, echec);
	}

	public AttNord() {
		valeur = 5;
	}

	public void todo(List<Integer> l, $Personnage p, List<$Personnage> lp, Grille g) {
		if (l.get(1) == 5) { //Action reussie
			$Personnage e = null;
			Coordonnees card = new Coordonnees(p.getPosition());
			card.setY(card.getY() - 1);
			int s = lp.size();
			int i = 0;
			while (i < s) {
				if ((lp.get(i).getPosition().getX() == card.getX())&&(lp.get(i).getPosition().getY() == card.getY())){
					e = lp.get(i);
					i = s;
				}
				i++;
			}
			if (p instanceof Gentil) {
				if (e != null && e instanceof Mechant)
					((Mechant) e).setVie(((Mechant) e).getVie() - ((Gentil) p).getDmg());
				}
			else if (e != null && e instanceof Gentil) {
				((Gentil) e).setVie(((Gentil) e).getVie() - p.getDmg());
				if (Grille.random(0, 101) > 24)	//75% de chances de souffrir de saignement apres une attaque de zombie
					((Gentil) e).setSaignement(true);
				if (Grille.random(0, 101) > 4)	//95% de chances d'etre infecte apres une attaque de zombie
					((Gentil) e).setInfecte(true);
			}
			effect = true;
		}
		else { // Echec de l'action
			effect = false;
		}
	}
}
