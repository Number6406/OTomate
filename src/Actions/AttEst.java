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
	}

	public AttEst() {
		valeur = 7;
	}

	public void todo(List<Integer> l, $Personnage p, List<$Personnage> lp, Grille g) {
		if (l.get(2) == 7) { // 7 = ennemi a l'est et 3eme element regard a l'est
			$Personnage e = null;
			Coordonnees card = new Coordonnees(p.getPosition());
			card.setX(card.getX() + 1);
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
				if (Grille.random(0, 101) > 24)	//75% de chances de souffrir de saignement apres une attaque de zombies
					((Gentil) e).setSaignement(true);
				if (Grille.random(0, 101) > 4)	//95% de chances d'etre infecte
					((Gentil) e).setInfecte(true);
			}
			effect = true;
        	if(p.getInactivite()<20)
        		p.setInactivite(20);
		}
		else { // Echec de l'action
			effect = false;
			p.setInactivite(p.getInactivite()-1);
		}	 
	}
}
