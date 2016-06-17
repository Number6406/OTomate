package Actions;

import java.util.List;

import Otomate.$Personnage;
import Otomate.Mechant;
import Otomate.Grille;

public class DeplSud extends $Action {

	public DeplSud(String succes, String echec) {
		super(succes, echec);
	}

	public DeplSud() {
		valeur = 2;
	}

	public void todo(List<Integer> l, $Personnage p, List<$Personnage> lp, Grille g) {
		if (l.get(3) == 2) {
			p.getPosition().setY(p.getPosition().getY() + 1);
			effect = true;
		}
		else {
			effect = false;
		}
		if (p instanceof Mechant) {
			if (g.Pos(p.getPosition()).piegee == true){
				((Mechant) p).setVie(((Mechant) p).getVie() - 20);
				g.Pos(p.getPosition()).piegee = false;
			}
		}
	}
}
