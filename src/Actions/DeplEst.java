package Actions;

import java.util.List;

import Otomate.$Personnage;
import Otomate.Mechant;
import Otomate.Grille;

public class DeplEst extends $Action {

	public DeplEst(String succes, String echec) {
		super(succes, echec);
	}

	public DeplEst() {
		valeur = 3;
	}

	public void todo(List<Integer> l, $Personnage p, List<$Personnage> lp, Grille g) {
		if (l.get(2) == 3) {
			p.getPosition().setX(p.getPosition().getX() + 1);
			effect = true;
		}
		else {
			effect = false;
		}
		if (p instanceof Mechant) {
			if (g.Pos(p.getPosition()).getPiegee() == true){
				((Mechant) p).setVie(((Mechant) p).getVie() - 20);
				g.Pos(p.getPosition()).setPiegee(false);		//le piege ne s'active qu'une fois
			}
		}
	}
}
